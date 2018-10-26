package tn.esprit.PIDEVMap.persistence;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Arrival {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	private Date arrivalDate;

	@OneToOne
	private Applicant applicant;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	} 
}
