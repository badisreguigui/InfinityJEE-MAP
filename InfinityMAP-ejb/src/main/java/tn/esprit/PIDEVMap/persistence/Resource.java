package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * Entity implementation class for Entity: Resource
 *
 */

@Entity

public class Resource implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	protected String lastname;
	protected String firstname;
	protected String picture;
	protected String seniority;
	protected String sector;
	protected String profil;
	@Enumerated
	protected ContractType contractype;
	@Enumerated
	protected State state;
	
	protected String region;
	protected float rating;
	
	
	@ManyToMany
	private List<Skills> listSkills;
	
	@ManyToMany(mappedBy="listResources")
	private List<Projet> listProjets;

	
	 	@OneToMany(mappedBy="resource")
		private List<Mandate> listMandats;

	 	@OneToMany(mappedBy="resource")
		private List<Vacation> listVacations;
	 	
	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	




	public Resource(String lastname, String firstname, String picture, String seniority, String sector, String profil,
			ContractType contractype, State state, String region, float rating, List<Skills> listSkills,
			List<Projet> listProjets, List<Mandate> listMandats, List<Vacation> listVacations) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.picture = picture;
		this.seniority = seniority;
		this.sector = sector;
		this.profil = profil;
		this.contractype = contractype;
		this.state = state;
		this.region = region;
		this.rating = rating;
		this.listSkills = listSkills;
		this.listProjets = listProjets;
		this.listMandats = listMandats;
		this.listVacations = listVacations;
	}









	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}


	public ContractType getContractype() {
		return contractype;
	}


	public void setContractype(ContractType contractype) {
		this.contractype = contractype;
	}




	public List<Mandate> getListMandats() {
		return listMandats;
	}




	public void setListMandats(List<Mandate> listMandats) {
		this.listMandats = listMandats;
	}




	public String getRegion() {
		return region;
	}




	public void setRegion(String region) {
		this.region = region;
	}




	public float getRating() {
		return rating;
	}




	public void setRating(float rating) {
		this.rating = rating;
	}




	public List<Projet> getListProjets() {
		return listProjets;
	}




	public void setListProjets(List<Projet> listProjets) {
		this.listProjets = listProjets;
	}




	public List<Vacation> getListVacations() {
		return listVacations;
	}




	public void setListVacations(List<Vacation> listVacations) {
		this.listVacations = listVacations;
	}
	
	

	
	
}