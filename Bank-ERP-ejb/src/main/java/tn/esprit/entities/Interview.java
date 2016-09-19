package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Interview
 *
 */
@Entity
public class Interview implements Serializable {

	private Integer id;
	private String Description;
	private Date date;
	private static final long serialVersionUID = 1L;

	private List<Candidate> candidates;

	private List<Employee> employees;

	public Interview() {
		super();
		candidates = new ArrayList<Candidate>();
		employees = new ArrayList<Employee>();
		date = new Date();
	}

	public Interview(String description) {
		super();
		Description = description;
		candidates = new ArrayList<Candidate>();
		employees = new ArrayList<Employee>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "candidate_interview", joinColumns = { @JoinColumn(name = "I_ID", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "C_ID", referencedColumnName = "id") })
	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employee_interview", joinColumns = { @JoinColumn(name = "I_ID", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "E_ID", referencedColumnName = "id") })
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	
}
