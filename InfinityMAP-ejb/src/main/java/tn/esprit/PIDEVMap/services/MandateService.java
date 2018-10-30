package tn.esprit.PIDEVMap.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue.ValueType;
import javax.json.stream.JsonParser;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.PIDEVMap.persistence.HistoriqueAssignationMandat;
import tn.esprit.PIDEVMap.persistence.Mandate;
import tn.esprit.PIDEVMap.persistence.Resource;
import tn.esprit.PIDEVMap.persistence.ResourceRequest;
import tn.esprit.PIDEVMap.persistence.State;
import tn.esprit.PIDEVMap.persistence.Vacation;

@Stateless
public class MandateService implements MandateServiceRemote {
	@PersistenceContext
	private EntityManager em;

	@Override
	public String affecterMandateAResource(int requestId) {
		double minValue=0;
		
		ResourceRequest request = em.find(ResourceRequest.class, requestId);
		String profil = request.getSearchedProfile();
		TypedQuery<Resource> query = em.createQuery("SELECT e FROM Resource e where e.profil=:profil  and e.yearsOfExperience=:years ", Resource.class)
	    .setParameter("profil", profil).setParameter("years",request.getYearsOfExperience());
	Resource r = query.getSingleResult();
		Mandate mandat = new Mandate();
		mandat.setNomMandat(request.getTitle());
		mandat.setFacture(0);
		mandat.setDate_end_mandate(request.getMandateEndDate());
		mandat.setDate_start_mandate(request.getMandateStartDate());
		mandat.setRequest(request);
		mandat.setResource(r);
		Set<Mandate> mandates = new HashSet();
		mandates.add(mandat);
		//r.setListMandats(mandates);
		request.setListMandats(mandates);
		HistoriqueAssignationMandat historique = new HistoriqueAssignationMandat();
		historique.setHeureSauvegarde(Calendar.getInstance().getTime());
		historique.setEtatMandat("prochainement");

		historique.setMandates(mandates);
		mandat.setHistorique(historique);
		em.persist(historique);
		return "affectation mandat"+mandat.toString()+" reussite pour"+r.toString();
	    


	}

	@Override
	public void GererMandat(int MandateId) {
		Mandate m = em.find(Mandate.class, MandateId);
		TypedQuery<HistoriqueAssignationMandat> query = em
				.createQuery("SELECT h FROM HistoriqueAssignationMandat h where h.HistoriqueId=:id ",
						HistoriqueAssignationMandat.class)
				.setParameter("id", m.getHistorique().getHistoriqueId());
		HistoriqueAssignationMandat historique = query.getSingleResult();
		HistoriqueAssignationMandat historiqueUpdate = new HistoriqueAssignationMandat();
		historiqueUpdate.setHeureSauvegarde(Calendar.getInstance().getTime());
		historique.setMandates(historique.getMandates());
		 Calendar cal1 = Calendar.getInstance();
	        Calendar cal2 = Calendar.getInstance();
	       
//	String date2=dateFormat.parse(Calendar.getInstance().getTime().toString())
		for (Mandate mandate : historique.getMandates()) {
cal1.setTime(mandate.getDate_end_mandate());
cal2.setTime(mandate.getDate_start_mandate());
            if (mandate.getDate_start_mandate().before(Calendar.getInstance().getTime())
            		&& mandate.getDate_end_mandate().after(Calendar.getInstance().getTime()) ){
	        historiqueUpdate.setEtatMandat(" en cours");
	        em.persist(historiqueUpdate);
          
             }
			if (mandate.getDate_end_mandate().before(Calendar.getInstance().getTime())) {
				historiqueUpdate.setEtatMandat("finie");
				em.persist(historiqueUpdate);
				em.remove(m);
			}

		}

	}

	@Override
	public List<Mandate> afficherListesMandats() {
		TypedQuery<Mandate> query = em.createQuery("select  m from Mandate m", Mandate.class);

		return query.getResultList();

	}

	@Override
	public Set<Mandate> findAllMandates(int ResourceId) {

		TypedQuery<Resource> query = em.createQuery("select  r from Resource r where r.id=:ResourceId", Resource.class)
				.setParameter("ResourceId", ResourceId);
		Set<Mandate> mandates=new HashSet<>();
		for(Mandate m:query.getSingleResult().getListMandats()){
			mandates.add(m);
		}
	
		return mandates;
	}

	@Override
	public String AlerteFinMandate() {

		TypedQuery<Mandate> query = em.createQuery("select  m from Mandate m", Mandate.class);
		for (Mandate m : query.getResultList()) {
			if (m.getDate_end_mandate().getDay() - Calendar.getInstance().getTime().getDay() <= 40) {
				final String username = "akroutabir@gmail.com";
				final String password = "botbot122";
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("akroutabir@gmail.com"));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("akroutabir@gmail.com"));
					message.setSubject("");
					message.setText("Resource:" + m.getResource().getFirstname()
							+ "\n\n Votre mandat associée au projet "+m.getRequest().getProject().getName()+" est valide encore 40jours !" + "");
					Transport.send(message);
					System.out.println("Done");
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}

			}

		}
		return "mail sended ";
	}

	@Override
	public float calculFactureMandat(int mandateId, int ResourceId) {
		float facture = 0;
		int nbreHeures = 0;
		Resource resource = em.find(Resource.class, ResourceId);
		for (Mandate m : resource.getListMandats()) {
			if (m.getMandateId() == mandateId)

			{
				Long intervalle = m.getDate_end_mandate().getTime() - m.getDate_start_mandate().getTime();
				long nbreJours = intervalle / 86400000;

				facture = (float) nbreJours * resource.getSalaireHoraire();
				m.setFacture(facture);
			}
		}
		return facture;
	}

	@Override
	public float calculTotalMandat(int ResourceId) {

		float factureTotale = 0;
		Resource resource = em.find(Resource.class, ResourceId);
		for (Mandate m : resource.getListMandats()) {

			factureTotale += m.getFacture();
			resource.setTotalFactureMandat(factureTotale);

		}
		return factureTotale;
	}

    private double deg2rad(double deg) {
      return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
      return (rad * 180.0 / Math.PI);
    }
	@Override
	public double GPS(int resourceId,int clientId) {
		
Resource r=em.find(Resource.class,resourceId);
tn.esprit.PIDEVMap.persistence.Client c1=em.find(tn.esprit.PIDEVMap.persistence.Client.class,clientId);
double lat1=getLatitude(c1.getIpAdress());
double lat2=getLatitude(r.getIpAdress());
double lon1=getLongitude(c1.getIpAdress());
double lon2=getLongitude(r.getIpAdress());
		      double theta = lon1 - lon2;
		      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		      dist = Math.acos(dist);
		      dist = rad2deg(dist);
		      dist = dist * 60 * 1.1515;
		    
		        dist = dist * 1.609344;
		    
		      return (dist);
		    }

		    

	
	

	
	public double getLatitude(String s)  {
	Client client = ClientBuilder.newClient();
		Response response = client.target("http://api.ipstack.com/"+s+"?access_key=6bac93a513e7d21a4536fd82ec523554&Scallback = MY_FUNCTION")
		  .request().get();
		 JsonObject obj;
		
		 JsonReader reader = Json.createReader(new StringReader(response.readEntity(String.class)));
         
	        JsonObject personObject = reader.readObject();
	     JsonNumber lat=personObject.getJsonNumber("latitude");
	     JsonNumber lon=personObject.getJsonNumber("longitude");
	      double latitude=Double.parseDouble(lat.toString());
	      double longitude=Double.parseDouble(lon.toString());
		 return latitude;
				
	}
	
	public double getLongitude(String s) {
		Client client = ClientBuilder.newClient();
		Response response = client.target(
				"http://api.ipstack.com/" + s + "?access_key=6bac93a513e7d21a4536fd82ec523554&Scallback = MY_FUNCTION")
				.request().get();
		JsonObject obj;

		JsonReader reader = Json.createReader(new StringReader(response.readEntity(String.class)));

		JsonObject personObject = reader.readObject();

		JsonNumber lat = personObject.getJsonNumber("latitude");
		JsonNumber lon = personObject.getJsonNumber("longitude");

		double longitude = Double.parseDouble(lon.toString());
		return longitude;

	}
	@Override
	public Mandate modifierMandat(Mandate m, int idMandate) {
	Mandate mandate=em.find(Mandate.class,51);
	mandate.setDate_end_mandate(m.getDate_end_mandate());
	mandate.setDate_start_mandate(m.getDate_start_mandate());
	return mandate;
	}

	@Override
	public List<Mandate> SearchMandate(String dateDeb,String dateFin) {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date dateDebut=new Date();
		    Date dateEnd=new Date() ;
			try {
				dateDebut = sdf.parse(dateDeb);
			    dateEnd = sdf.parse(dateFin);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		
		TypedQuery<Mandate> query = em.createQuery("select  m from Mandate m where m.date_start_mandate=:dateDeb and "
				+ "date_end_mandate=:dateFin", Mandate.class);
		query.setParameter("dateDeb",dateDebut).setParameter("dateFin",dateEnd);

		return query.getResultList();
	}
	


}
