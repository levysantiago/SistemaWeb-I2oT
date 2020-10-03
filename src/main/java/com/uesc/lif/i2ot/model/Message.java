package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * Descrição: Esta classe representa as mensagens que serão enviadas pelo middleware
 * */

@SuppressWarnings("serial")
@Entity
public class Message extends Generic implements Serializable{
	
	@Column(name="message", length=100)
	private String message;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private SmartObject smartObject;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SmartObject getSmartObject() {
		return smartObject;
	}

	public void setSmartObject(SmartObject smartObject) {
		this.smartObject = smartObject;
	}
	
}
