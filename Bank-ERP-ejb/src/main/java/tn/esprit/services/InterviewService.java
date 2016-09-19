package tn.esprit.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import tn.esprit.entities.Candidate;
import tn.esprit.entities.Employee;
import tn.esprit.entities.Interview;
import tn.esprit.enumerators.CandidateState;
import tn.esprit.services.interfaces.IEmployeeServiceLocal;
import tn.esprit.services.interfaces.IInterviewServiceLocal;
import tn.esprit.services.interfaces.IInterviewServiceRemote;
import tn.esprit.services.interfaces.IServiceCandidateLocal;

/**
 * Session Bean implementation class InterviewService
 */
@Stateless
public class InterviewService implements IInterviewServiceRemote,
		IInterviewServiceLocal {

	@PersistenceContext(unitName = "Bank-ERP-ejb")
	private EntityManager em;

	@EJB
	IServiceCandidateLocal candidateService;
	@EJB
	IEmployeeServiceLocal employeeService;

	/**
	 * Default constructor.
	 */
	public InterviewService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean scheduleInterview(Integer idInterview, Integer idCandidate) {
		Interview interviewFound = findInterviewById(idInterview);
		Candidate candidateFound = candidateService
				.findCandidateById(idCandidate);
		candidateFound.setState(CandidateState.INTERVIEW_SCHEDULED);
		try {
			candidateService.updateCandidate(candidateFound);
		} catch (NotFoundException e) {
			System.out.println("not found");
		}
		interviewFound.getCandidates().add(candidateFound);
		em.merge(interviewFound);

		return true;

	}

	@Override
	public Boolean assignEmployeeToInterview(Integer idInterview,
			Integer idEmployee) {
		Interview interviewFound = findInterviewById(idInterview);
		Employee employeeFound = employeeService.findEmployeeById(idEmployee);
		System.out.println("hello: " + employeeFound.getCin());
		interviewFound.getEmployees().add(employeeFound);

		em.merge(interviewFound);
		return true;
	}

	@Override
	public Interview findInterviewById(Integer id) {
		return em.find(Interview.class, id);
	}
	
	public Boolean addInterview(Interview i, Integer idCandidate, Integer idEmployee){
		Boolean result = false;
		
		Candidate candidateFound = candidateService.findCandidateById(idCandidate);
		Employee employeeFound = employeeService.findEmployeeById(idEmployee);
		candidateFound.setState(CandidateState.INTERVIEW_SCHEDULED);
		candidateService.updateCandidate(candidateFound);
		i.getCandidates().add(candidateFound);
		i.getEmployees().add(employeeFound);
		
		try {
			candidateService.updateCandidate(candidateFound);
			result = true;
		} catch (NotFoundException e) {
			System.out.println("not found");
		}
		em.merge(i);
		
		return result;	
	}

	@Override
	public List<Interview> findAllInterviews() {
		String jpql = "select i from Interview i";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	
}
