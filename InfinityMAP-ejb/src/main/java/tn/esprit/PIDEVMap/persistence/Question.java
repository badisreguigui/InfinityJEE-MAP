package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Question {
	@Id
	private int id;
	private String question; 
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
	
}
