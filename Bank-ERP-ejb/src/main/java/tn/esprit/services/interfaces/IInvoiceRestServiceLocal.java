package tn.esprit.services.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Invoice;

@Local
public interface IInvoiceRestServiceLocal {

	Boolean addInvoice(Invoice invoice);
	
	ArrayList<Invoice> getAllInvoices();
	
	Invoice getInvoice(int idInvoice);

	Boolean executeInvoice(Integer idInvoice);
}
