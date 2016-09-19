package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import tn.esprit.entities.Account;
import tn.esprit.entities.Employee;


@Remote
public interface AccountServiceRemote {
	
	Boolean add(Account account);

	Boolean delete(Account account);

	Boolean update(Account account);

	List<Account> findAll();

	Account findAccountById(Integer accountId);
	
	Account findAccountByLogin(String login);

	Employee findEmployeeByLogin(String login);

	
	
	Account authentificate(String login, String pwd);
	
	
	Boolean signOut(String login);
}
