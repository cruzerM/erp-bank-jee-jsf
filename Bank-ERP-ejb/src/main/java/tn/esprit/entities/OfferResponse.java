package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tn.esprit.enumerators.OfferResponseState;

@Entity
public class OfferResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Offer offer;
	private Provider provider;
	private OfferResponsePK offerResponsePK;
	private Date postedDate;
	private Double amount;
	private String specifications;
	private OfferResponseState state;
	private String notes;
	
	public OfferResponse() {
		super();
	}
	
	public OfferResponse(Offer offer, Provider provider,
			OfferResponsePK offerResponsePK, Date postedDate, Double amount,
			String specifications, OfferResponseState state, String notes) {
		super();
		this.offer = offer;
		this.provider = provider;
		this.offerResponsePK = offerResponsePK;
		this.postedDate = postedDate;
		this.amount = amount;
		this.specifications = specifications;
		this.state = state;
		this.notes = notes;
	}
	
	public OfferResponse(Offer offer, Provider provider, Date postedDate, Double amount,
			String specifications, OfferResponseState state, String notes){
		this.offer = offer;
		this.provider = provider;
		this.offerResponsePK = new OfferResponsePK(provider.getId(), offer.getId());
		this.postedDate = postedDate;
		this.amount = amount;
		this.specifications = specifications;
		this.state = state;
		this.notes = notes;
	}

	@ManyToOne
	@JoinColumn(name="offerId",referencedColumnName="id",insertable=false,updatable=false)
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	@ManyToOne
	@JoinColumn(name="providerId",referencedColumnName="id",insertable=false,updatable=false)
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	@EmbeddedId
	public OfferResponsePK getOfferResponsePK() {
		return offerResponsePK;
	}
	public void setOfferResponsePK(OfferResponsePK offerResponsePK) {
		this.offerResponsePK = offerResponsePK;
	}
	@Temporal(TemporalType.DATE)
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public OfferResponseState getState() {
		return state;
	}
	public void setState(OfferResponseState state) {
		this.state = state;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

}
