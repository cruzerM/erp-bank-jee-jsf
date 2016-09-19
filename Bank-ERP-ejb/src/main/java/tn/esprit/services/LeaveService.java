package tn.esprit.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tn.esprit.crud.ILeaveCrud;
import tn.esprit.entities.Leave;
import tn.esprit.services.interfaces.LeaveServiceRemote;

@Stateless
public class LeaveService implements LeaveServiceRemote {

	@EJB
	ILeaveCrud l;

	@Override
	public Boolean doAdd(Leave leave) {
		try {
			l.add(leave);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean doDelete(Leave leave) {
		try {
			l.delete(leave);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean doUpdate(Leave leave) {
		try {
			l.update(leave);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Leave> doFindAll() {
		return l.findAll();

	}

	@Override
	public Leave doFindLeaveById(Integer leaveId) {
		return l.findLeaveById(leaveId);
	}

	@Override
	public List<Leave> doFindLeavesByEmployeeId(Integer empId) {
		return l.findLeavesByEmployeeId(empId);
	}

}