package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Departement;

@Remote
public interface ServiceDepartementRemote {
	public Boolean addDepartement(Departement d);
	public Boolean updateDepartement(Departement d);
	public Boolean deleteDepartement(Departement d);
	public List<Departement>  findDepartementByName(String name);
	public List<Departement> findAllDepartement();
}
