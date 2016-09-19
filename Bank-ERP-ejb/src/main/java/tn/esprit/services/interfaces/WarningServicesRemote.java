package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Warning;



@Remote
public interface WarningServicesRemote {
	public boolean AddWarning(Warning warning);

	public boolean DeleteWarning(Integer Id);

	public Warning findWarningById(Integer Id);

	public boolean updateWarning(Warning warning);
	
	public List<Warning> findAll();
	
	public List<Warning> findwarningByIdUser(Integer id);
}
