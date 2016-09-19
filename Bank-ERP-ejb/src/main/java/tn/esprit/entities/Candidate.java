package tn.esprit.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tn.esprit.enumerators.CandidateSource;
import tn.esprit.enumerators.CandidateState;

/**
 * Entity implementation class for Entity: Candidate
 *
 */
@Entity
public class Candidate extends User implements Serializable {

	private String resume;
	private String degree;
	private Integer yearsOfExperience;
	private CandidateState state;
	private CandidateSource source;
	private Date applicationDate;
	private String skills;

	private Job job;
	
	private List<Interview> interviews;

	private static final long serialVersionUID = 1L;

	public Candidate() {
		super();
		state = CandidateState.WAITING;
		source = null;
		applicationDate = new Date();
		interviews = new ArrayList<Interview>();
	}

	public Candidate(Long cin, String firstName, String lastName,
			String mail, String resume, String degree,
			Integer yearsOfExperience, CandidateState state,
			CandidateSource source, Date applicationDate, String skills, List<Interview> interviews) {
		super(cin, firstName, lastName, mail);
		this.resume = resume;
		this.degree = degree;
		this.yearsOfExperience = yearsOfExperience;
		this.state = state;
		this.source = source;
		this.applicationDate = applicationDate;
		this.skills = skills;
		this.interviews = interviews;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Integer getYearsOfExperience() {
		return this.yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	@Enumerated(EnumType.STRING)
	public CandidateState getState() {
		return this.state;
	}

	public void setState(CandidateState state) {
		this.state = state;
	}

	@Enumerated(EnumType.STRING)
	public CandidateSource getSource() {
		return this.source;
	}

	public void setSource(CandidateSource source) {
		this.source = source;
	}

	@Temporal(TemporalType.DATE)
	public Date getApplicationDate() {
		return this.applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	@ManyToOne
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@ManyToMany(mappedBy="candidates")
	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}
	
	
	

	
}
