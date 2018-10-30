package tn.esprit.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.PIDEVMap.persistence.Client;
import tn.esprit.PIDEVMap.services.ClientServiceLocal;

@Path("/ClientService")
@RequestScoped
public class ClientService {
	@EJB
	ClientServiceLocal remote;
	
	//ajouter employe
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Path("addClient")
		public javax.ws.rs.core.Response ajouterClient(Client e)
		{
			//test sur le nom de limage 
			
			Pattern p = Pattern.compile("([^\\s]+(.jpg|.png|.gif|.bmp))");
			String logo = e.getLogo();
			Matcher m = p.matcher(logo);
			if (m.find())
			{
			  System.out.printf("The filename is '%s'%n", m.group(1));
			  remote.ajouterClient(e);
				return javax.ws.rs.core.Response.ok("ajoute avec succes").build();
			}
			else 
			{
	            System.out.println("NO MATCH");
	            return javax.ws.rs.core.Response.status(Status.NOT_ACCEPTABLE).build();
	        }
			/*String pattern = " (([\\s])+(.png|.jpg|.gif))";
	        
	        System.out.println(logo);
	        if (logo.matches(pattern)) {
	            System.out.println(logo + " matches \"" + pattern + "\"");
	        } else {
	            System.out.println("NO MATCH");
	            return javax.ws.rs.core.Response.status(Status.NOT_ACCEPTABLE).build();
	        }*/
			
		}
		
	
		
		//modifier client
	    @PUT
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("modifierClient/{clientId}")
	    public javax.ws.rs.core.Response updateStudent(@PathParam("clientId") int id, Client client) throws Exception {
	    	client.setId(id);
	    	if(remote.findClientById(id)== null )
	    	{
	    		 return javax.ws.rs.core.Response.ok("le client que vous voulez modifier n'existe pas ").build();
	    	}
	    	else
	    	{	
	    		
	    	
	    		System.out.println("id client= "+client.getId());
	    		System.out.println("nom client= "+client.getNom());
	    		//System.out.println("logo= "+client.getLogo());
	    		//System.out.println("type client= "+client.getCategorie());
	    		//System.out.println("etat client= "+client.getEtat());
	    		if(client.getEtat().equals("not available"))
	    		{
	    			return   javax.ws.rs.core.Response.ok("le client que vous vous voulez modifier est supprimé").build();
	    		}
	    		
	    			remote.modifierClient(client,id);
	    		
	    		System.out.println("baed modfi");
	    		System.out.println("id client= "+client.getId());
		    	System.out.println("nom client= "+client.getNom());
		    	//System.out.println("logo= "+client.getLogo());
		    	//System.out.println("type client= "+client.getCategorie());
		    	//System.out.println("etat client= "+client.getEtat());
	    		 return   javax.ws.rs.core.Response.ok("client modifié avec succes").build();
	    	}
	    }
	    
	    
	    
	   //supprimer client
	    @PUT
	    @Path("supprimerClient/{clientId}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response supprimerClient(@PathParam("clientId") int idclient, Client client) throws Exception {
	    	client=remote.findClientById(idclient);
	    	 if(client==null)
	    	 {
	    		 return javax.ws.rs.core.Response.ok("le client que vous voulez modifier n'existe pas ").build();
	    	 }
	    	 else
	    	 if(client.getEtat().equals("not available"))
	    	 {
	    		 return javax.ws.rs.core.Response.ok("client deja supprime").build();
	    	 }
	    	 remote.supprimerClient( idclient);
	    	 return javax.ws.rs.core.Response.ok("supprime avecc succes").build();
			
	    	
	    }
	    
	    
	    
	    //afficher liste des clients
	    @Path("afficherAllClients")
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getAllClients()
	    {
	    
	    	return Response.status(Status.ACCEPTED).entity(remote.findAllClients()).build()  ;
	    }
	    
	    
	    
	    //recherche de client par son nom
	    @GET
	    @Path("rechercheClient/{nomclient}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response search(@PathParam("nomclient") String nom) {

			if (remote.rechercheClient(nom) != null)
				return Response.status(Status.ACCEPTED).entity(remote.rechercheClient(nom)).build();
			else
				return Response.status(Status.NOT_FOUND).build();
		}
    	
}
