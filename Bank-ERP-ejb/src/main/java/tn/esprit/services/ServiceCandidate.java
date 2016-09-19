package tn.esprit.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import tn.esprit.entities.Candidate;
import tn.esprit.enumerators.CandidateSource;
import tn.esprit.enumerators.CandidateState;
import tn.esprit.services.interfaces.IServiceCandidateLocal;
import tn.esprit.services.interfaces.IServiceCandidateRemote;
import tn.esprit.services.interfaces.IServiceJobLocal;

/**
 * Session Bean implementation class ServiceCandidate
 */
@Stateless
public class ServiceCandidate implements IServiceCandidateRemote,
		IServiceCandidateLocal {

	@PersistenceContext(unitName = "Bank-ERP-ejb")
	private EntityManager em;

	@EJB
	IServiceJobLocal serviceJob;

	/**
	 * Default constructor.
	 */
	public ServiceCandidate() {
	}

	@Override
	public Boolean addCandidate(Candidate c) {
		Boolean result = false;

		try {
			em.persist(c);
			result = true;
		} catch (Exception e) {
			System.out.println("Persist Error: " + e.getMessage());
		}
		return result;
	}

	@Override
	public Boolean updateCandidate(Candidate c) {

		if (findCandidateById(c.getId()) != null) {
			Boolean result = false;

			try {
				em.merge(c);
				result = true;
			} catch (Exception e) {
				System.out.println("Merge Error: " + e.getMessage());
			}
			return result;
		} else
			throw new NotFoundException();
	}

	@Override
	public Boolean deleteCandidate(Integer id) {
		Candidate c = findCandidateById(id);
		if (c != null) {
			Boolean result = false;

			try {
				em.remove(c);
				result = true;
			} catch (Exception e) {
				System.out.println("Delete Error: " + e.getMessage());
			}
			return result;
		} else
			throw new NotFoundException();
	}

	@Override
	public Candidate findCandidateById(Integer id) {
		return em.find(Candidate.class, id);
	}

	@Override
	public List<Candidate> findCandidateByFirstName(String fName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Candidate> findCandidateByLastName(String lName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Candidate> findCandidateBySource(CandidateSource source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Candidate> findCandidateByState(CandidateState state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Candidate> findCandidateByAppDate(Date applicationDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Candidate> findCandidateByYearsOfExp(Integer experience) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Candidate> findAllCandidates() {
		String jpql = "select c from Candidate c";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public List<Candidate> findAllCandidatesByJob(Integer idJob) {
		String jpql = "select c from Candidate c where c.job.id=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", idJob);
		return query.getResultList();
	}

	@Override
	public List<Candidate> findAllCandidatesByJob(String nameJob) {
		String jpql = "select c from Candidate c where c.job.id=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", serviceJob.findJobByName(nameJob).getId());
		return query.getResultList();
	}

	@Override
	public Boolean updateCandidateState(Integer id, CandidateState state) {
			Candidate candidateFound = findCandidateById(id);
		if (candidateFound != null) {
			Boolean result = false;
			candidateFound.setState(state);
			try {
				em.merge(candidateFound);
				result = true;
			} catch (Exception e) {
				System.out.println("Merge Error: " + e.getMessage());
			}
			return result;
		} else
			throw new NotFoundException();
	}

}
