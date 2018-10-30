package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.PIDEVMap.persistence.Client;

@Stateless
public class ClientService implements ClientServiceLocal{

	@PersistenceContext
	EntityManager entityManager;
	



	
	//ajouter client
	@Override
	public int ajouterClient(Client client) {
		entityManager.persist(client);
		return client.getId();
	}
	

	//supprimer client ( changer etat client )
	@Override
	public void supprimerClient(int clientId) {
    	entityManager.createQuery("Update Client p set p.etat = 'not available' where p.id="+clientId).executeUpdate();

	}


	//modifier client 
	@Override
	public Client modifierClient(Client client,int idclient) {
		
		Client c= entityManager.find(Client.class, idclient);
		c.setId(client.getId());
		if(client.getNom() != null)	
		{
			c.setNom(client.getNom());
		}
		if(client.getLogo() != null)	
		{
			c.setLogo(client.getLogo());
		}
		if(client.getCategorie()!= null )
		{
			c.setCategorie(client.getCategorie());		 
		}
		if(client.getTypeClient() != null)
		{
			c.setTypeClient(client.getTypeClient());
		}
		
		return c;
	}
	

	///aficcher la lsite des clients
	@Override
	public List<Client> findAllClients() {
		
		Query query=entityManager.createQuery("SELECT c FROM Client c WHERE c.etat='available' ",Client.class );
		return query.getResultList();
	}


	@Override
	public Client findClientById(int clientId) {
		return entityManager.find(Client.class, clientId);
	}


	@Override
	public List<Client> rechercheClient(String nom) {
		Query query = entityManager.createQuery("select c from Client c where c.nom like :nclient");
		query.setParameter("nclient", "%"+nom+"%");
		return query.getResultList(); 
	}
}
