package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Rfid extends Generic implements Serializable{
	
	@Column(nullable=false)
	private String name;
	
	@OneToOne
	@JoinColumn(name="id_manufacturer")
	private Manufacturer manufacturer;
	
	@Column(nullable=false, unique=true, length=20)
	private String model;
	
	@OneToOne
	@JoinColumn(nullable=false, name="id_location")
	private Location location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
