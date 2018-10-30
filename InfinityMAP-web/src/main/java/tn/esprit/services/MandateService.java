package tn.esprit.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.enterprise.context.RequestScoped;
import javax.resource.spi.AuthenticationMechanism;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.PIDEVMap.persistence.Mandate;
import tn.esprit.PIDEVMap.persistence.Resource;
import tn.esprit.PIDEVMap.persistence.ResourceRequest;
import tn.esprit.PIDEVMap.persistence.User;
import tn.esprit.PIDEVMap.services.AuthentificationService;
import tn.esprit.PIDEVMap.services.MandateServiceRemote;
import tn.esprit.PIDEVMap.services.ResourceRequestServiceLocal;
import tn.esprit.filters.Secured;

@Path("/mandate")
public class MandateService {

	@EJB
	AuthentificationService local;
	
	@EJB
	MandateServiceRemote mandate;
	AuthenticationEndPoint e;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("affecterResourceMandat")
	public String addMandate(@QueryParam(value = "requestId")int requestId){
		return mandate.affecterMandateAResource(requestId);
	}
    @Secured
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ListeMandats")
	public List<Mandate>afficherListesMandats(){
      User LoggedPerson=local.getLoggedPerson();
    	if(LoggedPerson.getRole().equals("Admin"))
    		return null;
		return mandate.afficherListesMandats();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ListeMandatsParDate")
	public List<Mandate> afficherListesMandatsParDate(@QueryParam(value ="dateDeb")String dateDeb,@QueryParam(value ="dateFin")String dateFin){
		
	
		
		return mandate.SearchMandate(dateDeb,dateFin);
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
		
		mandate.GererMandat(mandateId);
		return "Mandate delete succeded";
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("alerterFinMandat")
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
	@Path("calculDistance")
	public double calculDistance(@QueryParam(value = "resourceId")int resourceId,@QueryParam(value = "clientId")int clientId){
	return mandate.GPS(resourceId,clientId);
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/UpdateMandat")
	public Mandate updateMandat(Mandate m,@QueryParam(value = "mandateId")int mandatId) {
		m.setMandateId(mandatId);
		
		return mandate.modifierMandat(m,mandatId);
	}
}



