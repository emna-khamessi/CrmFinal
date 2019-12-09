package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PointOfProspections database table.
 * 
 */
@Entity
@Table(name="PointOfProspections")
@NamedQuery(name="PointOfProspection.findAll", query="SELECT p FROM PointOfProspection p")
public class PointOfProspection implements Serializable {
	
	public PointOfProspection(int id, String adress, String nom, String type) {
		super();
		this.id = id;
		this.adress = adress;
		this.nom = nom;
		this.type = type;
	}

	public PointOfProspection(int id) {
		super();
		this.id = id;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Adress")
	private String adress;

	private String nom;

	@Column(name="Type")
	private String type;

	//bi-directional many-to-one association to Agent
	@OneToMany(mappedBy="pointOfProspection")
	private List<Agent> agents;

	public PointOfProspection() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Agent> getAgents() {
		return this.agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public Agent addAgent(Agent agent) {
		getAgents().add(agent);
		agent.setPointOfProspection(this);

		return agent;
	}

	public Agent removeAgent(Agent agent) {
		getAgents().remove(agent);
		agent.setPointOfProspection(null);

		return agent;
	}

}