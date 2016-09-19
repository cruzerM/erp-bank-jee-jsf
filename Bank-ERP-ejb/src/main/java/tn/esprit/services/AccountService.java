package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaUpdate;

import tn.esprit.entities.Account;
import tn.esprit.entities.Employee;
import tn.esprit.services.interfaces.AccountServiceRemote;

@WebService
@Stateless
public class AccountService implements AccountServiceRemote {

	@PersistenceContext
	EntityManager em;

	@WebMethod(exclude=true)
	@Override
	public Boolean add(Account account) {

		Boolean result = false;

		try {
			em.persist(account);
			result = true;
		} catch (Exception e) {
			System.out.println("Persist Error: " + e.getMessage());
		}
		return result;
	}

	@WebMethod(exclude=true)
	@Override
	public Boolean delete(Account account) {
		Query query = em.createQuery("delete from Account a where a.id = "
				+ account.getId());
		Boolean result = false;

		try {
			query.executeUpdate();
			result = true;
		} catch (Exception e) {
			System.out.println("Persist Error: " + e.getMessage());
		}
		return result;
	}

	@WebMethod(exclude=true)
	@Override
	public Boolean update(Account account) {
		Boolean result = false;

		try {
			em.merge(account);
			result = true;
		} catch (Exception e) {
			System.out.println("Persist Error: " + e.getMessage());
		}
		return result;
	}

	@WebMethod(exclude=true)
	@Override
	public List<Account> findAll() {
		return em.createQuery("select a from Account a", Account.class)
				.getResultList();
	}

	@WebMethod(exclude=true)
	@Override
	public Account findAccountById(Integer accountId) {
		return em.find(Account.class, accountId);
	}

	@WebMethod(exclude=true)
	@Override
	public Employee findEmployeeByLogin(String login) {
		return em.find(Employee.class, login);

	}
	
	@WebResult(name="isSignedIn")
	@WebMethod(operationName="SignIn")
	@Override
	public Account authentificate(@WebParam(name="login")String login, @WebParam(name="pwd")String pwd) {
		Account account = null;
		Query query = em
				.createQuery("select a from Account a where a.login=:l and a.password=:p");
		query.setParameter("l", login).setParameter("p", pwd);
		try {
			account = (Account) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("No Account: " + e.getMessage());
			return null;
		}

		if (account.getIsConnected()) {
			return null;
		} else{
			
			account.setIsConnected(true);
			try {
				em.merge(account);
			} catch (Exception e) {
				System.out.println("Set isConnected Issue: " + e.getMessage());
				return null;
			}
			return account;
		}
			
	}


	@WebMethod(exclude=true)
	@Override
	public Account findAccountByLogin(String login) {
		Account account = null;
		Query q = em.createQuery("select a from Account a where a.login=:l");
		q.setParameter("l", login);
		try {
			account = (Account) q.getSingleResult();
		} catch (Exception e) {
			account=null;
			System.out.println("Find By Login Issue: " + e.getMessage());
		}
		
		return account;
	}
	
	@WebResult(name="isSignedOut")
	@WebMethod(operationName="SignOut")
	@Override
	public Boolean signOut(@WebParam(name="login")String login) {
		
		Account a = findAccountByLogin(login);
		
		if(a!=null){
			
			a.setIsConnected(false);
			if(update(a)){
				return true;
			}
			else
				return false;
		}
		else
			return false;
		
		
	}

}
