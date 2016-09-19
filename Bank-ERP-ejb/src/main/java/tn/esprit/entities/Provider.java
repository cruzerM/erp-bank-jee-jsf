package tn.esprit.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Provider
 *
 */
@Entity

public class Provider implements Serializable {

	
	private Integer id;
	private String name;
	private String address;
	private Long phone;
	private String email;
	private List<OfferResponse> offerResponses;
	private static final long serialVersionUID = 1L;

	public Provider() {
		
	}  
	public Provider(String name, String address, Long phone, String email) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@OneToMany(mappedBy = "provider")
	public List<OfferResponse> getOfferResponses() {
		return offerResponses;
	}
	public void setOfferResponses(List<OfferResponse> offerResponses) {
		this.offerResponses = offerResponses;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
   
	
}
