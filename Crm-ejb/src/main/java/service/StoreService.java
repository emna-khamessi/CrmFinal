package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interfaces.StoreRemote;
import model.Store;
import model.User;

@LocalBean
@Stateless
public class StoreService implements StoreRemote{

	@PersistenceContext(unitName="sqlserver_pool")
	EntityManager em ;
	
	@Override
	public List<Store> getAllStore() {
		List<Store>st=em.createQuery("SELECT s FROM Store s",Store.class).getResultList();
		List<Store> stores = new ArrayList<Store>();
		for(Store s : st ) {
			stores.add(s);
			}
		return stores;
	}

	@Override
	public Store getStoreById(int id) {
		return em.find(Store.class, id);
	}

}
