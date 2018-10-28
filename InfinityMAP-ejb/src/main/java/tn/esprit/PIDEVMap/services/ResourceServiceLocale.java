package tn.esprit.PIDEVMap.services;


import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
	public Set<Resource> DisplayResource();
	public Set<Resource> FilterByName(String name,String firstname,int seniority,String sector,String profil,
			String contractype,String state,String region);
	public void addSkills(int resourceId,int skillId);
	public void addSkillResourceRating(int idRessource);
	public float CalculRating(int idRessource);
	public Resource updateRating(int idResource);
	public String holidayCalendar(String d) throws ParseException;
	public void ResourceToProject(int resourceId,int projetId);
}
