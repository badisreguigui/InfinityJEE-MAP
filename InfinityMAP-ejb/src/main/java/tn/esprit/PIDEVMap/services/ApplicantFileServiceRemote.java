package tn.esprit.PIDEVMap.services;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.PIDEVMap.persistence.ApplicantFile;
import tn.esprit.PIDEVMap.persistence.ApplicantRequest;
import tn.esprit.PIDEVMap.persistence.Question;

@Remote
public interface ApplicantFileServiceRemote {
	/////Passer Test
	public void searchAppropriateTest(int applicantId, String categorie); //y mettre fonction affecter aussi
	public void affecterTestAapplicant(int testId, int applicantId); 
	public List<Question> recevoirQuestions(int testId); //pour l'affichage par la suite dans .Net et maintenant pour boucler sur la liste et avoir chaque question
	public void repondreQuestion(int questionId, String reponse, int applicantId); 
	////Resultat
	//public float resultatTest(int applicantId); //boucle sur les questions et update dans l'applicantFile
	/////lettre d'embauche, voir s'il faut ajouter un fichier donc une classe qui conient filePath et ApplicantId
	/////
	public void EnvoyerApplicantFile(ApplicantFile applicantFile, int applicantId); //condition si lettre signée
	///
	
}
