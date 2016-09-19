package tn.esprit.services.interfaces;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Question;
import tn.esprit.entities.Survey;


@Remote
public interface IQuestionServiceRemote {

	
	void addQuestion(Question quest);
	void deleteQuestion(Question quest);
	void updateQuestion(Question quest);
	List<Question> findallQuestionBySurvey(Survey survey);
	List<Question> findallQuestion();
	Question findQuestionById(int id);
	
}
