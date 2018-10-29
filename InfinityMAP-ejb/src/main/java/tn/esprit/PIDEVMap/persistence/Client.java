package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity

public class Client implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String logo;
	private CategorieClient categorie;
	private TypeClient typeClient;
	private String etat="available";
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="client",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JsonIgnore
	private List<Projet> projets;

	
	
	public Client(String nom, String logo, CategorieClient categorie, TypeClient typeClient) {
		
		this.nom = nom;
		this.logo = logo;
		this.categorie = categorie;
		this.typeClient = typeClient;
	}
	public List<Projet> getProjets() {
		return projets;
	}
	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}
	
	public Client() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getNom() {
		return this.nom;
	}
	
	public void addProjet(Projet projet)
	{
		projet.setClient(this);
		this.projets.add(projet);
		
	}

	public void setNom(String nom) {
		this.nom = nom;
	}   
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}   
	public CategorieClient getCategorie() {
		return this.categorie;
	}

	public void setCategorie(CategorieClient categorie) {
		this.categorie = categorie;
	}   
	public TypeClient getTypeClient() {
		return this.typeClient;
	}

	public void setTypeClient(TypeClient typeClient) {
		this.typeClient = typeClient;
	}
	
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
   
}
