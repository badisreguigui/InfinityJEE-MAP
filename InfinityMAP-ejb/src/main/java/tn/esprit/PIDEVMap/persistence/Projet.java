package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne
	private Client client;
	
	@ManyToMany
	private List<Resource> listResources;

	@ManyToMany
	private List<Skills> listSkills;
	
	@OneToOne(mappedBy="project")
	private ResourceRequest resourceRequest;
	@JsonProperty("projetStartDate")
	@Temporal(TemporalType.DATE)
	private Date projetStartDate;

	@JsonProperty("projetEndDate")
	@Temporal(TemporalType.DATE)
	private Date projetEndDate;
	
	public ResourceRequest getResourceRequest() {
		return resourceRequest;
	}
	public void setResourceRequest(ResourceRequest resourceRequest) {
		this.resourceRequest = resourceRequest;
	}
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
	public Date getProjetStartDate() {
		return projetStartDate;
	}
	public void setProjetStartDate(Date projetStartDate) {
		this.projetStartDate = projetStartDate;
	}
	public Date getProjetEndDate() {
		return projetEndDate;
	}
	public void setProjetEndDate(Date projetEndDate) {
		this.projetEndDate = projetEndDate;
	}   
	
   
}
