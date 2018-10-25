package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import java.util.Date;

@Entity
public class ApplicantAnswer {
	@Id
	private int id; 
	private String answer;
	
	@ManyToOne
	private Applicant applicant;
	
	@ManyToOne
	private Question question;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	} 
	
}
