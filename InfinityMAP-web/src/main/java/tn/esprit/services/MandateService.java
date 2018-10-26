package tn.esprit.services;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.PIDEVMap.services.MandateServiceRemote;


@Path("/MandateService")
@RequestScoped
public class MandateService {
	@EJB
	MandateServiceRemote mandateService;
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return mandateService.test();
	}
}
