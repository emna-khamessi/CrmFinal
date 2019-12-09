package tn.esprit.jsf.Prospection;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import model.Agent;
import model.PointOfProspection;
import model.PointOfSale;
import model.Tache;
import service.AgentService;
import service.PointOfProspectionService;
import service.PointOfSaleService;
import service.TacheService;

@ManagedBean
@SessionScoped
public class agentBean implements Serializable {
	
	private String nom;
	private String prenom;
	private String email;
	private int numTel;
	private int status;
	private List<Agent> agents;
	private List<Agent> agentStatus;
	private int agentIdToBeUpdate;
	private Tache tache;
	private int idtache;
	private int idpos;
	private int idposale;
	public int getIdposale() {
		return idposale;
	}
	public void setIdposale(int idposale) {
		this.idposale = idposale;
	}
	public int getIdpos() {
		return idpos;
	}
	public void setIdpos(int idpos) {
		this.idpos = idpos;
	}
	public int getIdtache() {
		return idtache;
	}
	public void setIdtache(int idtache) {
		this.idtache = idtache;
	}
	public Tache getTache() {
		return tache;
	}
	public void setTache(Tache tache) {
		this.tache = tache;
	}
	private int idMission;
	@EJB
	AgentService agentService;
	
	@EJB 
	TacheService tacheService;
	
	@EJB
	PointOfProspectionService posService;
	
	@EJB
	PointOfSaleService posaleService;
	
	public String addAgent()  throws AddressException,MessagingException
	{
		Agent agent = new Agent();
		agent.setNom(nom);
		agent.setPrenom(prenom);
		agent.setEmail(email);
		agent.setNumTel(numTel);
		agent.setTache(tache);
		agentService.addAgent(agent);
		System.out.println("Agent Added");
			//if(agent!=null) {
			//String navigateTo= "/Agent/afficheAgents?faces-redirect=true";
			//}
		return "/Agent/afficheAgents?faces-redirect=true";
	}
	public void deleteAgent(int idAgent)
	{
		agentService.removeAgent(idAgent);
	}
	public String modifier(Agent agent) {		
		this.setNom(agent.getNom());
		this.setPrenom(agent.getPrenom());
		this.setEmail(agent.getEmail());
		this.setNumTel(agent.getNumTel());
		this.setAgentIdToBeUpdate(agent.getId());
		 	return "/Agent/EditAgent?faces-redirect=true";
	
	}	
	public String mettreAjourAgent() 
	{
		agentService.updateAgent(new Agent(agentIdToBeUpdate,email, nom, numTel, prenom));
		return "/Agent/afficheAgents?faces-redirect=true";
	}
	public void AffecterTacheAAgent(Tache tache, int idAgent) {
		agentService.AffecterTacheAAgent(tache, idAgent);
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNumTel() {
		return numTel;
	}
	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}
	public int getAgentIdToBeUpdate() {
		return agentIdToBeUpdate;
	}
	public void setAgentIdToBeUpdate(int agentIdToBeUpdate) {
		this.agentIdToBeUpdate = agentIdToBeUpdate;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	public List<Agent> getAgents(){
		agents = agentService.findallAgents();
		return agents;
	}
	

	public List<Agent> getAgentsByStatus(){
		agentStatus = agentService.findAgentsByStatus();
		return agentStatus;
	}
	public int getIdMission() {
		return idMission;
	}
	public void setIdMission(int idMission) {
		this.idMission = idMission;
	}
	
	public String affecterTacheAagent(Agent agent) {
		this.setAgentIdToBeUpdate(agent.getId());
		this.setNom(agent.getNom());
		return "/Agent/AffecterTacheAagent?faces-redirect=true";
		//tacheService.AffecterRessourceATache(idTache, idRessource);
	}
	public String affectation() {
		Tache t = tacheService.findTacheById(idtache);
		agentService.AffecterTacheAAgent(t, agentIdToBeUpdate);	
		return "/Agent/afficheAgents?faces-redirect=true";
	}
	
	public String affecterPosAagent(Agent agent) {
		this.setAgentIdToBeUpdate(agent.getId());
		this.setNom(agent.getNom());
		return "/Agent/AffecterPosAagent?faces-redirect=true";
		//tacheService.AffecterRessourceATache(idTache, idRessource);
	}
	public String affectation1() {
		//Tache t = tacheService.findTacheById(idtache);
		PointOfProspection pos = posService.findPointOfProspectionById(idpos);
		agentService.AffecterTacheAAgent(pos, agentIdToBeUpdate);	
		return "/Agent/afficheAgents?faces-redirect=true";
	}
	
	public String affecterPosalesAagent(Agent agent) {
		this.setAgentIdToBeUpdate(agent.getId());
		this.setNom(agent.getNom());
		return "/Agent/AffecterPosalesAagent?faces-redirect=true";
		//tacheService.AffecterRessourceATache(idTache, idRessource);
	}
	public String affectation2() {
		//Tache t = tacheService.findTacheById(idtache);
		//PointOfProspection pos = posService.findPointOfProspectionById(idpos);
		PointOfSale posale = posaleService.findPointOfSaleById(idposale);
		agentService.AffecterTacheAAgent(posale, agentIdToBeUpdate);	
		return "/Agent/afficheAgents?faces-redirect=true";
	}


}
