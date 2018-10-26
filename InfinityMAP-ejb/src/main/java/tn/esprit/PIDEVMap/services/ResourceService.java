package tn.esprit.PIDEVMap.services;


import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Path;

import tn.esprit.PIDEVMap.persistence.ArchiveResources;
import tn.esprit.PIDEVMap.persistence.ContractType;
import tn.esprit.PIDEVMap.persistence.Projet;
import tn.esprit.PIDEVMap.persistence.Resource;
import tn.esprit.PIDEVMap.persistence.State;;

@Stateful
@Path("/ResourceService")
@RequestScoped
public class ResourceService implements ResourceServiceLocale  {
	@PersistenceContext(unitName="map-ejb")
	EntityManager em;
	@Override
	public int AddResource(Resource r) {
		em.persist(r);
		return r.getId();
	}

	@Override
	public void DeleteResourceById(int ResourceId)
	{
		Resource r = em.find(Resource.class, ResourceId);
		em.remove(r);
		
	}

	@Override
	public void modifierResource(Resource r) {
		em.merge(r);
	}

	
	@Override
	public void InsertArchive(int resourceId) {
		Resource r = em.find(Resource.class, resourceId);
		ArchiveResources ar = new ArchiveResources(r.getLastname(),r.getFirstname(),r.getPicture(),r.getSeniority(),r.getSector(),r.getProfil(),r.getContractype(),r.getState(),r.getRegion(),r.getRating());
		em.persist(ar);
		int query = em.createQuery(
				   "Delete from Resource s where s.id=:id")
					  .setParameter("id", resourceId).executeUpdate();
		  
	}
	
	@Override
	public void affecterResourceAProjet(int resourceId, int projetId) {
		em.find(Projet.class, projetId).getListResources().add(em.find(Resource.class, resourceId));
		
	}

	@Override
	public List<Resource> DisplayResource() {
		List<Resource> allResources;
		TypedQuery<Resource> query = em.createQuery("Select r from Resource r",Resource.class);
		allResources=query.getResultList();
		return allResources;
	}
	
	

	






}
