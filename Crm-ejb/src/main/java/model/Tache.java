package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Tache database table.
 * 
 */
@Entity
@NamedQuery(name="Tache.findAll", query="SELECT t FROM Tache t")
public class Tache implements Serializable {
	public Tache(int id) {
		super();
		this.id = id;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	private Date dateDebut;

	private Date dateFin;

	private String nom;

	//bi-directional many-to-one association to Agent
	@OneToMany(mappedBy="tache")
	private List<Agent> agents;

	//bi-directional many-to-many association to Ressource
	@ManyToMany
	@JoinTable(
		name="RessourceTaches"
		, joinColumns={
			@JoinColumn(name="Tache_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Ressource_id")
			}
		)
	private List<Ressource> ressources;

	public Tache() {
	}

	public Tache(List<Agent> agents) {
		super();
		this.agents = agents;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Agent> getAgents() {
		return this.agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public Agent addAgent(Agent agent) {
		getAgents().add(agent);
		agent.setTache(this);

		return agent;
	}

	public Agent removeAgent(Agent agent) {
		getAgents().remove(agent);
		agent.setTache(null);

		return agent;
	}

	public List<Ressource> getRessources() {
		return this.ressources;
	}

	public void setRessources(List<Ressource> ressources) {
		this.ressources = ressources;
	}

	public Tache(int id, Date dateDebut, Date dateFin, String nom) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nom = nom;
	}
	public Tache(Date dateDebut, Date dateFin, String nom) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nom = nom;
	}


	@Override
	public String toString() {
		return "Tache [id=" + id +  "]";
	}
	

}