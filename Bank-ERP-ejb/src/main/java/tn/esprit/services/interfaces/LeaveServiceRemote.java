package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Leave;

@Remote
public interface LeaveServiceRemote {
	Boolean doAdd(Leave leave);

	Boolean doDelete(Leave leave);

	Boolean doUpdate(Leave leave);

	List<Leave> doFindAll();

	Leave doFindLeaveById(Integer leaveId);

	List<Leave> doFindLeavesByEmployeeId(Integer empId);
}
