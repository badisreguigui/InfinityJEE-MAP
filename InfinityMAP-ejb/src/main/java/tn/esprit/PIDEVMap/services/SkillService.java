package tn.esprit.PIDEVMap.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Path;

import tn.esprit.PIDEVMap.persistence.Resource;
import tn.esprit.PIDEVMap.persistence.Skills;

@Stateful
@Path("/SkillService")
@RequestScoped
public class SkillService implements SkillServiceLocale {
	@PersistenceContext(unitName="map-ejb")
	EntityManager em;
	@Override
	public int addSkill(Skills s) {
		em.persist(s);
		List<Skills> listSkills = new ArrayList<>();
		TypedQuery<Skills> query1 = em.createQuery("Select s from Skills s where s.value = :name",Skills.class)
				.setParameter("name", s.getValue());
		listSkills = query1.getResultList();
		for(Skills s1 : listSkills)
		if(s1.getValue().equals(s.getValue())){
			System.out.println(s1.getValue());
			System.out.println(s.getValue());
			s.setSkillRate(s1.getSkillRate());
			em.flush();
		}
		
		
		return s.getId();
	}

	@Override
	public void DeleteSkill(int skillId) {
		Skills s = em.find(Skills.class, skillId);
		em.remove(s);
		
	}

	@Override
	public Skills updateSkill(Skills s, int skillId) {
		Skills s1 = em.find(Skills.class, skillId);
		s1.setId(s1.getId());
		if(s.getValue()!=null){
			s1.setValue(s.getValue());
		}
		if(s.getSkillRate()!=0){
			s1.setSkillRate(s.getSkillRate());
		}
		em.flush();
		return s1;
	}

	@Override
	public List<Skills> GetSkills() {
		List<Skills> allSkills;
		TypedQuery<Skills> query = em.createQuery("Select r from Skills r",Skills.class);
		allSkills=query.getResultList();
		return allSkills;
	}

	@Override
	public List<Object[]> ResourcesNumberBySkill() {
		List<Object[]> ResourcesNumberBySkillSql;
		TypedQuery<Object[]> query = em.createQuery("SELECT s.value,count(s.value) from Skills s where s.ressource.id <> '' group by s.value order by count(s) desc ",Object[].class);
		return ResourcesNumberBySkillSql=query.getResultList();
		
	}

}
