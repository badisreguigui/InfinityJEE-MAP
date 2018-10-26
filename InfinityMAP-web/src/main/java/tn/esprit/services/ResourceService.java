package tn.esprit.services;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import tn.esprit.PIDEVMap.persistence.ArchiveResources;
import tn.esprit.PIDEVMap.persistence.ContractType;
import tn.esprit.PIDEVMap.persistence.Projet;
import tn.esprit.PIDEVMap.persistence.Resource;
import tn.esprit.PIDEVMap.persistence.State;
import tn.esprit.PIDEVMap.services.ResourceServiceLocale;;

@Path("/ResourceService")
@RequestScoped
public class ResourceService  {
	

	@EJB
	ResourceServiceLocale ResourceServiceLocal;
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addResource")
	public String AddResource(Resource r) {
		ResourceServiceLocal.AddResource(r);
		return "added";
		
	}

	@GET
	@javax.ws.rs.Produces(MediaType.TEXT_PLAIN)
	@Path("/deleteResource/{id}")
	public void DeleteResourceById(@PathParam(value="id") int ResourceId) {
		ResourceServiceLocal.InsertArchive(ResourceId);
		
	}

	public void modifierResource(Resource r) {
	}

	
	public void InsertArchive(int resourceId) {

		  
	}
	
	public void affecterResourceAProjet(int resourceId, int projetId) {
		
	}
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/getResources")
	public List<Resource> DisplayResources() {
		return ResourceServiceLocal.DisplayResource();
		
	}

	






}
