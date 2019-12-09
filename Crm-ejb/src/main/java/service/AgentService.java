package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Agent;
import model.NotifiableEntity;
import model.Notification;
import model.NotificationEntity_Notification;
import model.PointOfProspection;
import model.PointOfSale;
import model.Tache;
@Stateless
@LocalBean
public class AgentService implements AgentServiceRemote {

	@PersistenceContext
	EntityManager em;
	@Override
	public int addAgent(Agent agent) {
		// TODO Auto-generated method stub
		em.persist(agent);
		return agent.getId();
	}

	@Override
	public void removeAgent(int id) {
		em.remove(em.find(Agent.class, id));
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAgent(Agent agent) {
		// TODO Auto-generated method stub
//		Agent agen = em.find(Agent.class, agent.getId());
//		agen.setNom(agent.getNom());
//		agen.setPrenom(agen.getPrenom());
//		agen.setEmail(agen.getEmail());
//		agen.setStatus(agen.getStatus());
//		agen.setNumTel(agen.getNumTel());
		em.merge(agent);
		
	}

	@Override
	public Agent findAgentById(int id) {
		// TODO Auto-generated method stub
		return em.find(Agent.class, id);
	}

	@Override
	public List<Agent> findallAgents() {
		// TODO Auto-generated method stub
		List<Agent> agents = em.createQuery("select a from Agent a", Agent.class).getResultList();
		return agents;
	}
	
	@Override
	public void AffecterTacheAAgent(Object object, int idAgent) {
		Agent agent = em.find(Agent.class, idAgent);
		
		if(object instanceof Tache) {
	
			agent.setStatus(1);
			agent.setTache((Tache)object);
		}	
		 else if(object instanceof PointOfProspection) {
			
			agent.setStatus(2);
			agent.setPointOfProspection((PointOfProspection)object);
			
		} else if (object instanceof PointOfSale){		
			
			agent.setStatus(3);
			agent.setPointOfSale((PointOfSale)object);
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Date date = new Date();
		//HeadOfDepartement pr = em.find(HeadOfDepartement.class, idCHef);
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		System.out.println("****** dtae :: "+ dateFormat.format(date));
		Notification notification = new Notification(0, dateFormat.format(date), " Affectation de mission ",
				"Nom:" + agent.getNom() + "Prenom:" +agent.getPrenom()+
						 "  a affacter une mission");
		em.persist(notification);
		NotifiableEntity notifiable_entity = new NotifiableEntity("Administateur ", agent);
		em.persist(notifiable_entity);
		NotificationEntity_Notification notifiable_entity_notification = new NotificationEntity_Notification(
				notifiable_entity, notification);
		em.persist(notifiable_entity_notification);
		
	}

	@Override
	public List<Agent> findAgentsByStatus() {
		// TODO Auto-generated method stub
		TypedQuery<Agent> query = em.createQuery("SELECT a from Agent a where a.status=1", Agent.class);
		List<Agent> results = query.getResultList();
		return results; 
	}
	
}
