package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Employee;

@Remote
public interface IEmployeeServiceRemote {
	Boolean addEmployee(Employee e);
	Boolean updateEmploye(Employee e);
	Boolean deleteEmploye(Employee e);
	Employee findEmployeeById(Integer id);
	List<Employee> findAllEmployees();
	List<Employee> findEmployeeByEmpStatus(String status);
	List<Employee> findEmployeeByDepartement(String nom);
	List<Employee> findEmployeeByEmpStatusAndDepartement(String status,String name);
	Employee findEmployeeByLogin(String login);
}
