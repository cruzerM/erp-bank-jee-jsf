package tn.esprit.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Leave
 *
 */
@Entity
@Table(name=("e_leave"))
public class Leave implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="employee_id")
	private Employee employee;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	private Date endDate;
	private boolean isPayed;
	private String type;
	private String comment;
	private static final long serialVersionUID = 1L;

	public Leave() {
		super();
	}   
	

	public Leave(Employee employee, Date startDate, Date endDate,
			boolean isPayed, String type, String comment) {
		super();
		this.employee = employee;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isPayed = isPayed;
		this.type = type;
		this.comment = comment;
	}
	public Leave(int id, Employee employee, Date startDate, Date endDate,
			boolean isPayed, String type, String comment) {
		super();
		this.id=id;
		this.employee = employee;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isPayed = isPayed;
		this.type = type;
		this.comment = comment;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}   
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}   
	public boolean getIsPayed() {
		return this.isPayed;
	}

	public void setIsPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

   
}
