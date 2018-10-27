package tn.esprit.PIDEVMap.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ejb.Stateless;
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

import tn.esprit.PIDEVMap.persistence.HistoriqueAssignationMandat;
import tn.esprit.PIDEVMap.persistence.Mandate;
import tn.esprit.PIDEVMap.persistence.Resource;
import tn.esprit.PIDEVMap.persistence.ResourceRequest;

@Stateless
public class MandateService implements MandateServiceRemote {
	@PersistenceContext
	private EntityManager em;

	@Override
	public int affecterMandateAResource(int requestId) {
		 Resource r1=new Resource();
		 r1.setProfil("angular");
		 r1.setSalaireHoraire(Float.parseFloat("0"));
		 r1.setTotalFactureMandat(Float.parseFloat("0"));
		 em.persist(r1);
		ResourceRequest request = em.find(ResourceRequest.class, requestId);
		String profil = request.getSearchedProfile();
		TypedQuery<Resource> query = em.createQuery("SELECT e FROM Resource e where e.profil=:profil  ", Resource.class)
				.setParameter("profil", profil);
		// .setParameter("dateFin", request.getProject().getDateEnd(),
		// TemporalType.DATE)
		// .setParameter("dateDeb", request.getProject().getDateBegin(),
		// TemporalType.DATE);
		Resource r = query.getSingleResult();
		Mandate mandat = new Mandate();
		mandat.setNomMandat(request.getTitle());
		mandat.setFacture(0);
		mandat.setDate_end_mandate(request.getMandateEndDate());
		mandat.setDate_start_mandate(request.getMandateStartDate());
		mandat.setRequest(request);
		mandat.setResource(r);
		List<Mandate> mandates = new ArrayList<>();
		mandates.add(mandat);
		r.setListMandats(mandates);
		request.setListMandats(mandates);
		em.persist(r);
		em.persist(request);

		HistoriqueAssignationMandat historique = new HistoriqueAssignationMandat();
		historique.setHeureSauvegarde(Calendar.getInstance().getTime());
		historique.setEtatMandat("prochainement");

		historique.setMandates(mandates);
		mandat.setHistorique(historique);
		em.persist(historique);
		return mandat.getMandateId();

	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteMandat(int MandateId) {
		Mandate m = em.find(Mandate.class, MandateId);
		TypedQuery<HistoriqueAssignationMandat> query = em
				.createQuery("SELECT h FROM HistoriqueAssignationMandat h where h.HistoriqueId=:id ",
						HistoriqueAssignationMandat.class)
				.setParameter("id", m.getHistorique().getHistoriqueId());
		HistoriqueAssignationMandat historique = query.getSingleResult();
		HistoriqueAssignationMandat historiqueUpdate = new HistoriqueAssignationMandat();
		historiqueUpdate.setHeureSauvegarde(Calendar.getInstance().getTime());
		historique.setMandates(historique.getMandates());
		for (Mandate mandate : historique.getMandates()) {

			if (mandate.getDate_start_mandate().after(Calendar.getInstance().getTime())) {
				historiqueUpdate.setEtatMandat("En cours");
				em.persist(historiqueUpdate);
			}
			if (mandate.getDate_end_mandate().after(Calendar.getInstance().getTime())) {
				historiqueUpdate.setEtatMandat("finie");
				em.persist(historiqueUpdate);
				em.remove(m);
			}

		}

	}

	@Override
	public String test() {

		return "haifa";
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
			if (m.getDate_end_mandate().getDay() - Calendar.getInstance().getTime().getDay() <= 2) {
				final String username = "akroutabir@gmail.com";
				final String password = "";
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
							+ "\n\n ta mandat est valide encore 40jours !" + "");
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

	@Override
	public boolean GPS() {
	
		return true;
	}
}
