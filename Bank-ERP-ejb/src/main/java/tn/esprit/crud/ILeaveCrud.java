package tn.esprit.crud;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Leave;

@Local
public interface ILeaveCrud {
	void add(Leave leave);

	void delete(Leave leave);

	void update(Leave leave);

	List<Leave> findAll();

	Leave findLeaveById(Integer leaveId);

	List<Leave> findLeavesByEmployeeId(int empId);
}
