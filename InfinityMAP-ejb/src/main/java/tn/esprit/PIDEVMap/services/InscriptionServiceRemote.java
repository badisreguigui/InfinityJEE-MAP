package tn.esprit.PIDEVMap.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.PIDEVMap.persistence.MessageNotif;
import tn.esprit.PIDEVMap.persistence.User;
@Local
public interface InscriptionServiceRemote {
	public String Inscription(User u);
	 public List<MessageNotif> getMyNotifs(int userId);

}
