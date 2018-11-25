package tn.esprit.services;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST; 
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam; 
import javax.ws.rs.Produces; 
import javax.ws.rs.QueryParam; 
import javax.ws.rs.core.MediaType; 
 
import tn.esprit.PIDEVMap.persistence.Message; 
import tn.esprit.PIDEVMap.persistence.ResourceRequest; 
import tn.esprit.PIDEVMap.services.MessageServiceLocal;
import tn.esprit.filters.Secured; 
 
 
@Path("/MessageService") 
@RequestScoped 
public class MessageService { 
 
 
	 
	@EJB 
	MessageServiceLocal messageService; 
 
	AuthenticationEndPoint auth;
	 
	@POST 
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Path("/sendMessage/{userId}") 
	@Secured
	public int EnvoyerMessage(Message message,@PathParam(value="userId") int userId) 
	{ 
		return messageService.EnvoyerMessage(message,userId); 
	} 
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Path("/deleteMessage/{messageId}") 
	@Secured
	public void deleteMessage(@PathParam(value="messageId") int messageId) 
	{ 
	  if(auth.LoggedPerson.getRole().equals("Client"))
	  {
			messageService.supprimerMessage(messageId); 
	  }

		  System.out.println("ACCESS DENIED ");

	} 
 
 
 
	@POST 
	@Produces(MediaType.TEXT_PLAIN) 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Path("/getMessage")
	public String getFeedBack(@QueryParam(value="discussId") int discussId,@QueryParam(value="userId") int userId) 
	{ 
		 
		return messageService.getDiscussionFeedBack(discussId, userId); 
	} 
	 
	 
	@POST 
	@Produces(MediaType.APPLICATION_JSON) 
	@Path("/classMessages")
	public List<Message> getAllResourceRequest()	 
	{ 
		return messageService.ClassMessages(); 
	} 
	 
	 
}