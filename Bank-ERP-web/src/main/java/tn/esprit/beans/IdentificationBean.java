package tn.esprit.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import tn.esprit.entities.Account;
import tn.esprit.enumerators.RoleType;
import tn.esprit.services.interfaces.AccountServiceRemote;
import util.SessionBean;

@ManagedBean(name = "authBean")
@SessionScoped
public class IdentificationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Model
	private String login;
	private String pwd;
	private Account account;
	private RoleType roles;

	// Injection of proxy
	@EJB
	private AccountServiceRemote asr;

	public String doLogin() {
		account = new Account();
		account = asr.authentificate(login, pwd);
		
		if (account != null) {
			if (account.getRole().equals(RoleType.StockManager)){
				
				HttpSession session = SessionBean.getSession();
				session.setAttribute("user", account);
				return "/provider/home?faces-redirect=true";
			} else if(account.getRole().equals(RoleType.MarketingManager)){
				
				HttpSession session = SessionBean.getSession();
				session.setAttribute("user", account);
				return "/marketing/home?faces-redirect=true";
			}else{
				
				account.setIsConnected(false);
				asr.update(account);
				account = null;
				FacesContext.getCurrentInstance().addMessage(
						"loginForm:cmdLogin",
						new FacesMessage("You don't have permession !!"));
				return "/login?faces-redirect=true";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"loginForm:cmdLogin",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "/login?faces-redirect=true";
		}

	}

	public String doLogOut() {

		account.setIsConnected(false);
		asr.update(account);
		account = null;

		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();

		return "/login?faces-redirect=true";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public RoleType getRoles() {
		return roles;
	}

	public void setRoles(RoleType roles) {
		this.roles = roles;
	}
}
