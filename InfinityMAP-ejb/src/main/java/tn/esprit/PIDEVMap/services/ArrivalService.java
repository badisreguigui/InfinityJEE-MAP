package tn.esprit.PIDEVMap.services;

import java.util.Date;

import javax.ejb.Stateless;

import tn.esprit.PIDEVMap.persistence.Arrival;

@Stateless
public class ArrivalService implements ArrivalServiceRemote {

	@Override
	public void assignerParrain(int applicantId, int ressourceId) {
		
	}

	@Override
	public void ajouterArrival(Arrival arrival) {
		
	}

	@Override
	public void modifierArrival(int arrivalId, Date newDateArrivee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void annulerArrival(int arrivalId) {
		// TODO Auto-generated method stub
		
	}

}
