package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rdv {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	private Date rdvDate; 
	private RdvState state;
	
	@ManyToOne
	private Applicant applicant; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getRdvDate() {
		return rdvDate;
	}
	public void setRdvDate(Date rdvDate) {
		this.rdvDate = rdvDate;
	}
	public RdvState getState() {
		return state;
	}
	public void setState(RdvState state) {
		this.state = state;
	} 
	
}
