package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.PIDEVMap.persistence.ResourceRequest;

@Remote
public interface MessageServiceLocal {

	public int ajouterMessage(ResourceRequest resourceRequest);
	public void supprimerMessage(int id);
	public void modifierMessage(int id);
	public List<ResourceRequest> getAllMessage();
	public List<ResourceRequest> getMessagesBySender();
	public List<ResourceRequest> getMessagesByReciever();
	public String getDiscussionFeedBack();
}
