package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <b>Class Name:</b> City<br>
 * <br>
 * 
 * <b>Last Modification:</b> 16/04/2018<br><br>
 * 
 * <b>Description: </b> This class represents a city model. It
 * extends the {@link Generic} class, inheriting the 'id' attribute. <br><br> 
 * 
 * <b>Attributes: </b><br>
 * 
 * <b>- name: </b>The city name.<br><br>
 * 
 * <b>- state: </b>The state where the city is located. Foreign key for {@link State} model.</b><br>
 *
 * @author Levy
 *
 */

@SuppressWarnings("serial")
@Entity
public class City extends Generic implements Serializable{
	@Column(nullable=false, length=50)
	private String name;

	@ManyToOne
	@JoinColumn(nullable=false)
	private State state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	
}
