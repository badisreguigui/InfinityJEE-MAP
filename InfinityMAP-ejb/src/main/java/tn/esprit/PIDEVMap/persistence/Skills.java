package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
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
	
	@ManyToMany(mappedBy="listSkills")
	private List<Projet> listProjects;
	
	
	@ManyToMany(mappedBy="listSkills")
	private List<Resource> listResources;
	
	
	
}
