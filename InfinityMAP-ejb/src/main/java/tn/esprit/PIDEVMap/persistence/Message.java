package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.Date; 
 
import javax.persistence.Entity; 
import javax.persistence.EnumType; 
import javax.persistence.Enumerated; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
 
import com.fasterxml.jackson.annotation.JsonProperty; 
 
@Entity 
public class Message implements Serializable { 
 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int messageId; 
	 
	@JsonProperty("senderId") 
	private int senderId; 
	@JsonProperty("sentDate") 
	private Date sentDate; 
	@JsonProperty("messageType") 
	@Enumerated(EnumType.STRING) 
	private MessageType messageType; 
	@JsonProperty("Object") 
	private String Object; 
	@JsonProperty("recieverId") 
	private int recieverId; 
	@JsonProperty("messageBody") 
	private String messageBody; 
	 
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
	 
	public MessageType getMessageType() { 
		return messageType; 
	} 
	public void setMessageType(MessageType messageType) { 
		this.messageType = messageType; 
	} 
	public String getObject() { 
		return Object; 
	} 
	public void setObject(String object) { 
		Object = object; 
	} 
	public int getRecieverId() { 
		return recieverId; 
	} 
	public void setRecieverId(int recieverId) { 
		this.recieverId = recieverId; 
	} 
	 
	 
	public String getMessageBody() { 
		return messageBody; 
	} 
	public void setMessageBody(String messageBody) { 
		this.messageBody = messageBody; 
	} 
	public Message() { 
		super(); 
	} 
	public Message(int messageId, int senderId, Date sentDate, MessageType messageType, String object, int recieverId)  
	{ 
		super(); 
		this.messageId = messageId; 
		this.senderId = senderId; 
		this.sentDate = sentDate; 
		this.messageType = messageType; 
		this.Object = object; 
		this.recieverId = recieverId; 
	} 
}
 