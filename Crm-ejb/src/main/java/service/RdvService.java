package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.RdvRemote;
import model.Rdv;
import model.Store;
import model.User;

@LocalBean
@Stateless
public class RdvService implements RdvRemote{

	
	@PersistenceContext(unitName="sqlserver_pool")
	EntityManager em ;
	
	@Override
	public int addRdv(Rdv u) {
		System.out.println(u.getDescription());
		em.persist(u);
		return 0;
	}

	@Override
	public List<Rdv> getAllRdvByUser(int id) {
		TypedQuery<Rdv> query = em.createQuery("SELECT r FROM Rdv r WHERE r.user.userId =:id",Rdv.class);
		System.out.println("User From Session --> "+id);
		query.setParameter("id", id);
		List<Rdv> rdv =  new ArrayList<Rdv>();
		rdv  = query.getResultList();
		System.out.println("Rdv List Id -> "+rdv.size());
		return rdv;
	}

	@Override
	public void updateRdv(Rdv u, int id) {
		System.out.println(u.getDescription() +" - "+id);
				Rdv up = em.find(Rdv.class, id);
				up.setDescription(u.getDescription());
				up.setDate(u.getDate());
				up.setEtat(u.getEtat());
				up.setUser(u.getUser());
				up.setStoreBean(u.getStoreBean());
				//em.persist(up);
				System.out.println("Updated !!" + up);
		
	}

	@Override
	public void deleteRdv(int id) {
		em.remove(em.find(Rdv.class, id));
		System.out.println("Deleted!!!");
	}

	@Override
	public Rdv getRdvByUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Rdv> getAllRdv() {
		TypedQuery<Rdv> query = em.createQuery("SELECT r FROM Rdv r",Rdv.class);
		List<Rdv> rdv =  new ArrayList<Rdv>();
		rdv  = query.getResultList();
		return rdv;
	}

	@Override
	public Rdv getRdvById(int id) {
		return em.find(Rdv.class, id);
	}

}
