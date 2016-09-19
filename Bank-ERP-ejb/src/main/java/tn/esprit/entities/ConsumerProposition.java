package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ConsumerProposition implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	ConsumerPropositionPk pk;
	
	  @ManyToOne
	  @JoinColumn(insertable = false, updatable = false, name = "ConsumerFk", referencedColumnName = "id")
	   Consumer consumer;
	  
	   @ManyToOne
	   @JoinColumn(insertable = false, updatable = false, name = "PropositionFk", referencedColumnName = "id")
	   Proposition proposition;

	public ConsumerPropositionPk getPk() {
		return pk;
	}

	public void setPk(ConsumerPropositionPk pk) {
		this.pk = pk;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public Proposition getProposition() {
		return proposition;
	}

	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}

	public ConsumerProposition() {
		super();
	}
	   
	   
	   
	   
	   
	
}
