package tn.esprit.managedbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.User;
import services.ClientService;

@ManagedBean
@SessionScoped
public class LoginBean {
	
	private String password;
	private String email;
	public boolean loggedIn;
	public User user;
	
	@EJB
	ClientService clientService;
	
	
	public String doLoginC() {
		String navigateTo = "null";
		user = clientService.getUserByLoginAndPassword(email, password);
		System.out.println(user.getEmail()+"llllllllllllllllllllllllllllllllllllllllll");

		if (user != null /*&& employe.getRole() == Role.administrateur*/) {
			navigateTo = "/Client/IndexC?faces-redirect=true";
			loggedIn = true;
		} else {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("bad credentials"));
		}
		return navigateTo;
	}
	
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Client/loginC?faces-redirect=true";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
