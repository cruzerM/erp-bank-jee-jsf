package tn.esprit.beans;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.entities.Consumer;
import tn.esprit.services.interfaces.IConsumerServiceRemote;

@ApplicationScoped
@ManagedBean(name = "consumermb")
public class CustomerMB {
  
	Consumer c =new Consumer();
	@EJB
	IConsumerServiceRemote consumerservice;
	

}
