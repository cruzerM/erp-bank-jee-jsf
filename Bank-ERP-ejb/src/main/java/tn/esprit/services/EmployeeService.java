package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Employee;
import tn.esprit.services.interfaces.IEmployeeServiceLocal;
import tn.esprit.services.interfaces.IEmployeeServiceRemote;

/**
 * Session Bean implementation class EmployeeService
 */
@Stateless
@LocalBean
public class EmployeeService implements IEmployeeServiceRemote,
		IEmployeeServiceLocal {
	@PersistenceContext(unitName = "Bank-ERP-ejb")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public EmployeeService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean addEmployee(Employee e) {
		Boolean result = false;

		try {
			em.persist(e);
			result = true;
		} catch (Exception ex) {
			System.out.println("Persist Error: " + ex.getMessage());
		}
		return result;
	}

	@Override
	public Employee findEmployeeById(Integer id) {
		return em.find(Employee.class, id);
	}

	@Override
	public List<Employee> findAllEmployees() {
		String jpql = "select e from Employee e";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public Boolean updateEmploye(Employee e) {
		try {
			em.merge(e);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Boolean deleteEmploye(Employee e) {
		try {
			em.remove(em.merge(e));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findEmployeeByEmpStatus(String status) {
		Query query = em
				.createQuery("select e from Employee e where e.employementStat=:st");
		query.setParameter("st", status);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findEmployeeByDepartement(String name) {
		Query query = em
				.createQuery("select e from Employee e where e.departement.name=:name");
		query.setParameter("name", name);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findEmployeeByEmpStatusAndDepartement(String status,
			String name) {
		Query query = em
				.createQuery("select e from Employee e where e.employementStat=:st and e.departement.name=:name");
		query.setParameter("st", status).setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public Employee findEmployeeByLogin(String login) {
		return em.find(Employee.class, login);
	}
}
