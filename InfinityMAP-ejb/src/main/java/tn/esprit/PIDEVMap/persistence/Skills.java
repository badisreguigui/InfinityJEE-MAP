package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Skills implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String value;
	private float skillRate;
	
	/*@ManyToMany(mappedBy="listSkills")
	private List<Projet> listProjects;*/
	
	
	@ManyToMany(mappedBy="listSkills")
	private List<Resource> listResources;


	public Skills(String value, float skillRate, List<Resource> listResources) {
		super();
		this.value = value;
		this.skillRate = skillRate;
		//this.listProjects = listProjects;
		this.listResources = listResources;
	}


	public Skills() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public float getSkillRate() {
		return skillRate;
	}


	public void setSkillRate(float skillRate) {
		this.skillRate = skillRate;
	}





	public List<Resource> getListResources() {
		return listResources;
	}


	public void setListResources(List<Resource> listResources) {
		this.listResources = listResources;
	}
	
	
	
	
	
}
