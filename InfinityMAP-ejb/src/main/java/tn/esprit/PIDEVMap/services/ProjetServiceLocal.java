package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.PIDEVMap.persistence.Projet;

@Local
public interface ProjetServiceLocal {
	
	int ajouterProjet(Projet projet);
	 Projet modifierProjet(Projet projet,int idprojet);
	 void supprimerProjet(int idprojet);
	 Projet findProjetById(int projetId);
	 List<Projet> findAllProjets() ;
	 void affecterProjetAClient(int projetId, int clientId) ;
	 List<Projet> findListPByIdClient(int idclient);
	 long NbrPRojetParClient(int idclient);
	 long NbrProjetsTotal();
	 float statProjet(int idclient);

}
