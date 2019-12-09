package service;

import java.util.List;

import javax.ejb.Remote;

import model.Agent;
import model.Tache;

@Remote
public interface AgentServiceRemote {
	public int addAgent(Agent agent);
	public void removeAgent(int id);
	public void updateAgent(Agent agent);
	public Agent findAgentById(int id);
	public List<Agent> findallAgents();
	public void AffecterTacheAAgent(Object object, int idAgent);
	public List<Agent> findAgentsByStatus();
	
}
