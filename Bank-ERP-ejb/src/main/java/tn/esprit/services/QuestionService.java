package tn.esprit.services;
import java.util.List;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Question;
import tn.esprit.entities.Survey;
import tn.esprit.services.interfaces.IQuestionServiceRemote;

@Stateless
public class QuestionService implements IQuestionServiceRemote {
	@PersistenceContext
	private EntityManager em;
    public QuestionService(){}
    
    
	@Override
	public void addQuestion(Question quest) {
		em.persist(quest);
		
	}
	@Override
	public void deleteQuestion(Question quest) {
		em.remove(em.merge(quest));
		
	}
	@Override
	public void updateQuestion(Question quest) {
		em.merge(quest);
		
	}
	@Override
	public List<Question> findallQuestionBySurvey(Survey survey) {
		return em
				.createQuery("select q from Question q where q.survey=:s",
						Question.class).setParameter("s", survey)
				.getResultList();
		
	}
    @Override
	public List<Question> findallQuestion()
	{	return em.createQuery("select q from Question q",Question.class)
					.getResultList();
	}
	
   @Override
    public Question findQuestionById(int id)
   {return em.find(Question.class,id);
	   
   }
    
}
