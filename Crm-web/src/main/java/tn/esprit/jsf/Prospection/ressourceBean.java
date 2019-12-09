package tn.esprit.jsf.Prospection;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Agent;
import model.Ressource;
import model.Tache;
import service.RessourceService;

@ManagedBean
@SessionScoped
public class ressourceBean implements Serializable {
	
	private int ressourceId;
	private int etat;
	private String nom;
	private String type;
	private int RessourceIdToBeUpdate;
	private List<Ressource> ressources;
	@EJB
	RessourceService ressourceService;
	
	public void addRessource() 
	{
		Ressource ressource = new Ressource();
		ressource.setNom(nom);
		ressource.setType(type);
		ressourceService.AddRessource(ressource);
		System.out.println("Ressource Added");
			if(ressource!=null) {
			String navigateTo= "/Ressource/afficheRessource?faces-redirect=true";
			}
		//return "http://localhost:9080/Crm-web/Agent/afficheAgents.jsf";
	}
	public void deleteAgent(int idRessource)
	{
		ressourceService.removeRessource(idRessource);
	}
	public void modifier(Ressource ressource) {		
		this.setNom(ressource.getNom());
		this.setType(ressource.getType());
		this.setRessourceIdToBeUpdate(ressource.getId());
	
	}	
	public void mettreAjourRessource() 
	{
		ressourceService.updateRessource(new Ressource(RessourceIdToBeUpdate, nom, type));
	}
	public int getRessourceId() {
		return ressourceId;
	}
	public void setRessourceId(int ressourceId) {
		this.ressourceId = ressourceId;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public RessourceService getRessourceService() {
		return ressourceService;
	}
	public void setRessourceService(RessourceService ressourceService) {
		this.ressourceService = ressourceService;
	}
	public int getRessourceIdToBeUpdate() {
		return RessourceIdToBeUpdate;
	}
	public void setRessourceIdToBeUpdate(int ressourceIdToBeUpdate) {
		RessourceIdToBeUpdate = ressourceIdToBeUpdate;
	}
	public List<Ressource> getRessources()
	{
		ressources = ressourceService.findallRessources();
		return ressources;
	}
	
}
