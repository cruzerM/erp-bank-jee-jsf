package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PayRoll implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private Double bonus;
	private Double allowance;
	private Double syndicateSubscription;
	private Double cnssSubscription;
	private Double insurenceSubscription;
	private Double otherDeductions;
	private Double tax;
	private Double performanceBonus;
	private Double additionalSalary; // 13th and 14th month
	private Double amountPlus;
	private Double amountMinus;
	private Double netSalary;
	private Employee employee; //amountPlus - amountMinus - tax
	private Date date;

	public PayRoll() {

	}
	
	public PayRoll(Double bonus, Double allowance, Double syndicateSubscription,
			Double cnssSubscription, Double insurenceSubscription,
			Double otherDeductions, Double tax, Double performanceBonus,
			Double additionalSalary) {
		super();
		this.bonus = bonus;
		this.allowance = allowance;
		this.syndicateSubscription = syndicateSubscription;
		this.cnssSubscription = cnssSubscription;
		this.insurenceSubscription = insurenceSubscription;
		this.otherDeductions = otherDeductions;
		this.tax = tax;
		this.performanceBonus = performanceBonus;
		this.additionalSalary = additionalSalary;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getAllowance() {
		return allowance;
	}

	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}

	public Double getSyndicateSubscription() {
		return syndicateSubscription;
	}

	public void setSyndicateSubscription(Double syndicateSubscription) {
		this.syndicateSubscription = syndicateSubscription;
	}

	public Double getCnssSubscription() {
		return cnssSubscription;
	}

	public void setCnssSubscription(Double cnssSubscription) {
		this.cnssSubscription = cnssSubscription;
	}

	public Double getInsurenceSubscription() {
		return insurenceSubscription;
	}

	public void setInsurenceSubscription(Double insurenceSubscription) {
		this.insurenceSubscription = insurenceSubscription;
	}

	public Double getOtherDeductions() {
		return otherDeductions;
	}

	public void setOtherDeductions(Double otherDeductions) {
		this.otherDeductions = otherDeductions;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getPerformanceBonus() {
		return performanceBonus;
	}

	public void setPerformanceBonus(Double performanceBonus) {
		this.performanceBonus = performanceBonus;
	}

	public Double getAdditionalSalary() {
		return additionalSalary;
	}

	public void setAdditionalSalary(Double additionalSalary) {
		this.additionalSalary = additionalSalary;
	}

	public Double getAmountPlus() {
		return amountPlus;
	}

	public void setAmountPlus(Double amountPlus) {
		this.amountPlus = amountPlus;
	}

	public Double getAmountMinus() {
		return amountMinus;
	}

	public void setAmountMinus(Double amountMinus) {
		this.amountMinus = amountMinus;
	}

	public Double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}

	@OneToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
