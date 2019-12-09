package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Ressource database table.
 * 
 */
@Entity
@NamedQuery(name="Ressource.findAll", query="SELECT r FROM Ressource r")
public class Ressource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	private int etat;

	private String nom;

	private double quantite;

	private String type;

	//bi-directional many-to-many association to Tache
	@ManyToMany(mappedBy="ressources")
	private List<Tache> taches;

	public Ressource() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getQuantite() {
		return this.quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Tache> getTaches() {
		return this.taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public Ressource(int id, int etat, String nom, String type) {
		super();
		this.id = id;
		this.etat = etat;
		this.nom = nom;
		this.type = type;
	}
	public Ressource(int id, String nom, String type) {
		super();
		this.id = id;
		this.nom = nom;
		this.type = type;
	}
	

}