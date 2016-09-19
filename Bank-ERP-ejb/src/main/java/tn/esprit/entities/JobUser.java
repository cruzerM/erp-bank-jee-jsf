package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import tn.esprit.entities.JobUserPK;


@Entity
public class JobUser implements Serializable {

	
	private Date beginDate;
	private Date endDate;
	private JobUserPK jobUserPK;
	private Job job;
	private Employee employee;
	
	private static final long serialVersionUID = 1L;

	public JobUser() {
		super();
	}   
	
	
	public JobUser(Date beginDate, Date endDate, JobUserPK jobUserPK,
			Job job, Employee employee) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.jobUserPK = jobUserPK;
		this.job = job;
		this.employee = employee;
	}


	@Temporal(TemporalType.DATE)
	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}   
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}   
	@EmbeddedId
	public JobUserPK getJob_UserPK() {
		return this.jobUserPK;
	}

	public void setJob_UserPK(JobUserPK jobUserPK) {
		this.jobUserPK = jobUserPK;
	}
	@ManyToOne
	@JoinColumn(name="job_id",referencedColumnName="id",insertable=false,updatable=false)
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	@ManyToOne
	@JoinColumn(name="employee_id",referencedColumnName="id",insertable=false,updatable=false)
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public JobUser(Date beginDate, Date endDate, Job job, Employee employee) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.job = job;
		this.employee = employee;
		this.jobUserPK=new JobUserPK(job.getId(), employee.getId());
	}


	@Override
	public String toString() {
		return "JobUser [beginDate=" + beginDate + ", endDate=" + endDate
				+ ", jobUserPK=" + jobUserPK + ", job=" + job + ", employee="
				+ employee + "]";
	}
	
	
}
