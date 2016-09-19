package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Job;
import tn.esprit.entities.JobUser;
import tn.esprit.entities.PayRoll;
import tn.esprit.services.interfaces.IPayRollServices;

@Stateless
public class PayRollServices implements IPayRollServices {

	@PersistenceContext(unitName = "Bank-ERP-ejb")
	EntityManager entityManager;

	@Override
	public void add(PayRoll payRoll) {
		entityManager.persist(payRoll);
	}

	@Override
	public void delete(PayRoll payRoll) {
		entityManager.remove(payRoll);
	}

	@Override
	public void update(PayRoll payRoll) {
		entityManager.merge(payRoll);
	}

	@Override
	public PayRoll findById(Integer id) {
		return entityManager.find(PayRoll.class, id);
	}

	@Override
	public List<PayRoll> findAll() {

		Query query = entityManager.createQuery("SELECT pr FROM PayRoll pr");

		List<PayRoll> resultList = query.getResultList();

		return resultList;
	}

	@Override
	public Double calculateAmountPlus(Integer id) {

		Double amountPlus = 0.0;

		try {

			Query query = entityManager.createQuery(
					"SELECT emp FROM Employee emp WHERE emp.id=:employee_id")
					.setParameter("employee_id", id);

			Employee employee = (Employee) query.getSingleResult();

			// Try to use JOIN !!!!
			Query query1 = entityManager.createQuery(
					"SELECT job FROM JobUser job WHERE employee.id=:employee")
					.setParameter("employee", id);

			JobUser jobUser = (JobUser) query1.getSingleResult();

			Query query2 = entityManager.createQuery(
					"SELECT job FROM Job job WHERE id=:jobUser_id")
					.setParameter("jobUser_id", jobUser.getJob().getId());

			Job job = (Job) query2.getSingleResult();

			Double baseSalary = job.getSalary();

			Query query3 = entityManager
					.createQuery(
							"SELECT pay FROM PayRoll pay WHERE pay.employee.id=:employee_id")
					.setParameter("employee_id", id);

			PayRoll payRoll = (PayRoll) query3.getSingleResult();

			Double bonus = payRoll.getBonus();
			Double allowance = payRoll.getAllowance();

			amountPlus = baseSalary + bonus + allowance;

			// payRoll.setAmountPlus(amountPlus);
			// entityManager.merge(payRoll);

			// System.out.println("Amount Plus = " + amountPlus);

		} catch (NoResultException e) {
			System.out.println("No result found");
		}

		return amountPlus;
	}

	@Override
	public Double calculateAmountMinus(Integer id) {

		Double amountMinus = 0.0;

		try {

			Query query = entityManager.createQuery(
					"SELECT pay FROM PayRoll pay WHERE pay.employee.id=:id")
					.setParameter("id", id);

			PayRoll payRoll = (PayRoll) query.getSingleResult();

			Double tax;
			if (payRoll.getTax() == null) {
				tax = 0.0;
			} else {
				tax = payRoll.getTax();
			}
			Double syndicateSubscription = payRoll.getSyndicateSubscription();
			Double cnssSubscription = payRoll.getCnssSubscription();
			Double insurenceSubscription = payRoll.getInsurenceSubscription();
			Double otherDeductions = payRoll.getOtherDeductions();

			amountMinus = tax + syndicateSubscription + cnssSubscription
					+ insurenceSubscription + otherDeductions;

			// payRoll.setAmountMinus(amountMinus);
			// entityManager.merge(payRoll);
			// System.out.println("Amount Minus = " + amountMinus);

		} catch (NoResultException e) {
			System.out.println("No result found");
		}
		return amountMinus;
	}

	@Override
	public Double calculateTax(Integer id) {

		Double taxPerMonth = 0.0;

		try {
			Query query1 = entityManager.createQuery(
					"SELECT emp FROM Employee emp WHERE emp.id=:employee_id")
					.setParameter("employee_id", id);

			Employee employee = (Employee) query1.getSingleResult();

			Query query2 = entityManager
					.createQuery(
							"SELECT pay FROM PayRoll pay WHERE pay.employee.id=:employee_id")
					.setParameter("employee_id", id);

			PayRoll payRoll = (PayRoll) query2.getSingleResult();

			Double deductions;

			if (payRoll.getAmountMinus() == null) {
				deductions = 0.0;
			} else {
				deductions = payRoll.getAmountMinus();
			}

			Double annualNetSalary = (12 * payRoll.getAmountPlus())
					+ (2 * payRoll.getAdditionalSalary()) - deductions;

			Double deductionForFamilyStatus = 0.0;
			Double deductionForChildrenNumber = 0.0;

			if (employee.getFamilyStatus() == "Married") {
				deductionForFamilyStatus = 150.0;
			}

			if (employee.getNumberOfchildren() == 1) {
				deductionForChildrenNumber = 90.0;
			} else if (employee.getNumberOfchildren() == 2) {
				deductionForChildrenNumber = 165.0;
			} else if (employee.getNumberOfchildren() == 3) {
				deductionForChildrenNumber = 225.0;
			} else if (employee.getNumberOfchildren() >= 4) {
				deductionForChildrenNumber = 270.0;
			}

			Double tax = annualNetSalary - (0.1 * annualNetSalary)
					- deductionForChildrenNumber - deductionForFamilyStatus;

			taxPerMonth = tax / 12;

			// payRoll.setTax(taxPerMonth);
			// entityManager.merge(payRoll);

			// System.out.println("TAX = " + taxPerMonth);

		} catch (NoResultException e) {
			System.out.println("No result found");
		}

		return taxPerMonth;

	}

}
