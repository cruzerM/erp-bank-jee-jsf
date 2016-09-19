package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Employee;

@Local
public interface IEmployeeServiceLocal {
	Boolean addEmployee(Employee e);
	Employee findEmployeeById(Integer id);
	List<Employee> findAllEmployees();
	Employee findEmployeeByLogin(String login);
}
