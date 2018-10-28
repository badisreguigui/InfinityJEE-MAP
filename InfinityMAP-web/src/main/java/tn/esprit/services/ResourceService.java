package tn.esprit.services;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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
	public void DeleteResourceById(@PathParam(value="id") int resourceId) {
		ResourceServiceLocal.InsertArchive(resourceId);
		
	}

	@PUT
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/updateResource/{id}")
	public Resource updateResource(@PathParam(value="id") int idResource,Resource r) {
		r.setId(idResource);
		System.out.println(r.getFirstname());
		return ResourceServiceLocal.modifierResource(r, idResource);
	}

	
	public void affecterResourceAProjet(int resourceId, int projetId) {
		
	}
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/getResources")
	public List<Resource> DisplayResources() {
		return ResourceServiceLocal.DisplayResource();
		
	}
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/filterResources")
	public List<Resource> FilterResourceByName(@QueryParam(value="lastname") String name,@QueryParam(value="firstname") String firstname
			,@QueryParam(value="seniority") int seniority,@QueryParam(value="sector") String sector
			,@QueryParam(value="profil") String profil,@QueryParam(value="contractype") String contractype,
			@QueryParam(value="state") String state,@QueryParam(value="region") String region){
		return ResourceServiceLocal.FilterByName(name,firstname,seniority,sector,profil,contractype,state,region);
	}

	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/addResourcesSkills/{idresource}/{idskill}")
	public String addResourceSkills(@PathParam(value="idresource") int resourceId,@PathParam(value="idskill") int skillId){
		ResourceServiceLocal.addSkills(resourceId, skillId);
		return "Affected";
	}
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/addResourceSkill/{idresource}")
	public String addResourceSkills(@PathParam(value="idresource") int idResource) {
		ResourceServiceLocal.addSkillResourceRating(idResource);
		return "added";
		
	}

	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/updateRating/{idresource}")
	public String updateRatingResource(@PathParam(value="idresource") int idResource) {
		ResourceServiceLocal.updateRating(idResource);
		return "rating updated";
		
	}
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/getCurrentdate")
	public String CalendarHolidays(@QueryParam(value="date") String d) throws ParseException{
		return ResourceServiceLocal.holidayCalendar(d);
		 
	}




}
