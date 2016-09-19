package tn.esprit.entities;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Init;
import javax.enterprise.context.Initialized;
import javax.enterprise.inject.Default;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Proposition  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String proposition;
	 @ManyToOne
	 Question question;
	 
	 @OneToMany(mappedBy="proposition",fetch = FetchType.EAGER,cascade={CascadeType.REMOVE,CascadeType.MERGE})
	 List<ConsumerProposition>consumerPropositions;

	 @Column(name="flag", columnDefinition="Decimal(1) default '0'")
	 int flag;
	 
	 
	public Proposition(String proposition, Question question,
			List<ConsumerProposition> consumerPropositions, int flag) {
		super();
		this.proposition = proposition;
		this.question = question;
		this.consumerPropositions = consumerPropositions;
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List<ConsumerProposition> getConsumerPropositions() {
		return consumerPropositions;
	}

	public void setConsumerPropositions(
			List<ConsumerProposition> consumerPropositions) {
		this.consumerPropositions = consumerPropositions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProposition() {
		return proposition;
	}

	public void setProposition(String proposition) {
		this.proposition = proposition;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	

	public Proposition(String proposition, Question question,
			List<ConsumerProposition> consumerPropositions) {
		super();
		this.proposition = proposition;
		this.question = question;
		this.consumerPropositions = consumerPropositions;
		
	}
	 

	public Proposition() {
		super();
	
	}
	  
	 
	 
	
	
}
