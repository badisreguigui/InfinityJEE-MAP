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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties
public class Skills implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String value;
	private float skillRate;
	
	/*@ManyToMany(mappedBy="listSkills")
	private List<Projet> listProjects;*/
	@JsonBackReference(value="skills")
	@ManyToOne
	@JsonProperty("listResources")
	private Resource ressource;
	




	public Skills(String value, float skillRate, Resource ressource) {
		super();
		this.value = value;
		this.skillRate = skillRate;
		this.ressource = ressource;
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


	@JsonBackReference
	public Resource getRessource() {
		return ressource;
	}


	public void setRessource(Resource ressource) {
		this.ressource = ressource;
	}




	
	
	
	
}
