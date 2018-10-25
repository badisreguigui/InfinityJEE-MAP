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
	
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
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
