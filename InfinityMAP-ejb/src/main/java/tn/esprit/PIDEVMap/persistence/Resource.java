package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Entity implementation class for Entity: Resource
 *
 */

@Entity
@JsonIgnoreProperties
public class Resource implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	@JsonProperty("lastname")
	protected String lastname;
	@JsonProperty("firstname")
	protected String firstname;
	@JsonProperty("picture")
	protected String picture;
	@JsonProperty("seniority")
	protected int seniority;
	@JsonProperty("sector")
	protected String sector;
	@JsonProperty("profil")
	protected String profil;
	@Enumerated
	@JsonProperty("contractype")
	@JsonIgnore
	protected ContractType contractype;
	@Enumerated
	@JsonProperty("state")
	protected State state;
	@JsonProperty("region")
	protected String region;
	@JsonProperty("rating")
	protected float rating;
	private Float salaireHoraire;
	private Float TotalFactureMandat;

	private String ipAdress;
    private int yearsOfExperience;
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}


	public String getIpAdress() {
		return ipAdress;
	}


	public Float getSalaireHoraire() {
		return salaireHoraire;
	}


	public void setSalaireHoraire(Float salaireHoraire) {
		this.salaireHoraire = salaireHoraire;
	}


	public Float getTotalFactureMandat() {
		return TotalFactureMandat;
	}


	public void setTotalFactureMandat(Float totalFactureMandat) {
		TotalFactureMandat = totalFactureMandat;
	}


	public Set<Vacation> getVacation() {
		return Vacation;
	}


	public void setVacation(Set<Vacation> vacation) {
		Vacation = vacation;
	}



	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}


	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}


	private int holiday;


@JsonManagedReference(value="skills")
	@OneToMany(mappedBy="ressource",fetch=FetchType.EAGER)
	private Set<Skills> listSkills;

	@JsonManagedReference(value="resource")
    @OneToMany(mappedBy="resource",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Set<Mandate>ListMandats;

@JsonManagedReference(value="projet")
	@OneToMany(mappedBy="ressource",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Set<Projet> listProjets;


@JsonManagedReference(value="vacation")
@OneToMany(mappedBy="resource",fetch=FetchType.EAGER)
private Set<Vacation> Vacation;


	public Set<Mandate> getListMandats() {
		return ListMandats;
	}
	


	public void setListMandats(Set<Mandate> ListMandats) {
		ListMandats = ListMandats;
	}


	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Resource(int id, String lastname, String firstname, String picture, int seniority, String sector,
			String profil, ContractType contractype, State state, String region, float rating) {
		super();
		this.id = id;
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
	}




	public Resource(int id, String lastname, String firstname, String picture, int seniority, String sector,
			String profil, ContractType contractype, State state, String region, float rating, Set<Skills> listSkills
			) {
		super();
		this.id = id;
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
		/*this.listProjets = listProjets;
		this.listMandats = listMandats;
		this.listVacations = listVacations;*/
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
	public int getSeniority() {
		return seniority;
	}
	public void setSeniority(int seniority) {
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


	@JsonManagedReference
	public Set<Skills> getListSkills() {
		return listSkills;
	}


	public void setListSkills(Set<Skills> listSkills) {
		this.listSkills = listSkills;
	}



	@Override
	public String toString() {
		return "Resource [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", picture=" + picture
				+ ", seniority=" + seniority + ", sector=" + sector + ", profil=" + profil + ", contractype="
				+ contractype + "]";
	}


	


	

	public int getHoliday() {
		return holiday;
	}


	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}




	public Set<Projet> getListProjets() {
		return listProjets;
	}


	public void setListProjets(Set<Projet> listProjets) {
		this.listProjets = listProjets;
	}


	
	
	





	

	
	
}
