package service;

import java.util.List;

import javax.ejb.Remote;

import model.Ressource;
import model.Tache;

@Remote
public interface TacheServiceRemote {

	public int AddTache(Tache tache);
	public int AddRessource(Ressource ressource);
	public void removeTache(int idTache);
	public void updateTache(Tache tache);
	public Tache findTacheById(int idTache);
	public List<Tache> findallTache();
	public void AffecterRessourceATache(int idRessource, int idTache);
	public void AddAffecterRessourceATache(Tache tache, Ressource ressource);
}
