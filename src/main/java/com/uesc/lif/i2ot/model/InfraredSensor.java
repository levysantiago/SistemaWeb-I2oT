package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class InfraredSensor extends Generic implements Serializable{
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@Column(nullable=false)
	private int distance;
	
	@Column(nullable=false)
	private int reactionTime;
	
	@Column(nullable=false)
	private int readingTime;
	
	@OneToOne
	@JoinColumn(nullable=false, name="id_location")
	private Location location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getReactionTime() {
		return reactionTime;
	}

	public void setReactionTime(int reactionTime) {
		this.reactionTime = reactionTime;
	}

	public int getReadingTime() {
		return readingTime;
	}

	public void setReadingTime(int readingTime) {
		this.readingTime = readingTime;
	}

	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
