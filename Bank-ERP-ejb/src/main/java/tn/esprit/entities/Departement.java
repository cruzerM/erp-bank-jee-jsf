package tn.esprit.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;


@Entity
public class Departement implements Serializable {

	
	private Integer id;
	private String name;
	private Employee manager;
	
	private static final long serialVersionUID = 1L;

	private  List<Employee> employes;
	private List<Job> jobs;
	
	public Departement() {
		
	} 
	
	

	@Override
	public String toString() {
		return "Departement [id=" + id + ", name=" + name + ", manager="
				+ manager + "]";
	}



	public Departement(Integer id, String name, Employee manager) {
		super();
		this.id = id;
		this.name = name;
		this.manager = manager;
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

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany (mappedBy="departement")
	public List<Employee> getEmployes() {
		return employes;
	}
	public void setEmployes(List<Employee> employes) {
		this.employes = employes;
	}
	
	@ManyToOne
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
   
	@OneToMany (mappedBy="departement")
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
}
