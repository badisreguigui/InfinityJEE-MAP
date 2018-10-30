package tn.esprit.services;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import tn.esprit.PIDEVMap.persistence.Skills;
import tn.esprit.PIDEVMap.services.SkillServiceLocale;

@Path("/SkillsService")
@RequestScoped
public class SkillService {
	@EJB
	SkillServiceLocale skillService ;

	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addSkill")
	public String addSkill(Skills s) {
		skillService.addSkill(s);
		return "added";
	}

	@GET
	@javax.ws.rs.Produces(MediaType.TEXT_PLAIN)
	@Path("/deleteSkill/{id}")
	public void DeleteSkill(@PathParam(value="id") int skillId) {
		skillService.DeleteSkill(skillId);
		
	}

	@PUT
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/updateSkill/{id}")
	public Skills updateSkill(Skills s, @PathParam(value="id") int skillId) {
		s.setId(skillId);
		return skillService.updateSkill(s, skillId);
	}

	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/getSkills")
	public List<Skills> GetSkills() {
		return skillService.GetSkills();
	}

	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/orderSkillsByResources")
	public List<Object[]> NumberResourcesBySkill() {
		return skillService.ResourcesNumberBySkill();
	}
	


}
