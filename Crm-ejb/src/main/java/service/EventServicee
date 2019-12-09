package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.EventServiiceRemote;
import model.Admin;
import model.Event;
import model.Salle;
import model.User;

@Stateless
@LocalBean
public class EventService implements EventServiiceRemote {
	@PersistenceContext
	EntityManager em;
	
	@Override
	public int ajouterEvent(Event event , int selectedSalleId) {
		
		Salle s=em.find(Salle.class,selectedSalleId);
		if(s.getQuapacite()<event.getNbrPlaces())
		{
			System.out.println("capacite de salle inf au nbre de pllace d event !!");

			return -1 ;
		}
		String sDate1= event.getDateBegin();
		Date d=new Date(); 
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		if(d.compareTo(date1) > 0) {
			System.out.println("erreurAjout:date d'event < date systeme" );
			
		}
		else {
		em.persist(event);}
		return event.getEventId();
	}
	@Override
	public Event getEventById(int id) {
		// TODO Auto-generated method stub
		return em.find(Event.class, id);
	}
	
	@Override
	public List<Event> getAllEvents(){
		//return(em.createQuery("select c from Event c ",Event.class).getResultList());
		List<Event> evt = em.createQuery("select c from Event c ",Event.class).getResultList();
		return evt;
	}
	
	@Override
	public void deleteEvent(int eventId) {
		Event event =em.find(Event.class, eventId);
		for(User us : event.getUsers()) {
			us.getEvents().remove(event);
		}
		em.remove(event);
		}
	
	@Override
	public void updateEvent(Event e) {
		String sDate1= e.getDateBegin();
		Date d=new Date(); 
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} catch (ParseException t) {
			// TODO Auto-generated catch block
			t.printStackTrace();
		}   
		if(d.compareTo(date1) > 0) {
			System.out.println("erreurUdate: date d'event < date systeme" );
			
		}
		else {
			em.merge(e);}
        
	}
	
	@Override
	public Admin getAdminByLoginAndPassword(int login , int password) {
		
		TypedQuery<Admin> query = em.createQuery("SELECT e from Admin e WHERE e.login=:login AND e.password=:password" ,
				Admin.class	);
		query.setParameter("login", login);
		query.setParameter("password", password);
		Admin admin = null;
		try { admin = query.getSingleResult();}
		catch (Exception e) {System.out.println("erreur:" + e);}
			return admin;
	}
	
	@Override
	public boolean addPar(int eventId , int userId ) throws ParseException  {
		
		Event event = em.find(Event.class, eventId);
		User user = em.find(User.class , userId );
	if(!event.getUsers().contains(user))
	{
		String sDate1= event.getDateBegin();
		System.out.println(eventId+"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		Date d=new Date(); 
		System.out.println(d+"systeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeme"); 
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		System.out.println(sDate1+"mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"+date1);	   
		if ((d.compareTo(date1) < 0) && (event.getNbrPlaces()>event.getNbrPlaceVendu()))  {
			event.setNbrPlaceVendu(event.getNbrPlaceVendu()+1);
			user.getEvents().add(event) ;
			em.persist(user);
			return true  ;
			
		
		} else {
			event.setNbrPlaceVendu(event.getNbrPlaceVendu());
			
			
		}	
		
		

	}
		
	return false ;
		

	}
	@Override
	public List<Salle> displaySalles() {
		List<Salle> sal = em.createQuery("select c from Salle c ",Salle.class).getResultList();
		return sal;
	}
	
	
	
	@Override
	public List<User> getListUSers(int eventId) {
		Event event=em.find(Event.class, eventId);
		List<User> use = em.createQuery("select c from User c ",User.class).getResultList();
		return use ;
	}
	
	
	

}
