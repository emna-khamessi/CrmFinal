package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Agent database table.
 * 
 */
@Entity
@NamedQuery(name="Agent.findAll", query="SELECT a FROM Agent a")
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Email")
	private String email;

	private int heureTravail;

	private String nom;

	private int numTel;

	private String prenom;

	private int status;

	private String type;

	//bi-directional many-to-one association to PointOfProspection
	@ManyToOne
	@JoinColumn(name="PPFK")
	private PointOfProspection pointOfProspection;

	//bi-directional many-to-one association to PointOfSale
	@ManyToOne
	@JoinColumn(name="PSFK")
	private PointOfSale pointOfSale;

	//bi-directional many-to-one association to Tache
	@ManyToOne
	@JoinColumn(name="TacheFK")
	private Tache tache;

	public Agent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getHeureTravail() {
		return this.heureTravail;
	}

	public void setHeureTravail(int heureTravail) {
		this.heureTravail = heureTravail;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNumTel() {
		return this.numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PointOfProspection getPointOfProspection() {
		return this.pointOfProspection;
	}

	public void setPointOfProspection(PointOfProspection pointOfProspection) {
		this.pointOfProspection = pointOfProspection;
	}

	public PointOfSale getPointOfSale() {
		return this.pointOfSale;
	}

	public void setPointOfSale(PointOfSale pointOfSale) {
		this.pointOfSale = pointOfSale;
	}

	public Tache getTache() {
		return this.tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public Agent(int id, String email, String nom, int numTel, String prenom, int status) {
		super();
		this.id = id;
		this.email = email;
		this.nom = nom;
		this.numTel = numTel;
		this.prenom = prenom;
		this.status = status;
	}
	public Agent(int id, String email, String nom, int numTel, String prenom) {
		super();
		this.id = id;
		this.email = email;
		this.nom = nom;
		this.numTel = numTel;
		this.prenom = prenom;
	}
	public Agent(String email, String nom, int numTel, String prenom) {
		super();
		this.email = email;
		this.nom = nom;
		this.numTel = numTel;
		this.prenom = prenom;
	}
	

}