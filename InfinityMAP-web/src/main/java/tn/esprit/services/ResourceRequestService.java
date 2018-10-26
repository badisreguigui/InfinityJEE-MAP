package tn.esprit.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.PIDEVMap.persistence.ResourceRequest;
import tn.esprit.PIDEVMap.services.ResourceRequestServiceLocal;


@Path("/ResourceRequestService")
@RequestScoped
public class ResourceRequestService {
	
	
	
	@EJB
	ResourceRequestServiceLocal resourceRequestService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addResourceRequest")
	public String ajouterResourceRequest(ResourceRequest ressourceRequest)
	{
	    resourceRequestService.ajouterResourceRequest(ressourceRequest);
	    return " Add Succeded";
	}


	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/deleteResourceRequest/{id}")
	public void supprimerResourceRequest(@PathParam(value="id")int id)
	{
			resourceRequestService.supprimerResourceRequest(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateResourceRequest")
	public void modifierResourceRequest(int id) {
		// TODO Auto-generated method stub
		
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getResourceRequest")
	public List<ResourceRequest> getAllResourceRequest()	
	{
		return resourceRequestService.getAllResourceRequest();
	}


	public void TraiterResourceRequest() {
		// TODO Auto-generated method stub
		
	}



}
