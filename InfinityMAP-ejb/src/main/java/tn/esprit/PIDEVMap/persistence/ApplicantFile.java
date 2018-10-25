package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ApplicantFile {
	@Id
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
	public float getTestResult() {
		return testResult;
	}
	public void setTestResult(float testResult) {
		this.testResult = testResult;
	} 
	
}
