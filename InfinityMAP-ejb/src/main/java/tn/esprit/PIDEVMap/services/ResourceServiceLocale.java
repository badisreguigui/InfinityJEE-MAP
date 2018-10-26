package tn.esprit.PIDEVMap.services;


import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.PIDEVMap.persistence.Resource;

@Local
public interface ResourceServiceLocale {

	public int AddResource(Resource r);
	public void DeleteResourceById(int ResourceId);
	public void modifierResource(Resource r);
	public void InsertArchive(int resourceId);
	public void affecterResourceAProjet(int resourceId, int projetId);
	public List<Resource> DisplayResource();
}
