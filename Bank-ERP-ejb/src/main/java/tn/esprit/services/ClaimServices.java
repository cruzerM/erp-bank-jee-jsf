package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Claim;
import tn.esprit.services.interfaces.ClaimServicesRemote;


@Stateless
public class ClaimServices implements ClaimServicesRemote{

	@PersistenceContext(unitName = "Bank-ERP-ejb")
	private EntityManager entityManager;
	
	@Override
	public boolean AddClaim(Claim claim) {
		Boolean bool = false;
		try {
			entityManager.persist(claim);
			System.out.println("succcesss");
			bool = true;
			

		} catch (Exception e) {
			System.out.println(claim.getDescription());
			System.out.println(claim.getType());
			
			System.err.println("Insertion failure");

		}
		return bool;
	}

	@Override
	public boolean DeleteClaim(Claim claim) {
		Boolean b = false;
		try {
			entityManager.remove(claim);
			b = true;

		} catch (Exception e) {
			System.err.println("Delete failure");

		}
		return b;
	}

	@Override
	public Claim findClaimById(Integer Id) {
		return entityManager.find(Claim.class, Id);
	}

	@Override
	public boolean updateClaim(Claim claim) {
		Boolean b = false;
		try {
			entityManager.merge(claim);
			b = true;

		} catch (Exception e) {
			System.err.println("update failure");

		}
		return b;
	}

	@Override
	public List<Claim> findAll() {
		Query query=entityManager.createQuery("SELECT c FROM Claim c");
		return query.getResultList();
	}

}
