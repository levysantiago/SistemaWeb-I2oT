package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Phone extends Generic implements Serializable{
	@Column(nullable=false, unique=true, length=20)
	private String number;
	
	@JoinColumn(nullable=false)
	@ManyToOne
	private Person person;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
