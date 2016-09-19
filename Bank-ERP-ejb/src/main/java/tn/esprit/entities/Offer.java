package tn.esprit.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Offer
 *
 */
@Entity

public class Offer implements Serializable {

	
	private Integer id;
	private String name;
	private String description;
	private String items;
	private String convertedItems;
	private Date beginDate;
	private Date endDate;
	private Boolean state;
	private Double amount;
	private String specifications;
	private List<OfferResponse> offerResponses;
	private static final long serialVersionUID = 1L;

	public Offer() {
		
	}  
	
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	@Temporal(TemporalType.DATE)
	public Date getBeginDate() {
		return this.beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	} 
	@Transient
	public String getConvertedItems() {
		return convertedItems;
	}

	public void setConvertedItems(String convertedItems) {
		this.convertedItems = convertedItems;
	}
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}   
	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}   
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}   
	public String getSpecifications() {
		return this.specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	@OneToMany(mappedBy="offer")
	public List<OfferResponse> getOfferResponses() {
		return offerResponses;
	}

	public void setOfferResponses(List<OfferResponse> offerResponses) {
		this.offerResponses = offerResponses;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}
	
   
}
