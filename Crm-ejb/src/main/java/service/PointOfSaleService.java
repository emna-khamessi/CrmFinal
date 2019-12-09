package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Agent;
import model.PointOfSale;

@Stateless
@LocalBean
public class PointOfSaleService implements PointOfSaleServiceRemote {

	@PersistenceContext
	EntityManager em;
	@Override
	public int AddPointOfSale(PointOfSale pointOfSale) {
		// TODO Auto-generated method stub
		em.persist(pointOfSale);
		return pointOfSale.getId();
	}

	@Override
	public void removePointOfSale(int idPointOfSale) {
		// TODO Auto-generated method stub
		em.remove(em.find(PointOfSale.class, idPointOfSale));

	}

	@Override
	public void updatePointOfSale(PointOfSale pointOfSale) {
		// TODO Auto-generated method stub
		PointOfSale pos = em.find(PointOfSale.class, pointOfSale.getId());
		pos.setNom(pos.getNom());
		pos.setAdress(pos.getAdress());
		pos.setType(pos.getType());
		pos.setPhone(pos.getPhone());
		
	}

	@Override
	public PointOfSale findPointOfSaleById(int idPointOfSale) {
		// TODO Auto-generated method stub
		return em.find(PointOfSale.class, idPointOfSale);
	}

	@Override
	public List<PointOfSale> findallPointOfSale() {
		// TODO Auto-generated method stub
		List<PointOfSale> pointOfSales = em.createQuery("select p from PointOfSale p", PointOfSale.class).getResultList();
		return pointOfSales;
	}

}
