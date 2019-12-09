package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interf.AviServiceRemote;
import model.Avi;

@Stateless
@LocalBean
public class AviService implements AviServiceRemote  {
	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public int AddAvi(Avi e) {
		em.persist(e);
		return 1;
	}

	@Override
	public List<Avi> getAllAvi() {
		List<Avi> evts = em.createQuery("Select e from Avi e",
				Avi.class).getResultList();
				return evts;
	}

	@Override
	public void deleteAviById(int AviId) 
		
		{Avi e = em.find(Avi.class,AviId);
		em.remove(e);
	}

	@Override
	public void updateAvi(Avi e) {
		em.merge(e);
		
	}
}
