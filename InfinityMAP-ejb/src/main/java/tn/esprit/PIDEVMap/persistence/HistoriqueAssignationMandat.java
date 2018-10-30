package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class HistoriqueAssignationMandat implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty
	private int HistoriqueId;
	@JsonProperty
	@Temporal(TemporalType.TIMESTAMP)
	private Date HeureSauvegarde;
    
	public Date getHeureSauvegarde() {
		return HeureSauvegarde;
	}
	public void setHeureSauvegarde(Date heureSauvegarde) {
		HeureSauvegarde = heureSauvegarde;
	}
	private String etatMandat;
	@JsonManagedReference
	@OneToMany(mappedBy="historique",fetch=FetchType.EAGER)
	private Set<Mandate>mandates;
	public int getHistoriqueId() {
		return HistoriqueId;
	}
	public void setHistoriqueId(int historiqueId) {
		HistoriqueId = historiqueId;
	}
	
	
	public String getEtatMandat() {
		return etatMandat;
	}
	public void setEtatMandat(String etatMandat) {
		this.etatMandat = etatMandat;
	}
	public Set<Mandate> getMandates() {
		return mandates;
	}
	public void setMandates(Set<Mandate> mandates) {
		this.mandates = mandates;
	}
	
}
