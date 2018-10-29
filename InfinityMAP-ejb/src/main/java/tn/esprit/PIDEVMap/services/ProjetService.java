package tn.esprit.PIDEVMap.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.PIDEVMap.persistence.Projet;

@Stateless
public class ProjetService implements ProjetServiceLocal{

	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public int ajouterProjet(Projet projet) {
		entityManager.persist(projet);
		return projet.getId();
	}

	@Override
	public Projet modifierProjet(Projet projet,int idprojet) {
		Projet p= entityManager.find(Projet.class, idprojet);
		p.setId(projet.getId());
		if(projet.getStatut() != null)
			{
				p.setStatut(projet.getStatut());
			}
		
		return p;
		
	}

	@Override
	
	public void supprimerProjet(int idprojet) {
		
		entityManager.createQuery("Update Projet p set p.etat = 'not available' where p.id="+idprojet).executeUpdate();
	}
	
	
	@Override
	public Projet findProjetById(int projetId) {
		return entityManager.find(Projet.class, projetId);
	}

	@Override
	public List<Projet> findAllProjets() {
		Query query=entityManager.createQuery("SELECT p FROM Projet p ",Projet.class );
		return query.getResultList();
	}

	@Override
	public void affecterProjetAClient(int projetId, int clientId) {
		
		Client clientEntityManager=entityManager.find(Client.class, clientId);
		Projet projetEntityManager=entityManager.find(Projet.class, projetId);
		projetEntityManager.setClient(clientEntityManager);
	}

	@Override
	public List<Projet> findListPByIdClient(int idclient) {
		Query query=entityManager.createQuery("SELECT p FROM Projet p WHERE p.client.id=:id ",Projet.class );
		query.setParameter("id", idclient);
		return query.getResultList();
	}

	@Override
	public long NbrPRojetParClient(int idclient) {
		String sql = "SELECT COUNT(p.id) FROM Projet p WHERE p.client.id=:id";
		Query query = entityManager.createQuery(sql);
		query.setParameter("id", idclient);
		long count=(long)query.getSingleResult();
		System.out.println(count);
		return count;
	}

	@Override
	public long NbrProjetsTotal() {
		String sql = "SELECT COUNT(p.id) FROM Projet p WHERE p.etat='available'";
		Query query = entityManager.createQuery(sql);
		long count=(long)query.getSingleResult();
		System.out.println(count);
		return count;
	}

	@Override
	public float statProjet(int idclient) {
		
		return (NbrPRojetParClient(idclient)*100)/NbrProjetsTotal();
		
	}
}
