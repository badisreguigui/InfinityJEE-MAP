package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JsonProperty("question")
	private String question; 
	
	@JsonProperty("answer")
	private String answer; 
	
	@OneToMany(mappedBy="question")
	List<ApplicantAnswer>answers;
	
	@ManyToMany()
	List<Applicant>applicants; 
	
	@ManyToOne
	Test test; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<ApplicantAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<ApplicantAnswer> answers) {
		this.answers = answers;
	}
	public List<Applicant> getApplicants() {
		return applicants;
	}
	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	
}
