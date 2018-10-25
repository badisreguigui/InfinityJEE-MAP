package tn.esprit.PIDEVMap.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.PIDEVMap.persistence.Vacation;

@Stateless
public class VacationService implements VacationServiceRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public int AddVacation(Vacation v) {
		em.persist(v);
		return v.getId();
	}

	@Override
	public void DeleteVacationById(int VacationId) {
		Vacation v = em.find(Vacation.class, VacationId);
		em.remove(v);
		
	}

}
