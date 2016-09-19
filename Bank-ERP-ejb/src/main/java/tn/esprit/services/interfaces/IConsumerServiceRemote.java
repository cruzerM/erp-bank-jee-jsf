package tn.esprit.services.interfaces;

import java.util.List;


import javax.ejb.Remote;

import tn.esprit.entities.Consumer;
import tn.esprit.entities.Proposition;




@Remote
public interface IConsumerServiceRemote {

	
	
	void addConsumer(Consumer con);
	List <Consumer> findallConsumersByProposition(Proposition pro);
	int countConsumersByProposition(Proposition pro);
	
}
