package tn.esprit.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question implements Serializable {
 
	private static final long serialVersionUID = 5217161219841273012L;
	
	@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
    String question;
    
    @ManyToOne
     Survey survey; 
     
    @OneToMany(mappedBy="question",fetch = FetchType.EAGER,cascade={CascadeType.REMOVE,CascadeType.MERGE})
     List<Proposition> propositionlist;
    

    public Question(String question, Survey survey,
			List<Proposition> propositionlist, int flag) {
		super();
		this.question = question;
		this.survey = survey;
		this.propositionlist = propositionlist;
		this.flag = flag;
	}

    @Column(name="flag", columnDefinition="Decimal(1) default '0'")
	int flag;
    
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public List<Proposition> getPropositionlist() {
		return propositionlist;
	}

	public void setPropositionlist(List<Proposition> propositionlist) {
		this.propositionlist = propositionlist;
	}

	public Question(String question, Survey survey,
			List<Proposition> propositionlist) {
		super();
		this.question = question;
		this.survey = survey;
		this.propositionlist = propositionlist;
	}
    

    
 
	public Question() {
		super();
		
	} 
    
    
    
    
 
}
