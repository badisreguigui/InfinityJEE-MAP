package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ArchiveResources implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String lastname;
	private String firstname;
	private String picture;
	private String seniority;
	private String sector;
	private String profil;
	@Enumerated
	private ContractType contractype;
	@Enumerated
	private State state;


	public ArchiveResources(String lastname, String firstname, String picture, String seniority, String sector,
			String profil, ContractType contractype, State state) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.picture = picture;
		this.seniority = seniority;
		this.sector = sector;
		this.profil = profil;
		this.contractype = contractype;
		this.state = state;
	}
	public ArchiveResources() {
		super();
		// TODO Auto-generated constructor stub
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
	
}
