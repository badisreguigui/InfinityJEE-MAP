package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ApplicantFile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	private float testResult;
	
	@OneToOne
	private Applicant applicant;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public float getTestResult() {
		return testResult;
	}
	public void setTestResult(float testResult) {
		this.testResult = testResult;
	} 
	
}
