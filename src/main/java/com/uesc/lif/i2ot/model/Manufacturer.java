package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * <b>Class Name:</b> Manufacturer<br>
 * <br>
 * 
 * <b>Last Modification:</b> 16/04/2018<br><br>
 * 
 * <b>Description: </b>This class represents a manufacturer model. 
 * It extends the {@link Person} class getting the 'name' and
 * 'address' attributes, and using the joined inheritance
 * type, the manufacturer's 'id' can be the same of person's 'id'.<br><br>
 *
 * <b>Attributes: </b><br><br>
 * 
 * <b>- CNPJ:</b> The manufacturer's CNPJ<br>
 *
 * @author Levy
 *
 */

@SuppressWarnings("serial")
@Entity
@PrimaryKeyJoinColumn(name="id")
public class Manufacturer extends Person implements Serializable{
	@Column(nullable=false, unique=true, length=20)
	private String cnpj;

	public String getCNPJ() {
		return cnpj;
	}

	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
	}
}