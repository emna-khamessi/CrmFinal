package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Avis database table.
 * 
 */
@Entity
@Table(name="Avis")
@NamedQuery(name="Avi.findAll", query="SELECT a FROM Avi a")
public class Avi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="ClientId")
	private String clientId;

	@Column(name="Comm")
	private String comm;

	@Column(name="Date")
	private String date;

	@Column(name="Reponse")
	private String reponse;

	@Column(name="Score")
	private int score;

	public Avi(String clientId, String comm, String date, String reponse, int score) {
		super();
		this.clientId = clientId;
		this.comm = comm;
		this.date = date;
		this.reponse = reponse;
		this.score = score;
	}

	public Avi() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReponse() {
		return this.reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}