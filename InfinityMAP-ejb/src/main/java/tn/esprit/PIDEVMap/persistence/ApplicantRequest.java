package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import java.util.Date;

enum Requeststate{inProcess,denied,waiting}
@Entity
public class ApplicantRequest {
	@Id
	private int id; 
	private Date date; 
	private String speciality; 
	@Enumerated(EnumType.STRING)
	private Requeststate state;
	
	@OneToOne
	private Applicant applicant;
	
	public Requeststate getState() {
		return state;
	}
	public void setState(Requeststate state) {
		this.state = state;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
}
