package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Test {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	private CategoryTest category;
	
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

	public CategoryTest getCategory() {
		return category;
	}

	public void setCategory(CategoryTest categoryTest) {
		this.category = categoryTest;
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
