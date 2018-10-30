package tn.esprit.PIDEVMap.services; 
 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
 
import javax.ejb.Stateful; 
import javax.enterprise.context.RequestScoped; 
import javax.json.Json; 
import javax.json.JsonArray; 
import javax.json.JsonArrayBuilder; 
import javax.json.JsonObjectBuilder; 
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext; 
import javax.persistence.TypedQuery; 
import javax.ws.rs.Path; 
 
import tn.esprit.PIDEVMap.persistence.Message; 
import tn.esprit.PIDEVMap.persistence.MessageNotif; 
import tn.esprit.PIDEVMap.persistence.ResourceRequest; 
import tn.esprit.PIDEVMap.persistence.TchatMessages; 
import tn.esprit.PIDEVMap.persistence.User; 
 
@Stateful 
@Path("/MessageService") 
@RequestScoped 
public class MessageService implements MessageServiceLocal { 
 
	@PersistenceContext(unitName="map-ejb") 
	EntityManager em; 
	 
	@Override 
	public int EnvoyerMessage(Message message,int userId) { 
		em.persist(message); 
		MessageNotif mn=new MessageNotif(); 
		mn.setDescription(message.getMessageBody()); 
		mn.setSeen(0); 
		User u=(User) em.createQuery("Select u from User u where u.userId=:userId").setParameter("userId",userId).getSingleResult(); 
		mn.setUser(u); 
		em.persist(mn); 
		return mn.getMessageNotifId(); 
	} 
 
	@Override 
	public void supprimerMessage(int id) { 
		// TODO Auto-generated method stub 
		 
	} 
 
 
	@Override 
	public List<TchatMessages> getAllMessage(int discussId) { 
		TypedQuery<TchatMessages> query=em.createQuery("select m from TchatMessages m where m.tchatdiscussion=(select d from TchatDiscussion d where d.id=76)",TchatMessages.class); 
		 
		return query.getResultList(); 
	} 
 
	 
 
	@Override 
	public String getDiscussionFeedBack(int discussId,int userId) { 
		float tauxAppreciation = 0;  
		List<String> positiveWords = Arrays.asList("merci","content","tres content","satisfait"); 
		List<String> negativeWords = Arrays.asList("deçu","non","je n'aime pas","!!!"); 
 
		List<TchatMessages> messages = getAllMessage(discussId); 
		for (int i = 0; i < messages.size(); i++)  
		{ 
 
			if(messages.get(i).getSenderID()!=userId) 
			{ 
				String[] count = messages.get(i).getMsg().split(" "); 
				float tauxPositiveWords = ((float)verifyExistence(positiveWords, messages.get(i).getMsg()) /count.length)*100; 
				if((tauxPositiveWords > 25)&&(tauxPositiveWords < 50)){ 
					tauxAppreciation+=0.5;  
				} 
				else if(tauxPositiveWords > 50){ 
					tauxAppreciation+=1;  
				} 
 
				float tauxBadWords = ((float)verifyExistence(negativeWords,messages.get(i).getMsg()) / messages.get(i).getMsg().length())*100; 
				if((tauxBadWords >= 25)&&(tauxBadWords <50)){ 
					tauxAppreciation-=0.5;  
				} 
				else if(tauxBadWords > 50){ 
					tauxAppreciation-=1;  
				} 
				 
 
		} 
		} 
			 
 
		tauxAppreciation = ((float)tauxAppreciation / messages.size()) * 100;  
		 
		if((tauxAppreciation < 25)){ 
			return "mauvaise appréciation"+tauxAppreciation;  
		} 
		else if((tauxAppreciation > 25)&&(tauxAppreciation < 40)){ 
			return "appreciation moyenne";  
		} 
		else if(tauxAppreciation > 50){ 
			return "très bonne appréciation";  
		} 
		return null; 
	} 
	 
	int verifyExistence(List<String> liste, String message){ 
		int cmpt = 0;  
 
		for (int i = 0; i <liste.size(); i++)  
		{ 
			if(message.contains(liste.get(i))) 
				cmpt++; 
		} 
 
		return cmpt;  
	} 
	 
	 
	int verifyExistence2(List<String> liste, String message){ 
		int cmpt = 0;  
 
		for (int i = 0; i <liste.size(); i++)  
		{ 
			if(message.contains(liste.get(i))) 
				cmpt++; 
		} 
System.out.println("LE COMPTEUR EST ====>"+cmpt); 
		return cmpt;  
	} 
 
	@Override 
	public void modifierMessage(int id) { 
		// TODO Auto-generated method stub 
		 
	} 
 
	@Override 
	public List<Message> ClassMessages() 
	{ 
		List<Message> allMessages = em.createQuery("select m from Message m ").getResultList(); 
		 
		List<String> clientWords = Arrays.asList("jai besoin","il me faut","au plus vite"); 
		List<String> resourceWords = Arrays.asList("excellent","travail parfait","comprehensible"); 
		List<Message> MessageClassedByClient=new ArrayList<>(); 
		List<Message> MessageClassedByResource=new ArrayList<>(); 
 
		 
		for (int i = 0; i < allMessages.size(); i++)  
		{ 
			 
		   if(verifyExistence2(clientWords, allMessages.get(i).getMessageBody())>0) 
		   { 
			   MessageClassedByClient.add(allMessages.get(i)); 
		   } 
		    
		   else if(verifyExistence2(resourceWords, allMessages.get(i).getMessageBody())>0) 
		   { 
			   System.out.println("IT ENTERS HERE "); 
			   MessageClassedByResource.add(allMessages.get(i)); 
		   } 
			 
		} 
	 
		List<Message> newList = new ArrayList<Message>(); 
		newList.addAll(MessageClassedByClient); 
		newList.addAll(MessageClassedByResource); 
		 
		return newList; 
	} 
} 
