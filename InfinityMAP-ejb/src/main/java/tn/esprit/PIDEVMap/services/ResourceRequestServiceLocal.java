package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.json.JsonArray;
import javax.json.JsonObject;

import tn.esprit.PIDEVMap.persistence.ResourceRequest;


@Local
public interface ResourceRequestServiceLocal
{
	public String ajouterResourceRequest(ResourceRequest ressourceRequest,int idProjet);
	public void supprimerResourceRequest(int id);
	public void modifierResourceRequest(int id);
	public List<ResourceRequest> getAllResourceRequest();
	public void TraiterResourceRequest();
	
}
