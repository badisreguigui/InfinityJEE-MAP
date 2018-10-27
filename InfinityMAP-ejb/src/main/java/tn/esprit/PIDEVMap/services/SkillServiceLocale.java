package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.PIDEVMap.persistence.Skills;

@Local
public interface SkillServiceLocale {
	
	public int addSkill(Skills s);
	public void DeleteSkill(int skillId);
	public Skills updateSkill(Skills s,int skillId);
	public List<Skills> GetSkills();
	public int ResourcesNumberBySkill(int skillId);
	
	
	

}
