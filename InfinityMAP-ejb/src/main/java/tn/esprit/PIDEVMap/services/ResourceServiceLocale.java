package tn.esprit.PIDEVMap.services;


import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.PIDEVMap.persistence.Resource;

@Local
public interface ResourceServiceLocale {

	public int AddResource(Resource r);
	public void DeleteResourceById(int ResourceId);
	public Resource modifierResource(Resource r,int idResource);
	public void InsertArchive(int resourceId);
	public void affecterResourceAProjet(int resourceId, int projetId);
	public List<Resource> DisplayResource();
	public List<Resource> FilterByName(String name,String firstname,String seniority,String sector,String profil,
			String contractype,String state,String region);
	public void addSkills(int resourceId,int skillId);
}
