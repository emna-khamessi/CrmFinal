package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Agent;
import model.PointOfProspection;

@Stateless
@LocalBean
public class PointOfProspectionService implements PointOfProspectionServiceRemote {

	@PersistenceContext
	EntityManager em;
	@Override
	public int AddPointOfProspection(PointOfProspection pointOfProspection) {
		// TODO Auto-generated method stub
		em.persist(pointOfProspection);
		return pointOfProspection.getId();
	}

	@Override
	public void removePointOfProspection(int idPointOfProspection) {
		// TODO Auto-generated method stub
		em.remove(em.find(PointOfProspection.class, idPointOfProspection));

	}

	@Override
	public void updatePointOfProspection(PointOfProspection pointOfProspection) {
		// TODO Auto-generated method stub
		PointOfProspection pop = em.find(PointOfProspection.class, pointOfProspection.getId());
		pop.setNom(pop.getNom());
		pop.setAdress(pop.getAdress());
		pop.setType(pop.getType());
	}

	@Override
	public PointOfProspection findPointOfProspectionById(int idPointOfProspection) {
		// TODO Auto-generated method stub
		return em.find(PointOfProspection.class, idPointOfProspection);
	}

	@Override
	public List<PointOfProspection> findallPointOfProspection() {
		// TODO Auto-generated method stub
		List<PointOfProspection> pointOfProspections = em.createQuery("select p from PointOfProspection p", PointOfProspection.class).getResultList();
		return pointOfProspections;
	}

}
