package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

enum ApplicantType{Applicant,PreEmployed,employed,refused}
@Entity
public class Applicant extends Resource implements Serializable{	

	private String name; 
	
	@Enumerated(EnumType.STRING)
	private ApplicantType stateApplicant;
	
	@OneToMany(mappedBy="applicant")
	List<ApplicantAnswer>answers;
	
	@ManyToMany
	List<Test>tests; 
	
	@OneToOne(mappedBy="applicant")
	private ApplicantRequest applicantRequest;
	
	@OneToOne(mappedBy="applicant")
	private ApplicantFile applicantFile;
	
	@OneToOne(mappedBy="applicant")
	private Arrival arrival;
	
	@OneToMany(mappedBy="applicant")
	List<Rdv>rdvs;
	
	@ManyToMany(mappedBy="applicants")
	List<Question>questions;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ApplicantType getStateApplicant() {
		return stateApplicant;
	}
	public void setStateApplicant(ApplicantType stateApplicant) {
		this.stateApplicant = stateApplicant;
	}
	
}
