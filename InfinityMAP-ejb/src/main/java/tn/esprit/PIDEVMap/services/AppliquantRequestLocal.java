package tn.esprit.PIDEVMap.services;
import java.util.Date;
import java.util.Locale.Category;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.PIDEVMap.persistence.ApplicantRequest;
import tn.esprit.PIDEVMap.persistence.CategoryTest;
import tn.esprit.PIDEVMap.persistence.Question;
import tn.esprit.PIDEVMap.persistence.Rdv;
import tn.esprit.PIDEVMap.persistence.Requeststate;
import tn.esprit.PIDEVMap.persistence.Test;

@Local
public interface AppliquantRequestLocal {
	public int sendRequet(String speciality); 
	public String affecterRequestAapplicant(int appliquantId, int requestId); 
	public Requeststate suiviRequest(int requestId);
	public String CancelRequest(int applicantId, int requestId); //supprimer + verifier si applicantId est celui de la demande
	
	/////Partie admin
	public String TraiterDemande(int requestId, int reponse, Date date); //retourner le rdv et modifier etat Request
	public int proposerTest(CategoryTest categoryTest); 
	public void ajouterQuestion(Question q, int testId);
	public float corrigerTest(int testId, int applicantId); //update note dans dossier ou ressource
	public void proposerLettre(int applicantId, String bodyFormulaire); // formulaire: ajouter sous forme de lettre + bodyFormulaire split 
	/////Partie suivi interne de l'embauche
	public void envoyerMailMinistere(String adresseMinistere, int applicantId); 
	public boolean consulterReponse(int applicantId);  //scrapping mail pour connaitre la reponse + modifier region de la ressource
	////
	
}
