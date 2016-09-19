package tn.esprit.services.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Candidate;
import tn.esprit.enumerators.CandidateSource;
import tn.esprit.enumerators.CandidateState;

@Local
public interface IServiceCandidateLocal {

	Boolean addCandidate(Candidate c);

	Boolean updateCandidate(Candidate c);

	Boolean updateCandidateState(Integer id, CandidateState state);

	Boolean deleteCandidate(Integer id);

	// Find Methods
	Candidate findCandidateById(Integer id);

	List<Candidate> findCandidateByFirstName(String fName);

	List<Candidate> findCandidateByLastName(String lName);

	List<Candidate> findCandidateBySource(CandidateSource source);

	List<Candidate> findCandidateByState(CandidateState state);

	List<Candidate> findCandidateByAppDate(Date applicationDate);

	List<Candidate> findCandidateByYearsOfExp(Integer experience);

	List<Candidate> findAllCandidates();

	List<Candidate> findAllCandidatesByJob(Integer idJob);

	List<Candidate> findAllCandidatesByJob(String nameJob);
}
