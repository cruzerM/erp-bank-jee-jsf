package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Departement;
import tn.esprit.services.interfaces.ServiceDepartementRemote;

/**
 * Session Bean implementation class ServiceDepartement
 */
@Stateless
public class ServiceDepartement implements ServiceDepartementRemote {

	@PersistenceContext(unitName = "Bank-ERP-ejb")
	EntityManager em;

	@Override
	public Boolean addDepartement(Departement d) {
		try {
			em.persist(d);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Boolean updateDepartement(Departement d) {
		try {
			em.merge(d);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Boolean deleteDepartement(Departement d) {
		try {
			em.remove(em.merge(d));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departement> findDepartementByName(String name) {
		Query query = em
				.createQuery("select d from Departement d where d.name=:name");
		query.setParameter("name", name);
		return (List<Departement>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departement> findAllDepartement() {
		Query query = em.createQuery("select d from Departement d");
		return query.getResultList();
	}

}
