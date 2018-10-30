package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Mandate implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int MandateId;
	@JsonProperty("dateEndMandate")
	@Temporal(TemporalType.DATE)
	private Date date_end_mandate;
	@JsonProperty("dateStartMandate")
	@Temporal(TemporalType.DATE)
	private Date date_start_mandate;
	private String NomMandat;

	@ManyToOne
	@JsonBackReference(value="request")
	private ResourceRequest request;
	private float Facture;
	@ManyToOne
	@JsonIgnore
	@JsonBackReference(value="resource")
	private Resource resource;
	@ManyToOne
	@JsonBackReference
	private HistoriqueAssignationMandat historique;
	public int getMandateId() {
		return MandateId;
	}
	public void setMandateId(int mandateId) {
		MandateId = mandateId;
	}
	
	public ResourceRequest getRequest() {
		return request;
	}
	public void setRequest(ResourceRequest request) {
		this.request = request;
	}
	@JsonBackReference
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public HistoriqueAssignationMandat getHistorique() {
		return historique;
	}
	public void setHistorique(HistoriqueAssignationMandat historique) {
		this.historique = historique;
	}
	public Date getDate_end_mandate() {
		return date_end_mandate;
	}
	public void setDate_end_mandate(Date date_end_mandate) {
		this.date_end_mandate = date_end_mandate;
	}
	public Date getDate_start_mandate() {
		return date_start_mandate;
	}
	public void setDate_start_mandate(Date date_start_mandate) {
		this.date_start_mandate = date_start_mandate;
	}
	public String getNomMandat() {
		return NomMandat;
	}
	public void setNomMandat(String nomMandat) {
		NomMandat = nomMandat;
	}
	public float getFacture() {
		return Facture;
	}
	public void setFacture(float facture) {
		Facture = facture;
	}
	@Override
	public String toString() {
		return "Mandate [MandateId=" + MandateId + ", date_end_mandate=" + date_end_mandate + ", date_start_mandate="
				+ date_start_mandate + ", NomMandat=" + NomMandat + ", Facture=" + Facture
				+ "]";
	}

	
}
