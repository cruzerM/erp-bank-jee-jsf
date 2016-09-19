package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Claim;
import tn.esprit.entities.Warning;
import tn.esprit.services.interfaces.WarningServicesRemote;

@Stateless
public class WarningServices implements WarningServicesRemote{
	@PersistenceContext(unitName = "Bank-ERP-ejb")
	private EntityManager entityManager;
	
	@Override
	public boolean AddWarning(Warning warning) {
		Boolean bool = false;
		try {
			entityManager.persist(warning);
			System.out.println("succcesss");
			bool = true;
			

		} catch (Exception e) {
			System.out.println(warning.getCause());
			System.out.println(warning.getTitre());
			
			System.err.println("Insertion failure");

		}
		return bool;
	}

	@Override
	public boolean DeleteWarning(Integer Id) {
		Boolean b = false;
		try {
			entityManager.remove(entityManager.find(Claim.class, Id));
			b = true;

		} catch (Exception e) {
			System.err.println("Delete failure");

		}
		return b;
	}

	@Override
	public Warning findWarningById(Integer Id) {
		return entityManager.find(Warning.class, Id);
	}

	@Override
	public boolean updateWarning(Warning warning) {
		Boolean b = false;
		try {
			entityManager.merge(warning);
			b = true;

		} catch (Exception e) {
			System.err.println("update failure");

		}
		return b;
	}

	@Override
	public List<Warning> findAll() {
		Query query=entityManager.createQuery("SELECT w FROM Warning w");
		return query.getResultList();
	}

	@Override
	public List<Warning> findwarningByIdUser(Integer id) {
		Query query=entityManager.createQuery("select w from Warning w where w.user.id=:Id");
		query.setParameter("Id", id);
		return query.getResultList();
	}

}
