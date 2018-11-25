package tn.esprit.PIDEVMap.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
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
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.PIDEVMap.persistence.Client;
import tn.esprit.PIDEVMap.persistence.Projet;
import tn.esprit.PIDEVMap.persistence.ResourceRequest;

@Stateful
@Path("/ResourceRequestService")
@RequestScoped
public class ResourceRequestService implements ResourceRequestServiceLocal {
	
	@PersistenceContext(unitName="map-ejb")
	EntityManager em;
	
	
	
	
	@Override
	public void supprimerResourceRequest(int id)
	{
		System.out.println(id);
		em.remove(em.find(ResourceRequest.class,id));	
	}

	@Override
	
	public void modifierResourceRequest(int id) {
		
		 
		
		
	}

	@Override
	public List<ResourceRequest> getAllResourceRequest() {
    
		List<ResourceRequest>AllResources;
		 TypedQuery<ResourceRequest> query=
				 em.createQuery("Select r from ResourceRequest r",ResourceRequest.class);
		AllResources=query.getResultList();
		 return AllResources;
	}


	@Override
	public void TraiterResourceRequest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String ajouterResourceRequest(ResourceRequest ressourceRequest, int idProjet, int idClient) {

		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		//System.out.println(date); 
        ressourceRequest.setProject(em.find(Projet.class,idProjet));
        ressourceRequest.setClient(em.find(Client.class,idClient));

       ressourceRequest.setDepotDate(date);
       ressourceRequest.setMandateEndDate(date);
       ressourceRequest.setMandateStartDate(date);
		em.persist(ressourceRequest);
		final String username = "slim.aouadi@esprit.tn";
		final String password = "slim@5ra21156990";
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
			message.setFrom(new InternetAddress("slim.aouadi@esprit.tn"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("slim.aouadi@esprit.tn"));
			message.setSubject("New Resource Request");
			message.setText("You've just recieved a new Resource Request from Slim");
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return " Successfull";
	}



}
