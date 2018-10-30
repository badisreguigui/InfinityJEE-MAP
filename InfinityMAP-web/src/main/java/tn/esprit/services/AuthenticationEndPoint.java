package tn.esprit.services;


import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.time.ZoneId;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.PIDEVMap.persistence.User;
import tn.esprit.PIDEVMap.services.AuthentificationService;

//Generationdu token
//token jwt : json web token (générer à partir des certaine paramètre)
//Tester dans POSTMan URL /authentification / Post / Body / form url
@Path("authentification")
public class AuthenticationEndPoint {
	
	@EJB
	AuthentificationService local;
	
	
	public static User LoggedPerson;
	@Context
	private UriInfo uriInfo;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	
	// this is a first alternative (with formParam)
	//username et password  --> POSTMAN
	public Response authenticateUser(@QueryParam("username") String username, @QueryParam("password") String password) {
// Authenticate the user using the credentials provided
			
			User user=authenticate(username, password);
			if(user!=null){
			// Issue a token for the user
			String token = issueToken(username);
			// Return the token on the response
			return Response.ok(token).build();}
			else{
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	private User authenticate(String username, String password) {
		User user=local.authentificationUser(username,password);
		if(user!=null){
	LoggedPerson=user;}
		return user;
	}

	private String issueToken(String username) {
		// Issue a token (can be a random String persisted to a database or a JWT token)
		// The issued token must be associated to a user
		// Return the issued token
		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0,keyString.getBytes().length, "DES");

		System.out.println("the key is : " + key.hashCode());
		System.out.println("uriInfo.getAbsolutePath().toString() : " +

				uriInfo.getAbsolutePath().toString());

		System.out.println("Expiration date: " + toDate(LocalDateTime.now().plusMinutes(15L)));
		String jwtToken = Jwts.builder()
				.setSubject(username)
				.setIssuer(uriInfo.getAbsolutePath().toString())
				//La date de la création du token
				.setIssuedAt(new Date())
				.setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
				.signWith(SignatureAlgorithm.HS512, key)
				//pour rendre le token  compresser
				.compact();

		System.out.println("the returned token is : " + jwtToken);
		return jwtToken;
	}

	// ======================================
	// = Private methods =
	// ======================================
	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	public User getLoggedPerson() {
		if(LoggedPerson == null){
			return null ; 
		}
		else{
			return LoggedPerson;
		}

	}
}
