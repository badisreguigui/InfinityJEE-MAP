package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class LigneSkill implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	@JsonProperty("value")
	protected String value;
	@JsonProperty("Resource")
	protected int idResource;
	
	public LigneSkill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LigneSkill(String value, int idResource) {
		super();
		this.value = value;
		this.idResource = idResource;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getIdResource() {
		return idResource;
	}
	public void setIdResource(int idResource) {
		this.idResource = idResource;
	}
	
	
	
	
}
