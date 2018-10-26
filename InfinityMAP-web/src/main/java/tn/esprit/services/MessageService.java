package tn.esprit.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.PIDEVMap.persistence.ResourceRequest;
import tn.esprit.PIDEVMap.services.MessageServiceLocal;


@Path("/MessageService")
@RequestScoped
public class MessageService {


	
	@EJB
	MessageServiceLocal messageService;

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addMessage")
	public int ajouterMessage(ResourceRequest resourceRequest) {
		return 0;
	}

	
	public void supprimerMessage(int id) {
		// TODO Auto-generated method stub
		
	}

	
	public void modifierMessage(int id) {
		// TODO Auto-generated method stub
		
	}

	public List<ResourceRequest> getAllMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ResourceRequest> getMessagesBySender() {

return null;
	}

	public List<ResourceRequest> getMessagesByReciever() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getDiscussionFeedBack() {
		// TODO Auto-generated method stub
		return null;
	}
}