package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class TchatDiscussion implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int senderID;
	private int recieverID;
	
	@OneToMany(mappedBy="tchatdiscussion")
	private List<TchatMessages> listTchatMessages;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSenderID() {
		return senderID;
	}
	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}
	public int getRecieverID() {
		return recieverID;
	}
	public void setRecieverID(int recieverID) {
		this.recieverID = recieverID;
	}
	public TchatDiscussion(int id, int senderID, int recieverID) {
		super();
		this.id = id;
		this.senderID = senderID;
		this.recieverID = recieverID;
	}
	public TchatDiscussion() {
		super();
	}
	
	
	

}
