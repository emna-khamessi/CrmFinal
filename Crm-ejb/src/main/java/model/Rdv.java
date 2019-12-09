package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Rdv database table.
 * 
 */
@Entity
@NamedQuery(name="Rdv.findAll", query="SELECT r FROM Rdv r")
public class Rdv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp date;

	private String description;

	private int etat;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="usr")
	private User user;

	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="store")
	private Store storeBean;

	public Rdv() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Store getStoreBean() {
		return this.storeBean;
	}

	public void setStoreBean(Store storeBean) {
		this.storeBean = storeBean;
	}

}