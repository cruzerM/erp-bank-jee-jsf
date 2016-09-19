package tn.esprit.entities;

import java.io.Serializable;





import javax.persistence.Embeddable;

@Embeddable
public class JobUserPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer job_id;
	private Integer employee_id;
	
	
	public Integer getJob_id() {
		return job_id;
	}
	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employee_id == null) ? 0 : employee_id.hashCode());
		result = prime * result + ((job_id == null) ? 0 : job_id.hashCode());
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
		JobUserPK other = (JobUserPK) obj;
		if (employee_id == null) {
			if (other.employee_id != null)
				return false;
		} else if (!employee_id.equals(other.employee_id))
			return false;
		if (job_id == null) {
			if (other.job_id != null)
				return false;
		} else if (!job_id.equals(other.job_id))
			return false;
		return true;
	}
	public JobUserPK(Integer job_id, Integer employee_id) {
		super();
		this.job_id = job_id;
		this.employee_id = employee_id;
	}
	
	public JobUserPK() {
		super();
	}
	
	

	
}
