package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "notifiable_entity")
public class NotifiableEntity implements Serializable {

private static final long serialVersionUID= 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="notifiable_entity_ID")
	private int id;
	
	@Column(name="identified")
	private String identified;
	
	@Column(name="clas")
	private String clas;
	
	@OneToOne(fetch= FetchType.EAGER)
	private Agent agent;

	public NotifiableEntity(String identified, Agent agent) {
		super();
		this.identified = identified;
		this.agent = agent;
	}

	public NotifiableEntity() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public String getIdentified() {
		return identified;
	}

	public String getClas() {
		return clas;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdentified(String identified) {
		this.identified = identified;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
}
