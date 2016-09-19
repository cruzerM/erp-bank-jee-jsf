package tn.esprit.entities;

import java.util.Date;
import java.util.HashMap;

public class Invoice {

	private Integer invoiceId;
	private Date invoiceDate;
	private String invoiceOwner;
	private String invoiceDescription;
	private Double invoicePrice;
	private Boolean invoiceStatus;

	private Integer quantity;
	private String product;

	private HashMap<String, Integer> items;

	public Invoice() {
		super();
		invoiceDate = new Date();
		invoiceStatus = false;
		items = new HashMap<String, Integer>();
	}

	public Invoice(Integer invoiceId, Date invoiceDate, String invoiceOwner,
			String invoiceDescription, Double invoicePrice) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.invoiceOwner = invoiceOwner;
		this.invoiceDescription = invoiceDescription;
		this.invoicePrice = invoicePrice;
		invoiceStatus = false;
		items = new HashMap<String, Integer>();
	}

	public Invoice(String invoiceOwner, String invoiceDescription,
			Double invoicePrice, HashMap<String, Integer> items) {
		super();
		this.invoiceOwner = invoiceOwner;
		this.invoiceDescription = invoiceDescription;
		this.invoicePrice = invoicePrice;
		this.items = items;
		invoiceDate = new Date();
		invoiceStatus = false;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceOwner() {
		return invoiceOwner;
	}

	public void setInvoiceOwner(String invoiceOwner) {
		this.invoiceOwner = invoiceOwner;
	}

	public String getInvoiceDescription() {
		return invoiceDescription;
	}

	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
	}

	public Double getInvoicePrice() {
		return invoicePrice;
	}

	public void setInvoicePrice(Double invoicePrice) {
		this.invoicePrice = invoicePrice;
	}

	public Boolean getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(Boolean invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public HashMap<String, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<String, Integer> items) {
		this.items = items;
	}
	
	

}
