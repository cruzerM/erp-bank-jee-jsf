package tn.esprit.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import tn.esprit.entities.Candidate;
import tn.esprit.entities.Employee;
import tn.esprit.entities.Job;
import tn.esprit.entities.JobUser;
import tn.esprit.enumerators.CandidateState;
import tn.esprit.services.interfaces.IServiceCandidateLocal;
import tn.esprit.services.interfaces.IServiceJobLocal;
import tn.esprit.services.interfaces.IServiceJobRemote;

/**
 * Session Bean implementation class ServiceJob
 */
@Stateless
public class ServiceJob implements IServiceJobRemote, IServiceJobLocal {

	@PersistenceContext(unitName = "Bank-ERP-ejb")
	private EntityManager em;
	@EJB
	private IServiceCandidateLocal serviceCandidate;

	/**
	 * Default constructor.
	 */
	public ServiceJob() {
	}

	@Override
	public Boolean addCandidateToJob(Integer idCandidate, Integer idJob) {
		Boolean b = false;
		try {
			Candidate candidateFound = em.find(Candidate.class, idCandidate);
			Job jobFound = em.find(Job.class, idJob);
			candidateFound.setJob(jobFound);
			em.merge(candidateFound);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean rejectCandidate(Integer idCandidate) {
		Boolean result = false;
		Candidate candidateFound = em.find(Candidate.class, idCandidate);
		candidateFound.setState(CandidateState.REJECTED);
		try {
			serviceCandidate.updateCandidate(candidateFound);
			result = true;
		} catch (NotFoundException e) {
			System.out.println("Not Found: " + e.getMessage());
		}
		return result;
	}

	@Override
	public Boolean addJob(Job job) {
		Boolean result = false;

		try {
			em.persist(job);
			result = true;
		} catch (Exception e) {
			System.out.println("Persist Error: " + e.getMessage());
		}
		return result;
	}

	@Override
	public Boolean updateJob(Job job) {
		if (findJobById(job.getId()) != null) {
			Boolean result = false;

			try {
				em.merge(job);
				result = true;
			} catch (Exception e) {
				System.out.println("Merge Error: " + e.getMessage());
			}
			return result;
		} else
			throw new NotFoundException();
	}

	@Override
	public Boolean deleteJob(Integer id) {
		Job job = findJobById(id);
		if (job != null) {
			Boolean result = false;

			try {
				em.remove(job);
				result = true;
			} catch (Exception e) {
				System.out.println("Delete Error: " + e.getMessage());
			}
			return result;
		} else
			throw new NotFoundException();
	}

	@Override
	public Job findJobById(Integer id) {
		return em.find(Job.class, id);
	}

	@Override
	public Job findJobByName(String name) {
		String jpql = "select j from Job j where j.name=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", name);
		Job jobFound = null;
		try {
			jobFound = (Job) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no result");
		}
		return jobFound;
	}

	@Override
	public List<Job> findAllJobs() {
		String jpql = "select j from Job j";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public List<Job> findAllAvailableJobs() {
		String jpql = "select j from Job j where j.status=0";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	public Boolean deleteJob(Job j) {
		try{
			em.remove(em.merge(j));
			return true;
			}catch(Exception ex){
				return false;
			}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findAllJob() {
		Query query=em.createQuery("select j from Job j");
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Job> findAvJobByDepartment(Integer id) {
		Query query=em.createQuery("select j from Job j where j.status=false and j.departement.id=:id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public Boolean addJobToAnEmployee(Employee e,Job j,Date beginDate,Date endDate) {
		
		JobUser roles = new JobUser(beginDate, endDate, j, e);
		try{
			em.persist(roles);
			return true;
			}catch(Exception ex){
				return false;
			}
	}

	@Override
	public Boolean updateJobToAnEmployee(Employee e,Job j,Date beginDate,Date endDate) {
		JobUser roles = new JobUser(beginDate, endDate, j, e);
		try{
			em.merge(roles);
			return true;
			}catch(Exception ex){
				return false;
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobUser> findAlljobsByEmployee(Integer id) {
		Query query=em.createQuery("select j from JobUser j where j.employee.id=:id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobUser> findAlljobsByEmployee(Integer idE, Integer idJ) {
		Query query=em.createQuery("select j from JobUser j where j.employee.id=:ide  and j.job.id=:idj");
		query.setParameter("ide", idE).setParameter("idj", idJ);
		return query.getResultList();
	}

}
