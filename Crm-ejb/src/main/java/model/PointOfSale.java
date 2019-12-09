package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PointOfSales database table.
 * 
 */
@Entity
@Table(name="PointOfSales")
@NamedQuery(name="PointOfSale.findAll", query="SELECT p FROM PointOfSale p")
public class PointOfSale implements Serializable {
	public PointOfSale(int id) {
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

	private int phone;

	@Column(name="Type")
	private String type;

	//bi-directional many-to-one association to Agent
	@OneToMany(mappedBy="pointOfSale")
	private List<Agent> agents;

	public PointOfSale() {
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

	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
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
		agent.setPointOfSale(this);

		return agent;
	}

	public Agent removeAgent(Agent agent) {
		getAgents().remove(agent);
		agent.setPointOfSale(null);

		return agent;
	}

	public PointOfSale(int id, String adress, String nom, int phone, String type) {
		super();
		this.id = id;
		this.adress = adress;
		this.nom = nom;
		this.phone = phone;
		this.type = type;
	}
	

}