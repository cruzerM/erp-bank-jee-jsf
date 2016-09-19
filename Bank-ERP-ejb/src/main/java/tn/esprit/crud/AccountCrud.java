package tn.esprit.crud;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.Account;
import tn.esprit.entities.Employee;

@Stateless
public class AccountCrud implements IAccountCrud {

	@PersistenceContext(unitName = "Bank-ERP-ejb")
	EntityManager em;

	@Override
	public void add(Account account) {
		em.persist(account);

	}

	@Override
	public void delete(Account account) {
		Query query = em.createQuery("delete from Account a where a.id = "
				+ account.getId());
		query.executeUpdate();
	}

	@Override
	public void update(Account account) {
		em.merge(account);

	}

	@Override
	public List<Account> findAll() {
		return em.createQuery("select a from Account a", Account.class)
				.getResultList();
	}

	@Override
	public Account findAccountById(Integer accountId) {
		return em.find(Account.class, accountId);
	}

	@Override
	public Employee findEmployeeByLogin(String login) {
		return em.find(Employee.class, login);

	}

	@Override
	public Account authentificate(String login, String pwd) {
		Account account = null;
		Query query = em
				.createQuery("select a from Account a where a.login=:l and a.password=:p");
		query.setParameter("l", login).setParameter("p", pwd);
		try {
			account = (Account) query.getSingleResult();
		} catch (Exception e) {
			account = null;
		}
		return account;
	}

}
