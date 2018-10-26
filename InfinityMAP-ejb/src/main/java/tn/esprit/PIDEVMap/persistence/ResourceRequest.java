package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@Entity

public class ResourceRequest implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requestId;
	@JsonProperty("depotDate")
	private Date depotDate;
	@JsonProperty("depotHour")
	private int depotHour;
	@JsonProperty("mandateStartDate")
	private Date mandateStartDate;
	@JsonProperty("mandateEndDate")
	private Date mandateEndDate;
	@JsonProperty("requiremenets")
	private String requirements;
	@JsonProperty("searchedProfile")
	private String searchedProfile;
	@JsonProperty("yearsOfExperience")
	private int yearsOfExperience;
	@JsonProperty("EducationScolarity")
	private String EducationScolarity;
	@JsonProperty("project")
	private Projet project;
	@JsonProperty("Director")
	private String Director;
	

	@JsonProperty("listMandats")
	@OneToMany(mappedBy="request")
	private List<Mandate> listMandats;
	
   
	public List<Mandate> getListMandats() {
		return listMandats;
	}
	public void setListMandats(List<Mandate> listMandats) {
		this.listMandats = listMandats;
	}
	

	
	public int getRequestId()
	{
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
	
	
	
	
}
