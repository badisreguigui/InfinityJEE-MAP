package tn.esprit.PIDEVMap.services;
import java.util.Date;

import javax.ejb.Remote;

import tn.esprit.PIDEVMap.persistence.ApplicantFile;
import tn.esprit.PIDEVMap.persistence.ApplicantRequest;
import tn.esprit.PIDEVMap.persistence.Arrival;
import tn.esprit.PIDEVMap.persistence.Question;

@Remote
public interface ArrivalServiceRemote {
	void assignerParrain(int applicantId, int ressourceId);  //ajouter applicantId et ressourceId dans la table parrainage
	void ajouterArrival(Arrival arrival);  
	void modifierArrival(int arrivalId, Date newDateArrivee); 
	void annulerArrival(int arrivalId); 
	
	
}
