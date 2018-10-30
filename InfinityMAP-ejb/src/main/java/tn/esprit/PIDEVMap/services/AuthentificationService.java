package tn.esprit.PIDEVMap.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.PIDEVMap.persistence.Client;
import tn.esprit.PIDEVMap.persistence.User;
import tn.esprit.PIDEVMap.utilities.CryptPasswordMD5;

@Stateless
@LocalBean
public class AuthentificationService  {
	public static User LoggedPerson ;
	@PersistenceContext
	private EntityManager em;
	private CryptPasswordMD5 cryptPasswordMD5 = new CryptPasswordMD5();
	
	//@Override
	public User authentificationUser(String login , String password) {
		TypedQuery<User> query = em.createQuery("SELECT p  FROM User p where p.Login=:login",User.class);
	
		query.setParameter("login", login);
		 User person = query.getSingleResult();		
			if((person.getLogin().equals(login))&&(person.getPassword().equals(cryptPasswordMD5.cryptWithMD5(password)))){
				LoggedPerson=person;
				return LoggedPerson;
			}
			else{
				return null;
			}	
	}

	//@Override
	public User getLoggedPerson() {
		if(LoggedPerson == null){
			return null ; 
		}
		else{
			return LoggedPerson;
		}

	}

}
