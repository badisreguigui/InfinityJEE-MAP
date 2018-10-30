package tn.esprit.PIDEVMap.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import tn.esprit.PIDEVMap.persistence.Mandate;
import tn.esprit.PIDEVMap.persistence.Resource;

@Local
public interface MandateServiceRemote {
	public String affecterMandateAResource(int requestId);
	public List<Mandate>afficherListesMandats();
	public void GererMandat(int MandateId);
	public Set<Mandate>findAllMandates(int ResourceId);
	public String AlerteFinMandate();
	public float calculFactureMandat(int mandateId,int ResourceId); 
	public float calculTotalMandat(int ResourceId);
	public double GPS(int resourceId,int clientId);
	public Mandate modifierMandat(Mandate m,int idMandate);
	public List<Mandate> SearchMandate(String dateDeb,String dateFin);
	
}
