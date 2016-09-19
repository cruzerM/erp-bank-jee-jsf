package tn.esprit.crud;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Account;
import tn.esprit.entities.Employee;

@Local
public interface IAccountCrud {
	void add(Account account);

	void delete(Account account);

	void update(Account account);

	List<Account> findAll();

	Account findAccountById(Integer accountId);

	Employee findEmployeeByLogin(String login);

	Account authentificate(String login, String pwd);
}
