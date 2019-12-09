package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "notifiableEntiy_notification")
public class NotificationEntity_Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Notification_NotifiableEntity_ID")
	private int id;
	

	@Column(name="seen")
	private int seen;
	
	@ManyToOne
	@JoinColumn(name = "NOIFIABLE_ENTITY_ID",referencedColumnName = "notifiable_entity_ID")
	private NotifiableEntity notifiable_entity;
	
	@ManyToOne
	@JoinColumn(name = "NOTIFICATION_ID",referencedColumnName = "Notification_ID")
	private Notification notification;

	public NotificationEntity_Notification(int id) {
		super();
		this.id = id;
	}

	public NotificationEntity_Notification() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public int getSeen() {
		return seen;
	}

	public NotifiableEntity getNotifiable_entity() {
		return notifiable_entity;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}

	public void setNotifiable_entity(NotifiableEntity notifiable_entity) {
		this.notifiable_entity = notifiable_entity;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public NotificationEntity_Notification(NotifiableEntity notifiable_entity, Notification notification) {
		super();
		this.notifiable_entity = notifiable_entity;
		this.notification = notification;
	}
}
