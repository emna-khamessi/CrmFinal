package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "notification")
public class Notification implements Serializable {

private static final long serialVersionUID= 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="Notification_ID")
	private int id;
	

	@Column(name="date_notif")
	private String dateNotif;
	
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="message")
	private String message;
	
	@Column(name="link")
	private String link;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public String getLink() {
		return link;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Notification() {
		super();
	}

	public Notification(int id) {
		super();
		this.id = id;
	}

	public String getDateNotif() {
		return dateNotif;
	}

	public void setDateNotif(String dateNotif) {
		this.dateNotif = dateNotif;
	}

	public Notification(int id, String dateNotif, String subject, String message) {
		super();
		this.id = id;
		this.dateNotif = dateNotif;
		this.subject = subject;
		this.message = message;
	}
}
