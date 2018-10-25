package tn.esprit.PIDEVMap.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
public class Mandate implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int MandateId;
	
	@ManyToOne
	private ResourceRequest request;
	
	@ManyToOne
	private Resource resource;

	
	@ManyToOne
	private HistoriqueAssignationMandat historique;
	
	
	public int getMandateId() {
		return MandateId;
	}
	@XmlAttribute(name="MandateId",required=true)
	public void setMandateId(int mandateId) {
		MandateId = mandateId;
	}

	public ResourceRequest getRequest() {
		return request;
	}
	public void setRequest(ResourceRequest request) {
		this.request = request;
	}

	public HistoriqueAssignationMandat getHistorique() {
		return historique;
	}
	public void setHistorique(HistoriqueAssignationMandat historique) {
		this.historique = historique;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
}
