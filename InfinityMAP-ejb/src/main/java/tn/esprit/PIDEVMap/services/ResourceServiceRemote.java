package tn.esprit.PIDEVMap.services;


import javax.ejb.Remote;

import tn.esprit.PIDEVMap.persistence.Resource;

@Remote
public interface ResourceServiceRemote {

	public int AddResource(Resource r);
	public void DeleteResourceById(int ResourceId);
	public void modifierResource(Resource r);
	public void InsertArchive(int resourceId);
	public void affecterResourceAProjet(int resourceId, int projetId);
}
