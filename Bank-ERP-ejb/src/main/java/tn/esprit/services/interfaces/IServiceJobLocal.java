package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Job;

@Local
public interface IServiceJobLocal {

	Boolean addJob(Job job);

	Boolean updateJob(Job job);

	Boolean deleteJob(Integer id);

	Job findJobById(Integer id);

	Job findJobByName(String name);

	List<Job> findAllJobs();
	
	List<Job> findAllAvailableJobs();

	Boolean addCandidateToJob(Integer idCandidate, Integer idJob);

	Boolean rejectCandidate(Integer idCandidate);
}
