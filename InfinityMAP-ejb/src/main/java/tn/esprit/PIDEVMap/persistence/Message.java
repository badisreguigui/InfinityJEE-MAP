package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int messageId;
	
	private int senderId;
	private Date sentDate;
	private String messageType;
	private String Object;
	private String recieverId;
	
	public int getMessageId()
	{
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getObject() {
		return Object;
	}
	public void setObject(String object) {
		Object = object;
	}
	public String getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}
	public Message() {
		super();
	}
	public Message(int messageId, int senderId, Date sentDate, String messageType, String object, String recieverId) {
		super();
		this.messageId = messageId;
		this.senderId = senderId;
		this.sentDate = sentDate;
		this.messageType = messageType;
		Object = object;
		this.recieverId = recieverId;
	}

	
}
