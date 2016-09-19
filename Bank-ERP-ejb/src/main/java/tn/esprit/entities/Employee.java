package tn.esprit.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee extends User implements Serializable {

	private String employementStat;
	private String workEmail;
	private Date startDay;
	private Date finishDay;
	private String reasonOfleave;
	private Integer numberOfchildren;
	private String familyStatus;
	private Integer category;
	private Boolean mutual;
	private Boolean syndicate;

	private Employee manager;
	private Departement departement;
	private List<JobUser> jobusers;
	private List<Interview> interviews;

	private List<Leave> leaves;

	private Account account;
	
	private PayRoll payRoll;

	private static final long serialVersionUID = 1L;

	public Employee() {
		super();
		interviews = new ArrayList<Interview>();
	}

	public Employee(String employementStat, String workEmail, Date startDay,
			Date finishDay, String reasonOfleave, Integer numberOfchildren,
			String familyStatus, Integer category, Boolean mutual,
			Boolean syndicate, Employee manager, Departement departement,
			List<JobUser> jobusers, List<Interview> interviews,
			List<Leave> leaves, Account account) {

		this.employementStat = employementStat;
		this.workEmail = workEmail;
		this.startDay = startDay;
		this.finishDay = finishDay;
		this.reasonOfleave = reasonOfleave;
		this.numberOfchildren = numberOfchildren;
		this.familyStatus = familyStatus;
		this.category = category;
		this.mutual = mutual;
		this.syndicate = syndicate;
		this.manager = manager;
		this.departement = departement;
		this.jobusers = jobusers;
		this.interviews = interviews;
		this.leaves = leaves;
		this.account = account;
	}

	@Override
	public String toString() {
		return "Employee [employementStat=" + employementStat + ", workEmail="
				+ workEmail + ", startDay=" + startDay + ", finishDay="
				+ finishDay + ", reasonOfleave=" + reasonOfleave
				+ ", numberOfchildren=" + numberOfchildren + ", familyStatus="
				+ familyStatus + ", category=" + category + ", mutual="
				+ mutual + ", syndicate=" + syndicate + "]";
	}

	public Integer getNumberOfchildren() {
		return numberOfchildren;
	}

	public void setNumberOfchildren(Integer numberOfchildren) {
		this.numberOfchildren = numberOfchildren;
	}

	public String getFamilyStatus() {
		return familyStatus;
	}

	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Boolean getMutual() {
		return mutual;
	}

	public void setMutual(Boolean mutual) {
		this.mutual = mutual;
	}

	public Boolean getSyndicate() {
		return syndicate;
	}

	public void setSyndicate(Boolean syndicate) {
		this.syndicate = syndicate;
	}

	public String getEmployementStat() {
		return employementStat;
	}

	public void setEmployementStat(String employementStat) {
		this.employementStat = employementStat;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	@Temporal(TemporalType.DATE)
	public Date getStartDay() {
		return this.startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	@Temporal(TemporalType.DATE)
	public Date getFinishDay() {
		return this.finishDay;
	}

	public void setFinishDay(Date finishDay) {
		this.finishDay = finishDay;
	}

	public String getReasonOfleave() {
		return this.reasonOfleave;
	}

	public void setReasonOfleave(String reasonOfleave) {
		this.reasonOfleave = reasonOfleave;
	}

	@ManyToOne
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	@ManyToOne
	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@OneToMany(mappedBy = "employee")
	public List<JobUser> getJobusers() {
		return jobusers;
	}

	public void setJobusers(List<JobUser> jobusers) {
		this.jobusers = jobusers;
	}

	@ManyToMany(mappedBy = "employees")
	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	@OneToMany(mappedBy = "employee")
	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@OneToOne(mappedBy="employee")
	public PayRoll getPayRoll() {
		return payRoll;
	}

	public void setPayRoll(PayRoll payRoll) {
		this.payRoll = payRoll;
	}

}
