package tn.esprit.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.entities.Question;
import tn.esprit.entities.Survey;
import tn.esprit.services.interfaces.IQuestionServiceRemote;
import tn.esprit.services.interfaces.ISurveyServiceRemote;

@SessionScoped
@ManagedBean(name = "surveymb")
public class SurveyMB {

	Survey c = new Survey();
	List<Survey> surveylist = new ArrayList<>();
	private Survey surveySelected = new Survey();
	private Boolean displayForm = false;
	@EJB
	ISurveyServiceRemote surveyservice;
	
	//-------------------------------------
	
	Question q = new Question();
	List<Question> questionlist = new ArrayList<>();
	
	@EJB
	IQuestionServiceRemote questionservice;
	//-------------------------

	public void dislay() {
		displayForm = true;
	}
	

	public Survey getC() {
		return c;
	}

	public void setC(Survey c) {
		this.c = c;
	}

	public Survey getSurveySelected() {
		return surveySelected;
	}

	public void setSurveySelected(Survey surveySelected) {
		this.surveySelected = surveySelected;
	}

	public Boolean getDisplayForm() {
		return displayForm;
	}

	public void setDisplayForm(Boolean displayForm) {
		this.displayForm = displayForm;
	}
	//--------------------------------

	
	
	public String doFindQuestion(Survey c) {	
		this.c=c;
		return("listequestion");	
	}

  public List<Question> getQuestionlist() {
		
		
	  questionlist= questionservice.findallQuestionBySurvey(c);
		return questionlist;
		
	}

//---------------------------------
	
	public Question getQ() {
		return q;
	}


	public void setQ(Question q) {
		this.q = q;
	}





	public void setQuestionlist(List<Question> questionlist) {
		this.questionlist = questionlist;
	}


	public String doAdd() {

		
	surveyservice.addSurvey(c);
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return("indexsurvey?faces-redirect=true");
	}
	
	
	public List<Survey> getSurveylist() {
		surveylist= surveyservice.findallSurvey();
		return surveylist;
	}
	public void setSurveylist(List<Survey> surveylist) {
		this.surveylist = surveylist;
	}
   
	public void doDeleteSurvey(Survey s) {
		surveyservice.deleteSurvey(s);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
	}
	
	public String doUpdateSurvey(Survey c) {	
		this.c=c;
		return("editsurvey");	
	}
  public String doUpdateSurvey() {
	  surveyservice.updateSurvey(c);
		return("indexsurvey?faces-redirect=true");
		
	}
  
  public String redrictionAjout()
  {    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	  return("ajoutsurvey?faces-redirect=true");}
  

	
}
