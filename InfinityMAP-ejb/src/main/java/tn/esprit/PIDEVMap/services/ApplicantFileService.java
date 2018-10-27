package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Path;

import tn.esprit.PIDEVMap.persistence.Applicant;
import tn.esprit.PIDEVMap.persistence.ApplicantAnswer;
import tn.esprit.PIDEVMap.persistence.ApplicantFile;
import tn.esprit.PIDEVMap.persistence.ApplicantRequest;
import tn.esprit.PIDEVMap.persistence.Question;
import tn.esprit.PIDEVMap.persistence.Test;

@Stateful
@Path("/ApplicantRequestService")
@RequestScoped
public class ApplicantFileService implements ApplicantFileServiceLocal{
	@PersistenceContext(unitName="map-ejb")
	EntityManager em;

	@Override
	public void searchAppropriateTest(int applicantId, String category) {
		TypedQuery<Test> query = em.createQuery("SELECT t FROM Test WHERE t.category = :category", Test.class); 
		Test test = query.setParameter("category", category).getSingleResult(); 
		if(test != null)
		affecterTestAapplicant(test.getId(), applicantId);
	}

	@Override
	public void affecterTestAapplicant(int testId, int applicantId) {
		em.find(Applicant.class, applicantId).getTests().add(em.find(Test.class, testId));
	}

	@Override
	public List<Question> recevoirQuestions(int testId) {
		//em.find(Test.class, testId).getQuestions(); // ne marche pas??
		TypedQuery<Question> query = em.createQuery("SELECT q FROM Question WHERE q.testId = :testId", Question.class); 
		List<Question> questions = query.setParameter("testId", testId).getResultList(); 
		return questions; 
	}

	@Override
	public String repondreQuestion(int questionId, String reponse, int applicantId) {
		ApplicantAnswer myAnswer = new ApplicantAnswer(); 
		myAnswer.setAnswer(reponse);
		
		Question questionAnswered = em.find(Question.class, questionId); 
		Applicant applicant = em.find(Applicant.class, applicantId);
		if((questionAnswered != null) && (applicant != null)){
			if(VerifierExistencePrecedente(applicantId, questionId)){ 
				myAnswer.setApplicant(applicant);
				myAnswer.setQuestion(questionAnswered);
				em.persist(myAnswer);
				return "Reponse Ajoutée"; 
			}
			return "Vous ne pouvez pas répondre 2 fois à la même question !"; 
		}
		return "Vérifiez vos données"; 
	}
	
	public boolean VerifierExistencePrecedente(int applicantId, int questionId){
		String selectQuestion = "SELECT q FROM Question AS q WHERE q.id = :questionId"; 
		selectQuestion = selectQuestion.replace(":questionId", ""+questionId); 
		
		String selectApplicant = "SELECT a FROM Applicant AS a WHERE a.id = :applicantId"; 
		selectApplicant = selectApplicant.replace(":applicantId", ""+applicantId); 
		
		TypedQuery<ApplicantAnswer> query = em.createQuery("SELECT r FROM ApplicantAnswer AS r WHERE r.applicant = ("+selectApplicant+") AND r.question = ("+selectQuestion+")", ApplicantAnswer.class); 
		List request = query.getResultList();
		
		return request.isEmpty(); 
	}

	@Override
	public void EnvoyerApplicantFile(ApplicantFile applicantFile, int applicatnId) {
		//ajouter condition de la signature
		applicantFile.setApplicant(em.find(Applicant.class, applicatnId));
		em.persist(applicantFile);
	}
	
}
