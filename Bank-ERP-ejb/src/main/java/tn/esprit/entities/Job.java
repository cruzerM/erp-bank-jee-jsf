package tn.esprit.entities;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;


@Entity

public class Job implements Serializable {

	
	
	private Integer id;
	private String name;
	private String level;
	private Double salary;
	private Boolean status;
	
	
	private Departement departement;
	private List<JobUser> jobusers;
	private List<Candidate> candidates;

	private static final long serialVersionUID = 1L;

	public Job() {
		status = true;
	}   
	


	public Job(String name) {
		super();
		this.name = name;
	}



	public Job(Integer id, String name, String level, Double salary,
			Boolean status, Departement departement, List<Candidate> candidates) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.salary = salary;
		this.status = status;
		this.departement = departement;
		this.candidates = candidates;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", level=" + level
				+ ", salary=" + salary + ", status=" + status
				+ ", departement=" + departement + "]";
	}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}
	
	
	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public void setName(String name) {
		this.name = name;
	}   
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}   
	public Double getSalary() {
		return this.salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}  
	
	@ManyToOne
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@OneToMany(mappedBy="job")
	public List<JobUser> getJobusers() {
		return jobusers;
	}
	public void setJobusers(List<JobUser> jobusers) {
		this.jobusers = jobusers;
	}
	

	@OneToMany(mappedBy = "job")
	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
   
}
