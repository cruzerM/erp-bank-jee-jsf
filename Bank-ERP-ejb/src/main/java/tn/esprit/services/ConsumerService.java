package tn.esprit.services;

import java.util.List;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Consumer;
import tn.esprit.entities.Proposition;
import tn.esprit.services.interfaces.IConsumerServiceRemote;

@Stateless
public class ConsumerService  implements IConsumerServiceRemote{
	@PersistenceContext
	private EntityManager em;
	public ConsumerService(){}
	@Override
	public void addConsumer(Consumer con) {
		em.persist(con);
		
	}
	@Override
	public List<Consumer> findallConsumersByProposition(Proposition pro) {

		return em
				.createQuery("select c from consumer c where c.proposition=:pro",
						Consumer.class).setParameter("s", pro)
				.getResultList();
	}
	@Override
	public int countConsumersByProposition(Proposition pro) {
		return em.createQuery("select c from consumer c where c.proposition=:pro",
						Consumer.class).setParameter("s", pro).getResultList().size();
				
	}
	
	
	

}
