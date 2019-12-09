package tn.esprit.managedbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.servlet.http.Part;

import model.Admin;
import model.Event;
import model.Salle;
import model.User;
import services.EventService;
import services.SalleService;
import utils.Util;

@ManagedBean
@SessionScoped
public class EventBean {
	
	private int eventId;
	private String dateBegin;
	private String dateEnd;
	private String imageUrl;
	private String name;
	private int nbrPlaces;
	private int nbrPlaceVendu;
	private Admin admin;
	private Salle salle;
	private List<User> users;
	private Integer eventIdToBeUpdate;
	private List<Event> events;
	private Event event;
	public boolean loggedIn;
	public int password;
	public int login;
	private List<Salle> salles;
	private int selectedSalleId;
	private Part file;
	
	
	
	@EJB
	EventService eventService;
	
	@EJB
	SalleService salleService;
	
	public String addEvent(int adminId) throws IOException {
		String navigateTo = "null";
		Event e = new Event(dateBegin , dateEnd , file.getSubmittedFileName() , name , nbrPlaces  );
		String folderName1 = Util.serverI;
		try {
			uploadimage(folderName1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Admin SelectedAd = new Admin();
		SelectedAd.setAdminId(adminId);
		e.setAdmin(SelectedAd);
		Salle SelectedSalle = new Salle();
		SelectedSalle.setSalleId(selectedSalleId);
		e.setSalle(SelectedSalle);
		
		eventService.ajouterEvent(e,selectedSalleId);
	
		navigateTo ="Admin/Index.xhtml?faces-redirect=true";
		
		return navigateTo;
		
	}
	
	
	
	
	
	
	
	public List<Event>getEvents(){
		events = eventService.getAllEvents();
		return events;
	}
	
	
	public String getUserss(int eventId) {
		users = eventService.getListUSers(eventId);
		return "/Admin/userrr?faces-redirect=true";
	}
	


	@PostConstruct
	public void init() {
		salles = eventService.displaySalles();
	}



	
	public String gomodif(Event e) {
		
		String navigatorTo = "null";
		
		this.setDateBegin(e.getDateBegin());
		this.setDateEnd(e.getDateEnd());
		this.setImageUrl(e.getImageUrl());
		this.setName(e.getName());
		this.setNbrPlaces(e.getNbrPlaces());
		this.setSalle(e.getSalle());
		this.setEventIdToBeUpdate(e.getEventId());
		
		navigatorTo= "/Admin/updateEvent.xhtml?faces-redirect=true";
		return navigatorTo;
	
	}
	public String mettreAjourEvent() throws IOException{
		String navigatorTo = "null";
		
		Event e = new Event(eventIdToBeUpdate , dateBegin , dateEnd , file.getSubmittedFileName() , name , nbrPlaces);
		String folderName1 = Util.serverI;
		try {
			uploadimage(folderName1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Salle SelectedSalle = new Salle();
		SelectedSalle.setSalleId(selectedSalleId);
		e.setSalle(SelectedSalle);
		eventService.updateEvent(e);
		//eventService.updateEvent(new Event(eventIdToBeUpdate , dateBegin , dateEnd , imageUrl , name , nbrPlaces ));
		 navigatorTo= "/Admin/Index.xhtml?faces-redirect=true";
		return navigatorTo;
		
		}
	
public String detail(Event e) {
		
		String navigatorTo = "null";
		
		this.setDateBegin(e.getDateBegin());
		this.setDateEnd(e.getDateEnd());
		this.setImageUrl(e.getImageUrl());
		this.setName(e.getName());
		this.setNbrPlaces(e.getNbrPlaces());
		this.setSalle(e.getSalle());
		this.setEventIdToBeUpdate(e.getEventId());
		
		navigatorTo= "/Client/Details.xhtml?faces-redirect=true";
		return navigatorTo;
		
		
	
	}
	
	
	
	
	public void supprimer(Integer eventId){
		eventService.deleteEvent(eventId);
		}
	
	public String addP(Integer eventId,Integer userId) throws ParseException {
		String navigatorTo = "null";
		if(eventService.addPar(eventId ,userId))
	
		{navigatorTo= "/Client/IndexC.xhtml?faces-redirect=true";} 
		else {
			navigatorTo= "/Client/dateend.xhtml?faces-redirect=true";
			
		}
			
			return navigatorTo;
		
	}
	
	
	
	public String doLogin() {
		String navigateTo = "null";
		//getEmployeByLoginAndPwd
		
		admin = eventService.getAdminByLoginAndPassword(login, password);
		System.out.println(admin.getLogin()+"llllllllllllllllllllllllllllllllllllllllll");

		if (admin != null /*&& employe.getRole() == Role.administrateur*/) {
			navigateTo = "/Admin/WelcomeAdmin?faces-redirect=true";
			loggedIn = true;
		} else {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("bad credentials"));
		}
		return navigateTo;
	}
	
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Admin/login?faces-redirect=true";
	}
	
	public String doBack() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Admin/WelcomeAdmin?faces-redirect=true";
	}
	
	public String doBackI() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Admin/Index?faces-redirect=true";
	}
	
	public String doBackIC() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Client/IndexC?faces-redirect=true";
	}
	public String doBackW() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Admin/WelcomeAdmin?faces-redirect=true";
	}
	
//////////////////image
public String uploadimage(String folderName1) throws IOException, Exception {
		
		if (file != null) {
		
		InputStream in = file.getInputStream();
		File f = new File(folderName1 + "\\" + file.getSubmittedFileName());
		f.createNewFile();
		FileOutputStream out = new FileOutputStream(f);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		out.close();
		in.close();
		return "succes-image-uplaod?faces-redirect=true";
		}
		else {
			return "succes-image-uplaod?faces-redirect=true";	
		}	
	}
	
	
	
	
///////////////////////////////get/set
	public String getDateBegin() {
		return dateBegin;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}


	public int getPassword() {
		return password;
	}


	public void setPassword(int password) {
		this.password = password;
	}


	public int getLogin() {
		return login;
	}


	public void setLogin(int login) {
		this.login = login;
	}


	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbrPlaces() {
		return nbrPlaces;
	}

	public void setNbrPlaces(int nbrPlaces) {
		this.nbrPlaces = nbrPlaces;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	
	public EventService getEventService() {
		return eventService;
	}

	
	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public int getEventIdToBeUpdate() {
		return eventIdToBeUpdate;
	}



	public void setEventIdToBeUpdate(Integer eventIdToBeUpdate) {
		this.eventIdToBeUpdate = eventIdToBeUpdate;
	}


	public List<Salle> getSalles() {
		return salles;
	}


	public void setSalles(List<Salle> salles) {
		this.salles = salles;
	}


	public int getSelectedSalleId() {
		return selectedSalleId;
	}


	public void setSelectedSalleId(int selectedSalleId) {
		this.selectedSalleId = selectedSalleId;
	}


	public Part getFile() {
		return file;
	}


	public void setFile(Part file) {
		this.file = file;
	}
	

	
public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}

	

	

}
