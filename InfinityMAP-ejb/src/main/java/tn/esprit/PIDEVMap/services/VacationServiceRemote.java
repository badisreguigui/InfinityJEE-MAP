package tn.esprit.PIDEVMap.services;

import tn.esprit.PIDEVMap.persistence.Vacation;

public interface VacationServiceRemote {
	public int AddVacation(Vacation v);
	public void DeleteVacationById(int VacationId);
}
