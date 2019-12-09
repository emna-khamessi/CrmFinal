package service;

import java.util.List;

import javax.ejb.Remote;

import model.Ressource;

@Remote
public interface RessourceServiceRemote {

	public int AddRessource(Ressource ressource);
	public void removeRessource(int ididRessource);
	public void updateRessource(Ressource ressource);
	public Ressource findRessourceById(int ididRessource);
	public List<Ressource> findallRessources();
}
