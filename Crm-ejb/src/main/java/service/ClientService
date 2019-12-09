package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.ClientServiceRemote;
import model.Admin;
import model.User;

@Stateless
@LocalBean
public class ClientService implements ClientServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	

	@Override
	public User getUserByLoginAndPassword(String email, String password) {
		TypedQuery<User> query = em.createQuery("SELECT e from User e WHERE e.email=:email AND e.password=:password" ,
				User.class	);
		query.setParameter("email", email);
		query.setParameter("password", password);
		User user = null;
		try { user = query.getSingleResult();}
		catch (Exception e) {System.out.println("erreur:" + e);}
			return user;
	}
	

}
