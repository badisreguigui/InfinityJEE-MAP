package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

enum Category{Technique,Français}
@Entity
public class Test {
	@Id
	private int id; 
	private Category category;
	
	@OneToMany(mappedBy="test")
	List<Question> questions;
	
	@ManyToMany
	List<Applicant> applicants;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	} 
}
