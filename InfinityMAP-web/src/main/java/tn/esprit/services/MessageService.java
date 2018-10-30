package tn.esprit.services;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes; 
import javax.ws.rs.POST; 
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam; 
import javax.ws.rs.Produces; 
import javax.ws.rs.QueryParam; 
import javax.ws.rs.core.MediaType; 
 
import tn.esprit.PIDEVMap.persistence.Message; 
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
	@Path("/sendMessage/{userId}") 
	public int EnvoyerMessage(Message message,@PathParam(value="userId") int userId) 
	{ 
		return messageService.EnvoyerMessage(message,userId); 
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