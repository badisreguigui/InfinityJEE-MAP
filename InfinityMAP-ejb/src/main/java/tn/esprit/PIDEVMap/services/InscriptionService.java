package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.PIDEVMap.persistence.User;
import tn.esprit.PIDEVMap.utilities.CryptPasswordMD5;
@Stateless
public class InscriptionService implements InscriptionServiceRemote {
	@PersistenceContext
	private EntityManager em;
	private CryptPasswordMD5 cryptPasswordMD5 = new CryptPasswordMD5();
	@Override
	public String Inscription(User u) {
		
		em.persist(u);
	u.setPassword(cryptPasswordMD5.cryptWithMD5(u.getPassword()));
	return u.getRole();
	}
	@Override
	// THIS FUNCTION IS NOT OVER NEED TO BE FINISHED 
	public List<String> getMyNotifs() {
		List<String>AllResources;
		 TypedQuery<String> query=
				 em.createQuery("Select m.description from MessageNotif m where m.seen=0 and m.user=(select u from User u where userId=1)",String.class);
		 
		 AllResources=query.getResultList();
		if(!AllResources.isEmpty())
		{
			 em.createQuery("UPDATE MessageNotif set seen=1 where user=(select u from User u where userId=1)").executeUpdate();

		}
		 return AllResources;
		 
	}

}
