package tn.esprit.beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import tn.esprit.entities.Proposition;
import tn.esprit.entities.Question;
import tn.esprit.entities.Survey;
import tn.esprit.services.interfaces.IPropositionServiceRemote;
import tn.esprit.services.interfaces.IQuestionServiceRemote;
import tn.esprit.services.interfaces.ISurveyServiceRemote;


@SessionScoped
@ManagedBean(name = "QuestionMB")
public class QuestionMB {
	List<SelectItem> selectoneitemrol;
	
    public List<SelectItem> getSelectoneitemrol() {
		return selectoneitemrol;
	}


	public void setSelectoneitemrol(List<SelectItem> selectoneitemrol) {
		this.selectoneitemrol = selectoneitemrol;
	}
//------------------------------------

	Question c = new Question();
    List<Question> questionlist = new ArrayList<>();
    private Question questionSelected = new Question();
	private Boolean displayForm = false;
	@EJB
	IQuestionServiceRemote questionservice;
  //-----------------------------
    Survey survey=new Survey();	
	List<Survey> surveylist = new ArrayList<>();
	 public List<Survey> getSurveylist() {
		return surveylist;
	}


	public void setSurveylist(List<Survey> surveylist) {
		this.surveylist = surveylist;
	}


	@EJB
	 ISurveyServiceRemote surveyservice;
	  public Survey getSurveySelected() {
		return surveySelected;
	}


	public void setSurveySelected(Survey surveySelected) {
		this.surveySelected = surveySelected;
	}


	Survey surveySelected = new Survey();
  //-----------------------------
	private Map<String, String> myModelValues = new HashMap<String, String>();
//	private Map<Survey, HashMap<String, String>> myModelValues = new HashMap<Survey, HashMap<String, String>>();

	private Map<String,String> surveys=new  HashMap<String, String>();
  //--------------------------

   
    
	public void dislay() {
		setDisplayForm(true);
	}


	public Question getC() {
		return c;
	}


	public void setC(Question c) {
		this.c = c;
	}


	public Question getQuestionSelected() {
		return questionSelected;
	}


	public void setQuestionSelected(Question questionSelected) {
		this.questionSelected = questionSelected;
	}


	public Boolean getDisplayForm() {
		return displayForm;
	}


	public void setDisplayForm(Boolean displayForm) {
		this.displayForm = displayForm;
	}
	
	
	//-------------------------------------------

 
	
	public Survey getSurvey() {
		return survey;
	}


	public void setSurvey(Survey survey) {
		this.survey = survey;
	}


//------------------------------------------crud

	public String doAdd() {
		
		
		Survey q =surveyservice.findSurveyById(survey.getId());	
        c.setSurvey(q);
		questionservice.addQuestion(c);
	
		return("/question/index?faces-redirect=true");
		}
	
	

	
	
	   public List<Question> getQuestionlist() {	   
		   questionlist= questionservice.findallQuestion();
			return questionlist;
		}


		public void setQuestionlist(List<Question> questionlist) {
			this.questionlist = questionlist;
		}
		public void doDeleteQuestion(Question q) {
			questionservice.deleteQuestion(q);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			
		}
		
		public String doUpdateQuestion(Question c) {	
			this.c=c;
			return("/question/edit");	
		}
	  public String doUpdateQuestion() {
		  questionservice.updateQuestion(c); 
			return("/question/index?faces-redirect=true");
			
		}
	//-----------------------------------------------dropdownlist
	
 
	

	public Map<String, String> getMyModelValues() {
	//	this.selectoneitemrol=new ArrayList<>();
		surveylist = surveyservice.findallSurvey();
		        for (Survey m:surveylist) {
		            myModelValues.put( m.getTheme(),""+m.getId());
		         //   this.selectoneitemrol.add((SelectItem) myModelValues);
		     }
		        return myModelValues;
	    }
	
	
	
	
	public void setMyModelValues(Map<String, String> myModelValues) {
	this.myModelValues= myModelValues;
	 }
	  
	  
	  
	/*	public Map<Survey, HashMap<String, String>> getMyModelValues() {
			
			surveylist = surveyservice.findallSurvey();		
			 for (Survey m:surveylist) {
				 surveys.put(m.getTheme(),""+m.getId());
				 myModelValues.put(m,(HashMap<String, String>) surveys);
		     }
			
			return myModelValues;
		}


		public void setMyModelValues(Map<Survey, HashMap<String, String>> myModelValues) {
			this.myModelValues = myModelValues;
		}


	  
	  */
	
	
	
	//------------------------------------------------find
	
			Proposition p = new Proposition();
			List<Proposition> propositionlist = new ArrayList<>();
			
			@EJB
			IPropositionServiceRemote propositionservice;
			
			
			public String doFindProposition(Question c) {	
				this.c=c;
				return("/listeproposition");	
			}


			public List<Proposition> getPropositionlist() {
				propositionlist=propositionservice.findallPropositionByQuestion(c);
				return propositionlist;
			}


			public void setPropositionlist(List<Proposition> propositionlist) {
				this.propositionlist = propositionlist;
			}
			
			
			 public String redrictionAjout()
			  {    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				  return("/ajoutquest?faces-redirect=true");}
			  		
			
//--------------------------------------

			public Map<String,String> getSurveys() {
				

				surveylist = surveyservice.findallSurvey();
				
				 for (Survey m:surveylist) {
					 surveys.put( m.getTheme(),""+m.getId());
					
			     }
				return surveys;
			}


			public void setSurveys(Map<String,String> surveys) {
				this.surveys = surveys;
			}


	
	
	
}
