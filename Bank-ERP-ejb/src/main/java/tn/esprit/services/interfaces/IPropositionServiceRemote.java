package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Proposition;
import tn.esprit.entities.Question;



@Remote
public interface IPropositionServiceRemote {

	
	void addProposition(Proposition pro);
	void deleteProposition(Proposition pro);
	void updateProposition(Proposition pro);
	List <Proposition> findallPropositionByQuestion(Question ques);
	List <Proposition> findAllproposition();
	
	
	
}
