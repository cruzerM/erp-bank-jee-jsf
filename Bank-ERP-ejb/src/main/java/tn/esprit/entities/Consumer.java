package tn.esprit.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Consumer implements Serializable {
	
	
  
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String nomprenom;
	
	String mail;
	
	
	@OneToMany(mappedBy="consumer")
	 List<ConsumerProposition> consumerPropositions;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomprenom() {
		return nomprenom;
	}


	public void setNomprenom(String nomprenom) {
		this.nomprenom = nomprenom;
	}


	


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	


	public Consumer(String nomprenom , String mail,
			List<ConsumerProposition> consumerPropositions) {
		super();
		this.nomprenom = nomprenom;	
		this.mail = mail;
		this.consumerPropositions = consumerPropositions;
	}
	public Consumer() {
		super();
	
	}
	
	
}
