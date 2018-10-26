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
	
	public List<ApplicantAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<ApplicantAnswer> answers) {
		this.answers = answers;
	}
	public List<Test> getTests() {
		return tests;
	}
	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	public ApplicantRequest getApplicantRequest() {
		return applicantRequest;
	}
	public void setApplicantRequest(ApplicantRequest applicantRequest) {
		this.applicantRequest = applicantRequest;
	}
	public ApplicantFile getApplicantFile() {
		return applicantFile;
	}
	public void setApplicantFile(ApplicantFile applicantFile) {
		this.applicantFile = applicantFile;
	}
	public Arrival getArrival() {
		return arrival;
	}
	public void setArrival(Arrival arrival) {
		this.arrival = arrival;
	}
	public List<Rdv> getRdvs() {
		return rdvs;
	}
	public void setRdvs(List<Rdv> rdvs) {
		this.rdvs = rdvs;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
		
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
