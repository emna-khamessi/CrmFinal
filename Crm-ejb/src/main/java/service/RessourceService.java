package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ressource;

@Stateless
@LocalBean
public class RessourceService implements RessourceServiceRemote {

	@PersistenceContext
	EntityManager em;
	@Override
	public int AddRessource(Ressource ressource) {
		// TODO Auto-generated method stub
		em.persist(ressource);
		return ressource.getId();
	}

	@Override
	public void removeRessource(int idRessource) {
		// TODO Auto-generated method stub
		em.remove(em.find(Ressource.class, idRessource));
		
	}

	@Override
	public void updateRessource(Ressource ressource) {
		// TODO Auto-generated method stub
		Ressource res = em.find(Ressource.class, ressource.getId());
		res.setNom(res.getNom());
		res.setType(res.getType());
		res.setQuantite(res.getQuantite());
		res.setEtat(res.getEtat());	
	}

	@Override
	public Ressource findRessourceById(int idRessource) {
		// TODO Auto-generated method stub
		return em.find(Ressource.class, idRessource);
	}

	@Override
	public List<Ressource> findallRessources() {
		// TODO Auto-generated method stub
		List<Ressource> ressources = em.createQuery("select r from Ressource r", Ressource.class).getResultList();
		return ressources;
	}

}
