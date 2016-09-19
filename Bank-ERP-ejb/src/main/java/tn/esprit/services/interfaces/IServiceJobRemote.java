package tn.esprit.services.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Job;
import tn.esprit.entities.JobUser;

@Remote
public interface IServiceJobRemote {

	Boolean addJob(Job job);

	Boolean updateJob(Job job);

	Boolean deleteJob(Integer id);

	Job findJobById(Integer id);

	Job findJobByName(String name);

	List<Job> findAllJobs();
	
	List<Job> findAllAvailableJobs();

	Boolean addCandidateToJob(Integer idCandidate, Integer idJob);

	Boolean rejectCandidate(Integer idCandidate);

	Boolean deleteJob(Job j);
	
	List<Job> findAllJob();
	List<Job> findAvJobByDepartment(Integer id);
	
	/***********/
	
	Boolean addJobToAnEmployee(Employee e,Job j,Date beginDate,Date endDate);
	Boolean updateJobToAnEmployee(Employee e,Job j,Date beginDate,Date endDate);
	List<JobUser> findAlljobsByEmployee(Integer id);
	List<JobUser> findAlljobsByEmployee(Integer idE,Integer idJ);
}
