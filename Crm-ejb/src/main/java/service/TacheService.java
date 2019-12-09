package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ressource;
import model.Tache;

@Stateless
@LocalBean
public class TacheService implements TacheServiceRemote {
	
	RessourceService reService = new RessourceService();

	@PersistenceContext
	EntityManager em;
	public int AddRessource(Ressource ressource) {
		// TODO Auto-generated method stub
		em.persist(ressource);
		return ressource.getId();
	}
	@Override
	public int AddTache(Tache tache) {
		// TODO Auto-generated method stub
		em.persist(tache);
		return tache.getId();
	}

	@Override
	public void removeTache(int idTache) {
		// TODO Auto-generated method stub
		em.remove(em.find(Tache.class, idTache));
		
	}

	@Override
	public void updateTache(Tache tache) {
		// TODO Auto-generated method stub
//		Tache t = em.find(Tache.class, tache.getId());
//		t.setNom(t.getNom());
//		t.setDateDebut(t.getDateDebut());
//		t.setDateFin(t.getDateFin());
		em.merge(tache);
		
	}

	@Override
	public Tache findTacheById(int idTache) {
		// TODO Auto-generated method stub
		return em.find(Tache.class, idTache);
	}

	@Override
	public List<Tache> findallTache() {
		// TODO Auto-generated method stub
		List<Tache> taches = em.createQuery("select t from Tache t", Tache.class).getResultList();
		return taches;
	}

	@Override
	public void AffecterRessourceATache(int idRessource, int idTache) {
		// TODO Auto-generated method stub
		Tache t = em.find(Tache.class, idTache);
		Ressource r = em.find(Ressource.class, idRessource);
		t.getRessources().add(r);
		r.getTaches().add(t);
		r.setEtat(1);
		em.persist(t);
		em.persist(r);
		em.flush();
		
	}

	@Override
	public void AddAffecterRessourceATache(Tache tache, Ressource ressource) {
		// TODO Auto-generated method stub
		this.AddTache(tache);
		this.AddRessource(ressource);
//		tache.getRessources().add(ressource);
//		ressource.getTaches().add(tache);
//		em.persist(tache);
//		em.persist(ressource);
//		em.flush();
				
	}

}
