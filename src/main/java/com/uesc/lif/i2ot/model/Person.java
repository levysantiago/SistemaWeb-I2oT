package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * <b>Class Name:</b> Person<br>
 * <br>
 * 
 * <b>Last Modification:</b> 16/04/2018<br><br>
 * 
 * <b>Description: </b>This is the mother class of {@link User},
 * {@link Manufacturer} and {@link Consignor}. Using the joined inheritance
 * type, the person's 'id' can be the same of your son's 'id'. Once that person has
 * 'name' and 'address', all her sons has the same attributes. This class also
 * extends the {@link Generic} class, inheriting the 'id' attribute.<br><br> 
 * 
 * <b>Attributes: </b><br>
 * 
 * <b>- name: </b> The person's name<br><br>
 * 
 * <b>- address: </b> The person's address. Foreign key for {@link Address} model.<br>
 *
 * @author Levy
 *
 */

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends Generic implements Serializable {
	@Column(nullable = false, length = 50)
	private String name;

	@JoinColumn(nullable = false)
	@OneToOne
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
