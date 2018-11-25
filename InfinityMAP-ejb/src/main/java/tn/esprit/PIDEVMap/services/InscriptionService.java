package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.PIDEVMap.persistence.MessageNotif;
import tn.esprit.PIDEVMap.persistence.User;
import tn.esprit.PIDEVMap.utilities.CryptPasswordMD5;

@Stateless
public class InscriptionService implements InscriptionServiceRemote {
	@PersistenceContext
	private EntityManager em;
	private CryptPasswordMD5 cryptPasswordMD5 = new CryptPasswordMD5();

	@Override
	public String Inscription(User u)
	{

		em.persist(u);
		u.setPassword(cryptPasswordMD5.cryptWithMD5(u.getPassword()));
		return u.getRole();
	}

	@Override
	// THIS FUNCTION IS NOT OVER NEED TO BE FINISHED
	public List<MessageNotif> getMyNotifs(int userId) {
		List<MessageNotif> AllResources;
		TypedQuery<MessageNotif> query = em.createQuery(
				"Select m from MessageNotif m where m.seen=0 and m.user=(select u from User u where id=:userId)",
				MessageNotif.class).setParameter("userId",userId);

		AllResources = query.getResultList();
		if (!AllResources.isEmpty()) {
			em.createQuery("UPDATE MessageNotif set seen=1 where user=(select u from User u where id=:userId)").setParameter("userId",userId).executeUpdate();

		}
		return AllResources;

	}

}
