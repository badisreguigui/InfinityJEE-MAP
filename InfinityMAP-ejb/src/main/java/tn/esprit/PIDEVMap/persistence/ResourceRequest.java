package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity

public class ResourceRequest implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requestId;
	@Temporal(TemporalType.DATE)
	@JsonProperty("depotDate")
	private Date depotDate;
	@JsonProperty("depotHour")
	private int depotHour;
	@JsonProperty("mandateStartDate")
	@Temporal(TemporalType.DATE)
	private Date mandateStartDate;

	@JsonProperty("mandateEndDate")
	@Temporal(TemporalType.DATE)
	private Date mandateEndDate;
	@JsonProperty("requirements")
	private String requirements;
	@JsonProperty("searchedProfile")
	private String searchedProfile;
	@JsonProperty("yearsOfExperience")
	private int yearsOfExperience;
	@JsonProperty("EducationScolarity")
	private String EducationScolarity;

	@JsonProperty("projet")
	@ManyToOne
	private Projet project;
	

	@ManyToOne
	@JsonProperty("client")
	private Client client;

	@JsonProperty("Director")
	private String Director;
	@JsonProperty("Title") 
	private String Title;
	
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	@JsonManagedReference(value = "request")
	@OneToMany(mappedBy = "request", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<Mandate> ListMandats;

	

	
	public Set<Mandate> getListMandats() {
		return ListMandats;
	}

	public void setListMandats(Set<Mandate> listMandats) {
		ListMandats = listMandats;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public Date getDepotDate() {
		return depotDate;
	}

	public void setDepotDate(Date depotDate) {
		this.depotDate = depotDate;
	}

	public int getDepotHour() {
		return depotHour;
	}

	public void setDepotHour(int depotHour) {
		this.depotHour = depotHour;
	}

	public Date getMandateStartDate() {
		return mandateStartDate;
	}

	public void setMandateStartDate(Date mandateStartDate) {
		this.mandateStartDate = mandateStartDate;
	}

	public Date getMandateEndDate() {
		return mandateEndDate;
	}

	public void setMandateEndDate(Date mandateEndDate) {
		this.mandateEndDate = mandateEndDate;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getSearchedProfile() {
		return searchedProfile;
	}

	public void setSearchedProfile(String searchedProfile) {
		this.searchedProfile = searchedProfile;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getEducationScolarity() {
		return EducationScolarity;
	}

	public void setEducationScolarity(String educationScolarity) {
		EducationScolarity = educationScolarity;
	}

	
	public Projet getProject() {
		return project;
	}

	public void setProject(Projet project) {
		this.project = project;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public ResourceRequest(int requestId, Date depotDate, int depotHour, Date mandateStartDate, Date mandateEndDate,
			String requirements, String searchedProfile, int yearsOfExperience, String educationScolarity,
			Projet project, String director) {
		super();
		this.requestId = requestId;
		this.depotDate = depotDate;
		this.depotHour = depotHour;
		this.mandateStartDate = mandateStartDate;
		this.mandateEndDate = mandateEndDate;
		this.requirements = requirements;
		this.searchedProfile = searchedProfile;
		this.yearsOfExperience = yearsOfExperience;
		this.EducationScolarity = educationScolarity;
		this.project = project;
		this.Director = director;
	}

	public ResourceRequest() {
		super();
	}

	@Override
	public String toString() {
		return "ResourceRequest [requestId=" + requestId + ", depotDate=" + depotDate + ", depotHour=" + depotHour
				+ ", mandateStartDate=" + mandateStartDate + ", mandateEndDate=" + mandateEndDate + ", requirements="
				+ requirements + ", searchedProfile=" + searchedProfile + ", yearsOfExperience=" + yearsOfExperience
				+ ", EducationScolarity=" + EducationScolarity + ", Director=" + Director + ", Title=" + Title + "]";
	}

	
}
