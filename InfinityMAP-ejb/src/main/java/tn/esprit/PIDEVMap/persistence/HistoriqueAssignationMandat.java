package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class HistoriqueAssignationMandat implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int HistoriqueId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date HeureSauvegarde;

	public Date getHeureSauvegarde() {
		return HeureSauvegarde;
	}
	public void setHeureSauvegarde(Date heureSauvegarde) {
		HeureSauvegarde = heureSauvegarde;
	}
	private String etatMandat;
	
	@OneToMany(mappedBy="historique")
	private List<Mandate>mandates;
	
	
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
	public List<Mandate> getMandates() {
		return mandates;
	}
	public void setMandates(List<Mandate> mandates) {
		this.mandates = mandates;
	}
	
	
	
	
}
