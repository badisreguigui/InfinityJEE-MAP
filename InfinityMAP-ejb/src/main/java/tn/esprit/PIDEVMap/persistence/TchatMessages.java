package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TchatMessages implements Serializable {

	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int senderID;
	private Date sentDate;
	private String msg;
	private int seen;
	
	@ManyToOne
	private TchatDiscussion tchatdiscussion;
	
	
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

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}

	public TchatDiscussion getTchatdiscussion() {
		return tchatdiscussion;
	}

	public void setTchatdiscussion(TchatDiscussion tchatdiscussion) {
		this.tchatdiscussion = tchatdiscussion;
	}

	public TchatMessages(int id, int senderID, Date sentDate, String msg, int seen, TchatDiscussion tchatdiscussion) {
		super();
		this.id = id;
		this.senderID = senderID;
		this.sentDate = sentDate;
		this.msg = msg;
		this.seen = seen;
		this.tchatdiscussion = tchatdiscussion;
	}

	public TchatMessages() {
		super();
	}
	


	
}
