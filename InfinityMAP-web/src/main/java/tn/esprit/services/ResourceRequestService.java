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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import tn.esprit.PIDEVMap.persistence.ResourceRequest;
import tn.esprit.PIDEVMap.services.ResourceRequestServiceLocal;
import tn.esprit.filters.Secured;


@Path("/ResourceRequestService")
@RequestScoped
public class ResourceRequestService {
	
	
	
	@EJB
	ResourceRequestServiceLocal resourceRequestService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addResourceRequest")
	public String ajouterResourceRequest(ResourceRequest ressourceRequest,@QueryParam(value="idProjet") int idProjet,@QueryParam(value="idClient") int idClient)
	{
		
	    resourceRequestService.ajouterResourceRequest(ressourceRequest,idProjet,idClient);
	    return " Add Succeded";
	}
	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addResourceRequest")
	public String ajouterResourceRequest(ResourceRequest ressourceRequest)
	{
	    resourceRequestService.ajouterResourceRequest2(ressourceRequest);
	    return " Add Succeded";
	}
*/

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
	@Path("/updateResourceRequest/{id}")
	public void modifierResourceRequest(@PathParam(value="id")int id)
	{
		resourceRequestService.modifierResourceRequest(id);
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getResourceRequest")
	public List<ResourceRequest> getAllResourceRequest()	
	{
		return resourceRequestService.getAllResourceRequest();
	}

	@Secured
	public void TraiterResourceRequest() {
		// TODO Auto-generated method stub
		
	}



}
