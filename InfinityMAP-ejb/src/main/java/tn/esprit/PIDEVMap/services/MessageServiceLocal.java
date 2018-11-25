package tn.esprit.PIDEVMap.services;
import java.util.List; 
 
import javax.ejb.Local; 
import javax.ejb.Remote;

import tn.esprit.PIDEVMap.persistence.Message;
import tn.esprit.PIDEVMap.persistence.ResourceRequest; 
import tn.esprit.PIDEVMap.persistence.TchatMessages; 
 
@Local 
public interface MessageServiceLocal { 
 
	public int EnvoyerMessage(Message message,int userId); 
	public void supprimerMessage(int messageId); 
	public void modifierMessage(int id); 
	public List<TchatMessages> getAllMessage(int discussId); 
	/*public List<Message> getMessagesBySender(); 
	public List<Message> getMessagesByReciever();*/ 
	public String getDiscussionFeedBack(int discussionId,int userId); 
	public List<Message> ClassMessages(); 
	 
} 
