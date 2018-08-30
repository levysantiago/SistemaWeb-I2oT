package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Location extends Generic implements Serializable{
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String address;
	
	//FALTA ATUALIZAR NO BANCO
	/*@Column(nullable=false)
	private String neighborhood;
	
	@Column(nullable=false)
	private String postalCode;*/
	
	@ManyToOne
	@JoinColumn(name="id_city", nullable=false)
	private City city;
	
	@Column(nullable=false)
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/*public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}*/

	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}

	/*public String getFederativeUnit() {
		return federativeUnit;
	}

	public void setFederativeUnit(String federativeUnit) {
		this.federativeUnit = federativeUnit;
	}*/

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
