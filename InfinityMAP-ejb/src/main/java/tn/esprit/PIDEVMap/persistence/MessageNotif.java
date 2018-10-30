package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class MessageNotif implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int messageNotifId;
	
	private String description;
	
	private int seen;
	
	
	@ManyToOne
	@JsonBackReference(value="user")
	private User user;

	public int getMessageNotifId() {
		return messageNotifId;
	}

	public void setMessageNotifId(int messageNotifId) {
		this.messageNotifId = messageNotifId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
