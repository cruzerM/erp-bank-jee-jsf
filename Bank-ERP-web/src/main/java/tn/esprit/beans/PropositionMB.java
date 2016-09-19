package tn.esprit.beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import tn.esprit.entities.Proposition;
import tn.esprit.entities.Question;
import tn.esprit.services.interfaces.IPropositionServiceRemote;
import tn.esprit.services.interfaces.IQuestionServiceRemote;


@SessionScoped
@ManagedBean(name = "propositionmb")
public class PropositionMB {
	
    Proposition c=new Proposition();
    List<Proposition> propositionlist = new ArrayList<>();
    private Proposition propositionSelected = new Proposition();
	private Boolean displayForm = false;
    @EJB
	IPropositionServiceRemote propositionservice;
	//-------------------------------------------------
    Question question=new Question();	
   	List<Question> questionlist = new ArrayList<>();
   	 @EJB
   	 IQuestionServiceRemote questionservice;
    //--------------------------------------------------
 	private Map<String, String> myModelValues = new HashMap<String, String>();
   	 //----------------------------------------------------
    
 	public void dislay() {
 		setDisplayForm(true);
 	}

	public Proposition getC() {
		return c;
	}

	public void setC(Proposition c) {
		this.c = c;
	}

	
	public Proposition getPropositionSelected() {
		return propositionSelected;
	}

	public void setPropositionSelected(Proposition propositionSelected) {
		this.propositionSelected = propositionSelected;
	}

	public Boolean getDisplayForm() {
		return displayForm;
	}

	public void setDisplayForm(Boolean displayForm) {
		this.displayForm = displayForm;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Question> getQuestionlist() {
		return questionlist;
	}

	public void setQuestionlist(List<Question> questionlist) {
		this.questionlist = questionlist;
	}


 	//---------------------------------------------------crud
       public String doAdd() {	
    	   /*questionservice.addQuestion(q);
		 c.getQuestionlist().add(q);
		 surveyservice.updateSurvey(c);
		return("/question/index?faces-redirect=true");*/
    	  
    	 Question q = questionservice.findQuestionById(question.getId());
    	 c.setQuestion(q);
		propositionservice.addProposition(c);
		
		return("/proposition/index?faces-redirect=true");
		}

		public List<Proposition> getPropositionlist() {
			propositionlist= propositionservice.findAllproposition();
				return propositionlist;
			}

		public void setPropositionlist(List<Proposition> propositionlist) {
			this.propositionlist = propositionlist;
		}

		public void doDeleteProposition(Proposition p) {
			
			propositionservice.deleteProposition(p);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			
		}
		
		public String doUpdateProposition(Proposition c) {	
			this.c=c;
			return("editproposition");	
		}
	  public String doUpdateProposition() {
		  propositionservice.updateProposition(c);
			return("indexproposition?faces-redirect=true");
			
		}
	  
	  ///--------------------------------------------dropdownlist
		public Map<String, String> getMyModelValues() {
			
			questionlist = questionservice.findallQuestion();
			        for (Question m:questionlist) {
			            myModelValues.put( m.getQuestion(),""+m.getId());
			     }
			        return myModelValues;
		    }
		public void setMyModelValues(Map<String, String> myModelValues) {
		this.myModelValues= myModelValues;
		 }
		

		 public String redrictionAjout()
		  {    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			  return("ajoutproposition?faces-redirect=true");}
		  		
		//-------------------------Export 
		 public void exportPDF(ActionEvent actionevent)
		 {}
		 
		 public void exportDOCx(ActionEvent actionevent)
		 {}
		 
		 public void exportXLSX(ActionEvent actionevent)
		 {}
		 
		 public void exportODT(ActionEvent actionevent)
		 {}
		
		
		
		
}
