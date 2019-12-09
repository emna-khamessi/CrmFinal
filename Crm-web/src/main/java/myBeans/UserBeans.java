package myBeans;

import java.io.Serializable;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import model.Rdv;
import model.Store;
import model.User;
import services.RdvService;
import services.StoreService;
import services.UserService;
import services.MailUser;

@ManagedBean(name="userBeans")
@SessionScoped
public class UserBeans implements Serializable {
	
	User user;
	Rdv rdv = new Rdv();
	Store store = new Store();
	String email;
	String password;
	String confirmpassword;
	String firtname;
	String lastname;
	List<Store> stores;
	List<Rdv> Rdvs;
	List<Rdv> AllRdvs;
	List<User> AllUsers;
	
	int idStore;
	int idRdv;
	private Date date;
	
	
	
	private Part uploadedFile;
	private String folder = "C:\\Work\\workspace-eclipse-conf\\CrmPI\\CrmPI-web\\src\\main\\webapp\\resources\\upload";
	
	@EJB
	UserService userService = new UserService();
	
	@EJB
	RdvService rdvService = new RdvService();
	
	@EJB
	StoreService storesService = new StoreService();
	
	@EJB
	MailUser servicemail ;
	
	public String login() {
		user = new User();
		user = userService.getUserByEmailAndPassword(email, password);
		FacesContext context = FacesContext.getCurrentInstance();
		if(user != null && user.getEtat() == 1) {
			System.out.println("Enter if COndidtion");
			context.getExternalContext().getSessionMap().put("user", user.getUserId());
			context.getExternalContext().getSessionMap().put("userImg", user.getImageUrl());
			if(user.getRole().toUpperCase().equals("PROSPECT")) {
				context.getExternalContext().getSessionMap().put("admin", "0");
				return "index?faces-redirect=true";
			}else {
				context.getExternalContext().getSessionMap().put("admin", "1");
				return "adminPanel?faces-redirect=true";
			}
			
		}
		return "login";
	}
	
	
	public String register() {
		 String fileName = "";

		 try (InputStream input = uploadedFile.getInputStream()) {
			 fileName = Utils.getFileNameFromPart(uploadedFile);
	         Files.copy(input, new File(folder, fileName).toPath());
		     }
		     catch (IOException e) {
		         e.printStackTrace();
		     }
		 
		 if(password.equals(confirmpassword)) {
			 user = new User();
			 user.setFirstName(firtname);
			 user.setLastName(lastname);
			 user.setPassword(password);
			 user.setEmail(email);
			 user.setConfirmPassword(confirmpassword);
			 user.setImageUrl(fileName);
			 user.setRole("prospect");
			 user.setEtat(0);
			 userService.addUser(user);
		 }
		 return "";
	}
	
	
	public void selectA(AjaxBehaviorEvent event) {
		try {
			store = storesService.getStoreById(idStore);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String createRdv() {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		
		//User From Session
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		int iduser = Integer.valueOf(session.getAttribute("user").toString());
		user = new User();
		user = userService.getUserById(iduser);
		////
		
		rdv.setEtat(0);
		rdv.setDate(new Timestamp(date.getTime()));
		rdv.setUser(user);
		
		store = storesService.getStoreById(store.getStoreId());
		rdv.setStoreBean(store);
		
		rdvService.addRdv(rdv);
		rdv = new Rdv();
		return "rdvList";
	}

	public String displayRdv(Rdv rdv) {
		this.rdv = rdvService.getRdvById(rdv.getId());
		idRdv = this.rdv.getId();
		
		//Rdv -> Session
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("rdv", idRdv);
		////
		return "editRdv";
	}

	public String deleteRdv(int id) {
		rdvService.deleteRdv(id);
		return "rdvList";
	}
	
	
public String editRdv() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		
		//Data Session
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		int iduser = Integer.valueOf(session.getAttribute("user").toString());
		idRdv = Integer.valueOf(session.getAttribute("rdv").toString());
		user = new User();
		user = userService.getUserById(iduser);
		////
		System.out.println("idRdv ----------> "+idRdv);
		
		rdv.setId(idRdv);
		rdv.setEtat(0);
		rdv.setDate(new Timestamp(date.getTime()));
		rdv.setUser(user);
		
		
		
		store = storesService.getStoreById(store.getStoreId());
		rdv.setStoreBean(store);
		
		rdvService.updateRdv(rdv,idRdv);
		rdv = new Rdv();
		return "rdvList";
	}
	

public String refuseRdv(Rdv rdv) {
	rdv.setEtat(-1);
	rdvService.updateRdv(rdv,rdv.getId());
	return "";
}

public String accepterRdv(Rdv rdv) {
	rdv.setEtat(1);
	rdvService.updateRdv(rdv,rdv.getId());

	return "";
}


public String promoteUser(User usr) {
	usr.setRole("admin");
	userService.updateUser(usr,usr.getUserId());
	return "";
}

public String depromoteUser(User usr) {
	usr.setRole("prospect");
	userService.updateUser(usr,usr.getUserId());
	return "";
}




public String activeCompte(User usr) throws AddressException, MessagingException {
	System.out.println(usr.getUserId() +" - " + usr.getEtat());
	usr.setEtat(1);
	System.out.println(usr.getUserId() +" - " + usr.getEtat());
	userService.updateUser(usr,usr.getUserId());
	servicemail.sendEmail("emna.kh3010@gmail.com", usr.getEmail(), "Activation",
			"your subscription is approaved!");
	return "";
}

public String desactiveCompte(User usr) {
	System.out.println(usr.getUserId() +" - " + usr.getEtat());
	usr.setEtat(0);
	System.out.println(usr.getUserId() +" - " + usr.getEtat());
	userService.updateUser(usr,usr.getUserId());
	return "";
}

public boolean checkRole(User user) {
	return user.getRole().toUpperCase().equals("ADMIN");
}

	
public String checkLogin() {
	try {
	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	HttpSession session = request.getSession();
	int type = Integer.valueOf(session.getAttribute("admin").toString());
		if(type == 0) {
			return "index";
		}else if(type == 1){
			return "adminPanel";
		}
	}catch(Exception e) {
		return "login";
	}
    return null;
}

public String checkSession(){
	try {
	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	HttpSession session = request.getSession();
	int type = Integer.valueOf(session.getAttribute("admin").toString());
		if(type == 1) {
			return "adminPanel";
		}
	}catch(Exception e) {
		return "login";
	}
    return null;
}


public String checkAdmin() {
	try {
	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	HttpSession session = request.getSession();
	int type = Integer.valueOf(session.getAttribute("admin").toString());
		if(type == 0) {
			return "index";
		}
	}catch(Exception e) {
		return "login";
	}
    return null;
}

	public String imgUser() {
		String img;
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			img = session.getAttribute("userImg").toString();
		}catch(Exception e) {
			img = "default.jpg";
		}
		return img;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=true";
	}
	
	
	public String goToUsersList() {
		
		return "adUserList?faces-redirect=true";
	}
	
	public String GoToAppointmentsList() {
		
		return "adRdvList?faces-redirect=true";
	}
	
public String GoToRdv() {
		
		return "createRdv?faces-redirect=true";
	}
	
	//////////////////////////////
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getFirtname() {
		return firtname;
	}
	public void setFirtname(String firtname) {
		this.firtname = firtname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Part getUploadedFile() {
		return uploadedFile;
	}
	 
	public void setUploadedFile(Part uploadedFile) {
		 this.uploadedFile = uploadedFile;
	 }


	public List<Store> getStores() {
		System.out.println(" Nbr Stores --> "+ storesService.getAllStore().size());
		return stores = storesService.getAllStore();
	}


	public Store getStore() {
		return store;
	}


	public void setStore(Store store) {
		this.store = store;
	}


	public int getIdStore() {
		return idStore;
	}


	public void setIdStore(int idStore) {
		this.idStore = idStore;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date13) {
		this.date = date13;
	}


	public Rdv getRdv() {
		return rdv;
	}


	public void setRdv(Rdv rdv) {
		this.rdv = rdv;
	}


	public List<Rdv> getRdvs() {
		//User From Session
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		int iduser = Integer.valueOf(session.getAttribute("user").toString());
		////
		return Rdvs = rdvService.getAllRdvByUser(iduser);
	}

	public List<Rdv> getAllRdvs() {
		return AllRdvs = rdvService.getAllRdv();
	}


	public int getIdRdv() {
		return idRdv;
	}


	public void setIdRdv(int idRdv) {
		this.idRdv = idRdv;
	}


	public List<User> getAllUsers() {
		return AllUsers = userService.getAllUsers();
	}


	
	
}
