package tn.esprit.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import tn.esprit.entities.Account;
import tn.esprit.entities.Provider;
import tn.esprit.enumerators.RoleType;
import tn.esprit.services.interfaces.ProviderServiceLocal;
import util.SessionBean;

@ManagedBean
@SessionScoped
public class ProviderBean {

	//Models
	private Provider provider = new Provider ();
	private Provider providerSelected = new Provider();
	private List<Provider> providers = new ArrayList<Provider>();
	
	//Injection of proxy
	@EJB
	ProviderServiceLocal providerServiceLocal;
	
	@PostConstruct
	public void init() {
		Account account = new Account();
		HttpSession session = SessionBean.getSession();
		account = (Account) session.getAttribute("user");
		
		if(!account.getRole().equals(RoleType.StockManager)){
			System.out.println("Account: " + account.getLogin());
			dislayErrorPage();
			System.out.println("error page: " + dislayErrorPage());
			
		}
	}
	
	public String dislayErrorPage() {
		return "/permissionError?faces-redirect=true";
	}
	
	public String dislayAddPage() {
		return "/addProvider?faces-redirect=true";
	}
	
	public String doAddProvider(){
		providerServiceLocal.createProvider(provider);
		return "/indexProvider?faces-redirect=true";
	}
	
	public String dislayUpdatePage() {
		return "/updateProvider?faces-redirect=true";
	}
	
	public String doUpdateProvider() {
		providerServiceLocal.saveProvider(providerSelected);
		return "/indexProvider?faces-redirect=true";
	}
	
	public String cancel() {
		providerSelected = new Provider();
		return "/indexProvider?faces-redirect=true";
	}
	
	public String doDeleteProvider(){
		providerServiceLocal.removeProvider(providerSelected);
		return "/indexProvider?faces-redirect=true";
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public List<Provider> getProviders() {
		providers = providerServiceLocal.findAllProviders();
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public Provider getProviderSelected() {
		return providerSelected;
	}

	public void setProviderSelected(Provider providerSelected) {
		this.providerSelected = providerSelected;
	}
	
}
