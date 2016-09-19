package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ConsumerPropositionPk implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int consumerFk;
	private int propositionFk;
	
	

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + consumerFk;
		result = prime * result + propositionFk;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsumerPropositionPk other = (ConsumerPropositionPk) obj;
		if (consumerFk != other.consumerFk)
			return false;
		if (propositionFk != other.propositionFk)
			return false;
		return true;
	}
	public ConsumerPropositionPk() {
		super();
	}
	public int getConsumerFk() {
		return consumerFk;
	}
	public void setConsumerFk(int consumerFk) {
		this.consumerFk = consumerFk;
	}
	public int getPropositionFk() {
		return propositionFk;
	}
	public void setPropositionFk(int propositionFk) {
		this.propositionFk = propositionFk;
	}
	
}
