package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;

import tn.esprit.PIDEVMap.persistence.ResourceRequest;

@Stateful
@Path("/MessageService")
@RequestScoped
public class MessageService implements MessageServiceLocal {

	@PersistenceContext(unitName="map-ejb")
	EntityManager em;
	
	@Override
	public int ajouterMessage(ResourceRequest resourceRequest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void supprimerMessage(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierMessage(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ResourceRequest> getAllMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceRequest> getMessagesBySender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceRequest> getMessagesByReciever() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDiscussionFeedBack() {
		// TODO Auto-generated method stub
		return null;
	}

}
