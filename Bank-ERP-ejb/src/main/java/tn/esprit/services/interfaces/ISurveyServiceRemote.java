package tn.esprit.services.interfaces;
import java.util.List;


import javax.ejb.Remote;

import tn.esprit.entities.Survey;

@Remote
public interface ISurveyServiceRemote {
	
	
	void addSurvey(Survey survey);
	void deleteSurvey(Survey survey);
	void updateSurvey(Survey survey);
	List <Survey> findallSurvey();
	Survey findSurveyById(int id);

}
