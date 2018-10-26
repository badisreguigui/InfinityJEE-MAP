package tn.esprit.PIDEVMap.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.PIDEVMap.persistence.ResourceRequest;

@Stateful
@Path("/ResourceRequestService")
@RequestScoped
public class ResourceRequestService implements ResourceRequestServiceLocal {
	
	@PersistenceContext(unitName="map-ejb")
	EntityManager em;
	
	
	@Override
	public String ajouterResourceRequest(ResourceRequest ressourceRequest)
	{

		em.persist(ressourceRequest);
		return " Successfull";
	}

	
	@Override
	public void supprimerResourceRequest(int id)
	{
		System.out.println(id);
		em.remove(em.find(ResourceRequest.class,id));	
	}

	@Override
	public void modifierResourceRequest(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ResourceRequest> getAllResourceRequest() {
    
		List<ResourceRequest>AllResources;
		 TypedQuery<ResourceRequest> query=
				 em.createQuery("Select r from ResourceRequest r",ResourceRequest.class);
		AllResources=query.getResultList();
		 return AllResources;
	}


	@Override
	public void TraiterResourceRequest() {
		// TODO Auto-generated method stub
		
	}



}
