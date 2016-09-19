package tn.esprit.services;
import java.util.List;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Proposition;
import tn.esprit.entities.Question;
import tn.esprit.services.interfaces.IPropositionServiceRemote;

@Stateless
public class PropositionService implements IPropositionServiceRemote{
	@PersistenceContext
	private EntityManager em;
	
	public PropositionService(){}

	@Override
	public void addProposition(Proposition pro) {
		em.persist(pro);
		
	}

	@Override
	public void deleteProposition(Proposition pro) {
		em.remove(em.merge(pro));
		
	}

	@Override
	public void updateProposition(Proposition pro) {
		em.merge(pro);
		
	}

	@Override
	public List <Proposition> findallPropositionByQuestion(Question ques) {
	
		return em
				.createQuery("select p from Proposition p where p.question=:s",
						Proposition.class).setParameter("s", ques)
				.getResultList();
		
	}
	
	@Override
	public List <Proposition> findAllproposition()
	{	return em.createQuery("select p from Proposition p", Proposition.class)
			.getResultList();
		
	}
	
	
	

}
