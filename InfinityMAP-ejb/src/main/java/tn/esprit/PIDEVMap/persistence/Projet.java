package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Projet
 *
 */
@Entity
public class Projet implements Serializable {

	   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private TypeProjet statut;
	
	@ManyToOne
	private Client client;
	
	@ManyToMany
	private List<Resource> listResources;

	@ManyToMany
	private List<Skills> listSkills;
	
	
	
	
	public Projet(TypeProjet statut) {
		this.setStatut(statut);
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Projet() {
	
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public TypeProjet getStatut() {
		return statut;
	}
	public void setStatut(TypeProjet statut) {
		this.statut = statut;
	}
	public List<Resource> getListResources() {
		return listResources;
	}
	public void setListResources(List<Resource> listResources) {
		this.listResources = listResources;
	}
	public List<Skills> getListSkills() {
		return listSkills;
	}
	public void setListSkills(List<Skills> listSkills) {
		this.listSkills = listSkills;
	}   
	
   
}
