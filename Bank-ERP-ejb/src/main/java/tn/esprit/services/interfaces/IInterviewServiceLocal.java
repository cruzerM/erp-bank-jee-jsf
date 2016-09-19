package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Interview;

@Local
public interface IInterviewServiceLocal {
	Boolean scheduleInterview(Integer idInterview, Integer idCandidate);

	Boolean assignEmployeeToInterview(Integer idInterview, Integer idEmployee);

	Interview findInterviewById(Integer id);
	
	Boolean addInterview(Interview i, Integer idCandidate, Integer idEmployee);

	List<Interview> findAllInterviews();
}
