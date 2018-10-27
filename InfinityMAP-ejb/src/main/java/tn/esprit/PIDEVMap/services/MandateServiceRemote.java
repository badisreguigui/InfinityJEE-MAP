package tn.esprit.PIDEVMap.services;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import tn.esprit.PIDEVMap.persistence.Mandate;


public interface MandateServiceRemote {
	public int affecterMandateAResource(int requestId);
	public List<Mandate>afficherListesMandats();
	public void deleteMandat(int MandateId);
	public String test();
	public Set<Mandate>findAllMandates(int ResourceId);
	public String AlerteFinMandate();
	public float calculFactureMandat(int mandateId,int ResourceId); 
	public float calculTotalMandat(int ResourceId);
	public boolean GPS(int ressourceId, int projetId);
}
