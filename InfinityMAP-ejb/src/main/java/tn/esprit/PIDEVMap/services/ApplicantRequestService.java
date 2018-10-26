package tn.esprit.PIDEVMap.services;

import java.util.Date;
import java.util.List;
import java.util.Locale.Category;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Path;

import tn.esprit.PIDEVMap.persistence.Applicant;
import tn.esprit.PIDEVMap.persistence.ApplicantAnswer;
import tn.esprit.PIDEVMap.persistence.ApplicantFile;
import tn.esprit.PIDEVMap.persistence.ApplicantRequest;
import tn.esprit.PIDEVMap.persistence.CategoryTest;
import tn.esprit.PIDEVMap.persistence.Question;
import tn.esprit.PIDEVMap.persistence.Rdv;
import tn.esprit.PIDEVMap.persistence.RdvState;
import tn.esprit.PIDEVMap.persistence.Requeststate;
import tn.esprit.PIDEVMap.persistence.Test;

@Stateful
@Path("/ApplicantRequestService")
@RequestScoped
public class ApplicantRequestService implements AppliquantRequestLocal {

	@PersistenceContext(unitName="map-ejb")
	EntityManager em;
	
	@Override
	public int sendRequet(ApplicantRequest request) {
		request.setState(Requeststate.waiting);
		em.persist(request);
		affecterRequestAapplicant(3, request.getId()); 
		return request.getId();
	}

	@Override
	public void affecterRequestAapplicant(int applicantId, int requestId) {
		em.find(ApplicantRequest.class, requestId).setApplicant(em.find(Applicant.class, applicantId));
	}

	@Override
	public ApplicantRequest suiviRequest(int requestId) {
		ApplicantRequest request = em.find(ApplicantRequest.class, requestId);
		return request;
	}

	@Override
	public void CancelRequest(int requestId, int applicantId) {
		//verify if applicantId of the owner of the request is the same
		ApplicantRequest request = em.find(ApplicantRequest.class, requestId);
		if(request.getApplicant().getId() == applicantId){
			em.remove(request);
		}
	}

	@Override
	public Rdv TraiterDemande(int requestId, int reponse, Date date) {
		ApplicantRequest request = em.find(ApplicantRequest.class, requestId);
		if(reponse == 1){
			request.setState(Requeststate.inProcess);
			
			Rdv rdv = new Rdv(); 
			rdv.setRdvDate(date);
			rdv.setState(RdvState.waiting);
			em.persist(rdv);
			
		}
		else if (reponse == -1){
			request.setState(Requeststate.denied);
		}
		return null;
	}

	@Override
	public int proposerTest(CategoryTest categoryTest) {
		Test testReal = new Test(); 
		testReal.setCategory(categoryTest);
		
		return 0;
	}

	@Override
	public void ajouterQuestion(Question q, int testId) {
		em.persist(q); 
		em.find(Test.class, testId).getQuestions().add(q);	
	}

	@Override
	public float corrigerTest(int testId, int applicantId) { //on peut mettre cette fonction dans une boucle pour corriger tous les tests de tous les applicant
		float result = 0; 
		Test test = em.find(Test.class, testId); 
		List<Question> questions = test.getQuestions(); 
		for (Question question : questions){
			TypedQuery<ApplicantAnswer> query = em.createQuery("SELECT q FROM ApplicantAnswer WHERE q.questionId = :questionId AND q.applicantId = :applicantId", ApplicantAnswer.class); 
		    ApplicantAnswer answer = query.setParameter("questionId", question.getId())
		    	  .setParameter("applicantId", applicantId).getSingleResult(); 
		    
		    if (answer.getAnswer() == question.getAnswer()){
		    	result+=10; 
		    }
		}
		
		TypedQuery<ApplicantFile> query = em.createQuery("SELECT q FROM ApplicantFile WHERE q.applicantId = :applicantId", ApplicantFile.class); 
		ApplicantFile file = query.setParameter("applicantId", applicantId).getSingleResult(); 
	    
		if (file != null){
	    	file.setTestResult(result);	
	    }
		///mettre une appreciation sur la note
		return result/questions.size();
	}
	
	
	//@Resource(lookup = "java:/futuramail")
	private Session mailSession;

	@Asynchronous
	@Lock(LockType.READ)
	    public void sendMail(String recipient, String subject, String text) {
	        try {

	            InitialContext ic = new InitialContext();
	            mailSession = (Session) ic.lookup("java:/futuramail");
	            MimeMessage message = new MimeMessage(mailSession);
	            Address[] to = new InternetAddress[]{new InternetAddress(recipient)};
	            message.setRecipients(Message.RecipientType.TO, to);
	            message.setSubject(subject);
	            message.setSentDate(new Date());
	            message.setContent(text, "text/html");
	            //message.setText(text);
	            Transport.send(message);
	            System.out.println("mail sent");
	        } catch (MessagingException me) {
	            me.printStackTrace();
	        } catch (NamingException ex) {
	           // Logger.getLogger(MailProcessor.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

	@Override
	public void envoyerMailMinistere(String adresseMinistere, int applicantId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean consulterReponse(int applicantId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void proposerLettre(int applicantId, String bodyFormulaire) {
		// TODO Auto-generated method stub
		
	}
}
