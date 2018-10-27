package tn.esprit.services;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.PIDEVMap.persistence.Mandate;
import tn.esprit.PIDEVMap.persistence.ResourceRequest;
import tn.esprit.PIDEVMap.services.MandateServiceRemote;
import tn.esprit.PIDEVMap.services.ResourceRequestServiceLocal;

@Path("/mandate")
@ManagedBean
public class MandateService {
	@EJB
	MandateServiceRemote mandate;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String test()
	{
	   
	    return mandate.test();
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("affecterResourceMandat")
	public String addMandate(@QueryParam(value = "requestId")int requestId){
		int mandateId=mandate.affecterMandateAResource(requestId);
		return "Affectation succeded";
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ListeMandats")
	public List<Mandate> afficherListesMandats(){
		return mandate.afficherListesMandats();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Recherche")
	public Set<Mandate> rechercherListesMandats(@QueryParam(value = "resourceId")int resourceId){
		return mandate.findAllMandates(resourceId);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("deleteMandate")
	public String deleteMandate(@QueryParam(value = "mandateId")int mandateId){
		
		mandate.deleteMandat(mandateId);
		return "Mandate delete succeded";
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("sendMail")
	public String sendMail(){
		return mandate.AlerteFinMandate();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("calculFacture")
	public float CalculFacture(@QueryParam(value = "mandateId")int mandateId,@QueryParam(value = "resourceId")int resourceId)
	{
		return mandate.calculFactureMandat(mandateId, resourceId);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("calculFactureTotal")
	public float CalculFactureTotale(@QueryParam(value = "resourceId")int resourceId)
	{
		return mandate.calculTotalMandat(resourceId);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("localisation")
	public String geolocalisation(){
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://api.ipstack.com/134.201.250.155?access_key=6bac93a513e7d21a4536fd82ec523554&Scallback = MY_FUNCTION")
		  .request(MediaType.TEXT_PLAIN_TYPE)
		  .header("Accept", "application/json")
		  .get();
String result =response.readEntity(String.class);
	return result;
	}
}  


