package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Employee;
import tn.esprit.entities.PayRoll;

@Remote
public interface IPayRollServices {

	void add(PayRoll payRoll);
	void delete(PayRoll payRoll);
	void update(PayRoll payRoll);
	PayRoll findById(Integer id);
	List<PayRoll> findAll();
	
	Double calculateAmountPlus(Integer id);
	Double calculateAmountMinus(Integer id);
	Double calculateTax(Integer id);
	
}
