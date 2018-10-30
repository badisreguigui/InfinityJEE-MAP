package tn.esprit.services;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.PIDEVMap.persistence.Message;
import tn.esprit.PIDEVMap.persistence.User;
import tn.esprit.PIDEVMap.services.AuthentificationService;
import tn.esprit.PIDEVMap.services.InscriptionServiceRemote;
import tn.esprit.PIDEVMap.services.MandateServiceRemote;
@Path("/Inscription")

@ManagedBean
public class InscriptionService {
	
	
	@EJB
	InscriptionServiceRemote inscription;
	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String RoleUser(User u){
		return inscription.Inscription(u);
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/MesNotifs")
	public List<String>getMyNotifs(){
		return inscription.getMyNotifs();
	}
	

}
