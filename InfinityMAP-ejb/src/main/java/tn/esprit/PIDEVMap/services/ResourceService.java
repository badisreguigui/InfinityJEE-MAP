package tn.esprit.PIDEVMap.services;


import java.util.Date;
import java.util.HashSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

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
import tn.esprit.PIDEVMap.persistence.LigneSkill;
import tn.esprit.PIDEVMap.persistence.Mandate;
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
		r.setHoliday(0);
		r.setSalaireHoraire(null);
		r.setTotalFactureMandat(null);
		r.setRating(0);
		r.setState(State.Available);
		r.setListMandats(null);
		r.setListProjets(null);
		r.setListSkills(null);
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
		if(r.getSeniority()!=0){
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
	public Set<Resource> DisplayResource() {
		List<Resource> allResources;
		Set<Resource> allResourcesSet = new HashSet<>();
		TypedQuery<Resource> query = em.createQuery("Select r from Resource r",Resource.class);
		allResources=query.getResultList();
		for(Resource r : allResources){
			allResourcesSet.add(r);
		}
		return allResourcesSet;
	}
	public Set<Resource> FilterByName(String name,String firstname,int seniority,String sector,String profil,
			String contractype,String state,String region){
			Set<Resource> ResourcesSet = new HashSet<>();
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
		    if (seniority != 0) {
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
			for(Resource r : resultList){
				ResourcesSet.add(r);
			}
			return ResourcesSet;
	}
	
	public void addSkills(int resourceId,int skillId){
		Resource r = em.find(Resource.class,resourceId);
		Skills s = em.find(Skills.class, skillId);
		s.setRessource(em.find(Resource.class, resourceId));
		/*List<Skills> listskills1 = new ArrayList<>();
		for(Skills s1 : r.getListSkills()){
			listskills1.add(s1);
		}*/
		//r.getListSkills().add(s);
		/*for(Skills s2 : listskills1){
			System.out.println(s2.getValue());
			if(s2==s){
				System.out.println("Already there :)");
				r.getListSkills().remove(s);
			}


		}*/
		
		
	}

	@Override
	public void addSkillResourceRating(int idRessource) {
		List<LigneSkill> allLigneSkillValues;
		Resource r = em.find(Resource.class, idRessource);
		TypedQuery<LigneSkill> query = em.createQuery("Select l from LigneSkill l WHERE l.idResource=:id",LigneSkill.class)
				.setParameter("id", idRessource);
		allLigneSkillValues=query.getResultList();
		for( Skills s : r.getListSkills()){
			LigneSkill skillIndi= new LigneSkill();
			System.out.println(s.getValue());
			skillIndi.setValue(s.getValue());
			skillIndi.setIdResource(idRessource);
			em.persist(skillIndi);
			for(LigneSkill l : allLigneSkillValues){
				if(l.getValue().equals(s.getValue())){
					System.out.println("Already there baby");
					em.remove(l);
				}
			}
				
		}
			
	}
	
	/*Resource r = em.find(Resource.class, idRessource);
	List<String> allLigneSkillValues;
	
	TypedQuery<String> query = em.createQuery("Select l.value from LigneSkill l WHERE l.idResource=:id",String.class)
			.setParameter("id", idRessource);
	allLigneSkillValues=query.getResultList();
	for( Skills s : r.getListSkills()){
		for(String value : allLigneSkillValues){
			if(value.equals(s.getValue())){
				System.out.println("already there");
			}
			else{
				LigneSkill skillIndi= new LigneSkill();
				System.out.println(s.getValue());
				skillIndi.setValue(s.getValue());
				skillIndi.setIdResource(idRessource);
				em.persist(skillIndi);
			}
		}
		
	}*/

	@Override
	public float CalculRating(int idRessource) {
		float rate=0;
		List<LigneSkill> listeskill= new ArrayList<>();
		TypedQuery<LigneSkill> query = em.createQuery("Select l from LigneSkill l WHERE l.idResource=:id",LigneSkill.class)
				.setParameter("id", idRessource);
		listeskill=query.getResultList();
		for (LigneSkill l: listeskill)
		{
			TypedQuery<Skills> query1 = em.createQuery("Select s from Skills s where s.value = :name",Skills.class)
					.setParameter("name", l.getValue());
			Skills s = query1.getSingleResult();
			rate+=s.getSkillRate();
		}
		float avg = 0; 
		if(listeskill.size() != 0){
		avg = rate / listeskill.size(); }
		return avg; 
	}

	@Override
	public Resource updateRating(int idResource) {
		Resource r1 = em.find(Resource.class, idResource);
		float rating = CalculRating(idResource) ;
		r1.setRating(rating);
		return r1;
	}

	@Override
	public String holidayCalendar(String d) throws ParseException {
		int workstate=0;
		String date = null;
		Calendar holidays = Calendar.getInstance();
		List<Resource> allResources;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date d2=dateFormat.parse(d);
		holidays.setTime(d2);
		System.out.println(d2);
		System.out.println(holidays.getTime());
		//DateFormat dateFormat1 = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
		if(holidays.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || holidays.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || (holidays.get(Calendar.MONTH) == Calendar.JANUARY
				&& holidays.get(Calendar.DAY_OF_MONTH) == 1) || (holidays.get(Calendar.MONTH) == Calendar.MARCH
				&& holidays.get(Calendar.DAY_OF_MONTH) == 20)){
			System.out.println(holidays.get(holidays.DAY_OF_WEEK));
			workstate=1;
			date = "HOLYDAAAAAAAAAAAAAAAYSSSSSS!!!!!!!!!!!!!!!!";
		}
		else{
			System.out.println(holidays.DAY_OF_WEEK);
			workstate=0;
			date = "WORKKKKKKKKKKKKKKKKKKKK";
		}
		int query1 = em.createQuery(
				   "UPDATE Resource SET holiday=:workstate").setParameter("workstate", workstate).executeUpdate();
		
		return date;
	}
	
	
	
	public void ResourceToProject(int resourceId,int projetId){
		Resource r = em.find(Resource.class,resourceId);
		for(Mandate r1 : r.getListMandats()){
			if (r1.getDate_start_mandate().after(r1.getDate_end_mandate())){
				
			}
			
		}
		Projet s = em.find(Projet.class, projetId);
		s.setRessource(em.find(Resource.class, resourceId));
		
		
	}
	

	






}
