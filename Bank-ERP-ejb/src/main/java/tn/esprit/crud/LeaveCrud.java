package tn.esprit.crud;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Leave;

@Stateless
public class LeaveCrud implements ILeaveCrud {

	@PersistenceContext(unitName = "Bank-ERP-ejb")
	EntityManager em;

	@Override
	public void add(Leave leave) {
		em.persist(leave);
	}

	@Override
	public void delete(Leave leave) {
		  Query query = em.createQuery("delete from Leave l where l.id = "+leave.getId());
			query.executeUpdate();

	}

	@Override
	public void update(Leave leave) {
		em.merge(leave);

	}

	@Override
	public List<Leave> findAll() {
		return em.createQuery("select a from Leave a", Leave.class)
				.getResultList();
	}

	@Override
	public Leave findLeaveById(Integer leaveId) {
		return em.find(Leave.class, leaveId);
	}

	@Override
	public List<Leave> findLeavesByEmployeeId(int empId) {
		return em
				.createQuery("select e from Leave e where e.Leave.id =:param",
						Leave.class).setParameter("param", empId)
				.getResultList();
	}

}
