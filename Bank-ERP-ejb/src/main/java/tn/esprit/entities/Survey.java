package tn.esprit.entities;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Init;
import javax.jws.soap.InitParam;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Survey implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String theme;
	String description ;
	
	 @Column(name="flag", columnDefinition="Decimal(1) default '0'")
	int flag;
	
	
	@OneToMany(mappedBy="survey",fetch = FetchType.EAGER,cascade={CascadeType.REMOVE,CascadeType.MERGE})
	 List<Question> questionlist;


	public Survey(String theme, String description, int flag,
			List<Question> questionlist) {
		super();
		this.theme = theme;
		this.description = description;
		this.flag = flag;
		this.questionlist = questionlist;
	}

	
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public List<Question> getQuestionlist() {
		return questionlist;
	}

	public void setQuestionlist(List<Question> questionlist) {
		this.questionlist = questionlist;
	}

	public Survey(String theme,String description) {
		super();
		this.theme = theme;
		this.description=description;
	}
	
	
	
	
	public Survey(String theme, String description, List<Question> questionlist) {
		super();
		this.theme = theme;
		this.description = description;
		this.questionlist = questionlist;
	}

	public Survey() {
		super();
		
	}
	
	
	
	
	
}
