package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <b>Class Name:</b> Address<br>
 * <br>
 * 
 * <b>Last Modification:</b> 16/04/2018<br><br>
 * 
 * <b>Description: </b> This class represents an address model. It
 * extends the {@link Generic} class, inheriting the 'id' attribute.
 * This class is extended by the model {@link Person}. <br><br> 
 * 
 * <b>Attributes: </b><br>
 * 
 * <b>- city: </b> The city where someone is located. Foreign key for {@link City} model.<br><br>
 * 
 * <b>- neighborhood: </b> A community or region within a city or municipality. <br><br>
 * 
 * <b>- publicPlace: </b> The space attached to a dwelling, used for the purpose 
 * of the house, or any common public space that can be enjoyed by the 
 * whole population.</b><br>
 * 
 * <b>- postalCode: </b> The postal code of the location.</b><br>
 * 
 * <b>- number: </b> The house's number.</b><br>
 *
 * @author Levy
 *
 */

@SuppressWarnings("serial")
@Entity
public class Address extends Generic implements Serializable {
	@JoinColumn(nullable=false)
	@ManyToOne
	private City city;
	
	@Column(nullable=false, length=100)
	private String neighborhood;
	
	@Column(nullable=false, length=100)
	private String publicPlace;
	
	@Column(nullable=false, length=11)
	private String postalCode;
	
	@Column(nullable=false, length=8)
	private String number;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
