package tn.esprit.PIDEVMap.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
