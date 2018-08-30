package com.uesc.lif.i2ot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Publications extends Generic implements Serializable{
	
	@Column(nullable=false)
	private Date timestamp;
	
	@OneToOne
	@JoinColumn(name="id_user")
	private User id_user;
	
	@OneToOne
	@JoinColumn(name="id_smartObject")
	private SmartObject id_smartObject;
	
	@OneToOne
	@JoinColumn(name="id_location")
	private Location location;
	
	@OneToOne
	@JoinColumn(name="id_statusSensor")
	private StatusSensor id_statusSensor;
	
	@OneToOne
	@JoinColumn(name="id_privacyLevel")
	private PrivacyLevel id_privacyLevel;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public User getId_user() {
		return id_user;
	}

	public void setId_user(User id_user) {
		this.id_user = id_user;
	}

	public SmartObject getId_smartObject() {
		return id_smartObject;
	}

	public void setId_smartObject(SmartObject id_smartObject) {
		this.id_smartObject = id_smartObject;
	}

	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	public StatusSensor getId_statusSensor() {
		return id_statusSensor;
	}

	public void setId_statusSensor(StatusSensor id_statusSensor) {
		this.id_statusSensor = id_statusSensor;
	}

	public PrivacyLevel getId_privacyLevel() {
		return id_privacyLevel;
	}

	public void setId_privacyLevel(PrivacyLevel id_privacyLevel) {
		this.id_privacyLevel = id_privacyLevel;
	}
	
}
