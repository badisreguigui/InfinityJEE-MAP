package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.PIDEVMap.persistence.Applicant;
import tn.esprit.PIDEVMap.persistence.ApplicantAnswer;
import tn.esprit.PIDEVMap.persistence.ApplicantFile;
import tn.esprit.PIDEVMap.persistence.ApplicantRequest;
import tn.esprit.PIDEVMap.persistence.Question;
import tn.esprit.PIDEVMap.persistence.Test;

@Stateless
public class ApplicantFileService implements ApplicantFileServiceRemote{
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
	public void repondreQuestion(int questionId, String reponse, int applicantId) {
		ApplicantAnswer myAnswer = new ApplicantAnswer(); 
		myAnswer.setAnswer(reponse);
		myAnswer.setApplicant(em.find(Applicant.class, applicantId));
		myAnswer.setQuestion(em.find(Question.class, questionId));
	}

	@Override
	public void EnvoyerApplicantFile(ApplicantFile applicantFile, int applicatnId) {
		//ajouter condition de la signature
		applicantFile.setApplicant(em.find(Applicant.class, applicatnId));
		em.persist(applicantFile);
	}
	
}
