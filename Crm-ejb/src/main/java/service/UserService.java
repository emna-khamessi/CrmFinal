package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.UserRemote;
import model.Rdv;
import model.User;



@LocalBean
@Stateless
public class UserService  implements UserRemote{

	@PersistenceContext(unitName="sqlserver_pool")
	EntityManager em ;
	
	
	@Override
	public int addUser(User u) {
		// TODO Auto-generated method stub
		System.out.println(u.getFirstName());
		em.persist(u);
		return 0;
	}

	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u",User.class);
		List<User> users =  new ArrayList<User>();
		users  = query.getResultList();
		return users;
	}

	@Override
	public void updateUser(User u, int id) {
		// TODO Auto-generated method stub
		User up = em.find(User.class, id);
		up.setFirstName(u.getFirstName());
		up.setLastName(u.getLastName());		
		up.setEmail(u.getEmail());
		up.setConfirmPassword(u.getConfirmPassword());				
		up.setImageUrl(u.getImageUrl());
		up.setRole(u.getRole());
		up.setEtat(u.getEtat());
		em.persist(up);
		System.out.println("Updated !!" + up);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		em.remove(em.find(User.class, id));
		System.out.println("Deleted!!!");
		
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		
		TypedQuery<User> query=em.createQuery("select u from User u where u.email =:email AND u.password =:password ",User.class);

		query.setParameter("email", email);
		query.setParameter("password", password);
		System.out.println(email + " - " + password);
		User user= null;
		try {
			System.out.println("Enter Service ");
			user=query.getSingleResult();
			System.out.println("ok");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur : " + e);
		}
		
		return user;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return em.find(User.class, id);
	}
	
	

}
