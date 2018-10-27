package tn.esprit.PIDEVMap.services;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.Path;

import tn.esprit.PIDEVMap.persistence.ArchiveResources;
import tn.esprit.PIDEVMap.persistence.ContractType;
import tn.esprit.PIDEVMap.persistence.Projet;
import tn.esprit.PIDEVMap.persistence.Resource;
import tn.esprit.PIDEVMap.persistence.Skills;
import tn.esprit.PIDEVMap.persistence.State;
import javax.persistence.EntityManagerFactory;

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
	public Resource modifierResource(Resource r,int idResource) {
		Resource r1 = em.find(Resource.class, idResource);
		r1.setId(r.getId());
		if(r.getLastname()!=null){
			r1.setLastname(r.getLastname());
		}
		if(r.getFirstname()!=null){
			r1.setFirstname(r.getFirstname());
		}
		if(r.getPicture()!=null){
			r1.setPicture(r.getPicture());
		}
		if(r.getProfil()!=null){
			r1.setProfil(r.getProfil());
		}
		if(r.getSector()!=null){
			r1.setSector(r.getSector());
		}
		if(r.getContractype()!=null){
			r1.setContractype(r.getContractype());
		}
		if(r.getSeniority()!=null){
			r1.setSeniority(r.getSeniority());
		}
		if(r.getState()!=null){
			r1.setState(r.getState());
		}
		if(r.getRegion()!=null){
			r1.setRegion(r.getRegion());
		}
		em.flush();
		return r1;
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
	
	public List<Resource> FilterByName(String name,String firstname,String seniority,String sector,String profil,
			String contractype,String state,String region){
		
			CriteriaBuilder builder = em.getCriteriaBuilder();
			
			CriteriaQuery<Resource> criteriaQuery = builder.createQuery(Resource.class);
			Root<Resource> root = criteriaQuery.from(Resource.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

		    //Adding predicates in case of parameter not being null
		    if (name != null) {
		        predicates.add(
		        		builder.equal(root.get("lastname"), name));
		    }
		    if (firstname != null) {
		        predicates.add(
		        		builder.equal(root.get("firstname"), firstname));
		    }
		    if (seniority != null) {
		        predicates.add(
		        		builder.equal(root.get("seniority"), seniority));
		    }
		    if (sector != null) {
		        predicates.add(
		        		builder.equal(root.get("sector"), sector));
		    }
		    if (profil != null) {
		        predicates.add(
		        		builder.equal(root.get("profil"), profil));
		    }
		    if (contractype != null) {
		        predicates.add(
		        		builder.equal(root.get("contractype"), contractype));
		    }
		    if (state != null) {
		        predicates.add(
		        		builder.equal(root.get("state"), state));
		    }
		    if (region != null) {
		        predicates.add(
		        		builder.equal(root.get("region"), region));
		    }
		    //query itself
		    criteriaQuery.select(root)
		            .where(predicates.toArray(new Predicate[]{}));
			Query query = em.createQuery(criteriaQuery);
			
			List<Resource> resultList = query.getResultList();
			return resultList;
	}
	
	public void addSkills(int resourceId,int skillId){
		Resource r = em.find(Resource.class,resourceId);
		Skills s = em.find(Skills.class, skillId);
		r.getListSkills().add(s);
		s.getListResources().add(r);
	}
	
	

	






}
