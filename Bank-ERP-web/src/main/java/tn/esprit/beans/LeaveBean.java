package tn.esprit.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import tn.esprit.crud.ILeaveCrud;
import tn.esprit.entities.Leave;
import tn.esprit.services.interfaces.LeaveServiceRemote;

@ManagedBean
@SessionScoped
public class LeaveBean {

	public LeaveBean() {
	}

	private Leave leave = new Leave();
	private DataModel<Leave> datamodelLeave = new ListDataModel<Leave>();

	@EJB
	private LeaveServiceRemote leaveService;

	public List<Leave> findAllLLeaves() {
		return this.leaveService.doFindAll();
	}

	public DataModel<Leave> getDataModel() {
		datamodelLeave.setWrappedData(leaveService.doFindAll());
		return datamodelLeave;
	}

	public String doAddLeave() {
		leaveService.doAdd(leave);
		return ("template?faces-redirect=true");
	}

	@PostConstruct
	public void init() {
		leave = new Leave();
	}

	/*** Getters & Setters ***/

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

}