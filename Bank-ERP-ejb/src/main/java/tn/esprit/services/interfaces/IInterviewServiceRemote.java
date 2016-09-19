package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Interview;

@Remote
public interface IInterviewServiceRemote {

	Boolean scheduleInterview(Integer idInterview, Integer idCandidate);

	Boolean assignEmployeeToInterview(Integer idInterview, Integer idEmployee);

	Interview findInterviewById(Integer id);
	
	Boolean addInterview(Interview i, Integer idCandidate, Integer idEmployee);
	
	List<Interview> findAllInterviews();
}
