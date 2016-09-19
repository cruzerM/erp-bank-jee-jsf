package tn.esprit.services;
import java.util.List;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Question;
import tn.esprit.entities.Survey;
import tn.esprit.services.interfaces.ISurveyServiceRemote;

@Stateless
public class SuerveyService implements ISurveyServiceRemote{
	@PersistenceContext
	private EntityManager em;

	
	public SuerveyService(){}


	@Override
	public void addSurvey(Survey survey) {
		em.persist(survey);
		
	}


	@Override
	public void deleteSurvey(Survey survey) {
		em.remove(em.merge(survey));
		
	}


	@Override
	public void updateSurvey(Survey survey) {
		em.merge(survey);
		
	}


	@Override
	public List <Survey> findallSurvey() {
		return em.createQuery("select s from Survey s", Survey.class)
				.getResultList();
		
		
	}
	
	
	 @Override
	    public Survey findSurveyById(int id)
	   {return em.find(Survey.class,id);}
	
}
