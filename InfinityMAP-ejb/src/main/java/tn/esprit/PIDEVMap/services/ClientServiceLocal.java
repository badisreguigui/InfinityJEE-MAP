package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.PIDEVMap.persistence.Client;

@Local
public interface ClientServiceLocal {

	int ajouterClient(Client client);
	void supprimerClient(int clientId) ;
	Client modifierClient(Client client,int idclient);
	List<Client> findAllClients();
	Client findClientById(int clientId);
	List<Client> rechercheClient(String nom) ;
}
