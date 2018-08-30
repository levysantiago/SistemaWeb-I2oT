package com.uesc.lif.i2ot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class StatusSensor extends Generic implements Serializable{
	@OneToOne
	@JoinColumn(name="id_infraredSensor")
	private InfraredSensor id_infraredSensor;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date timestamp;
	
	@Column(nullable=false)
	private String result;
	
	public InfraredSensor getId_infraredSensor() {
		return id_infraredSensor;
	}

	public void setId_sensor(InfraredSensor id_infraredSensor) {
		this.id_infraredSensor = id_infraredSensor;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
