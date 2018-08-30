package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * <b>Class Name:</b> Consignor<br>
 * <br>
 * 
 * <b>Last Modification:</b> 16/04/2018<br><br>
 * 
 * <b>Description: </b>This class represents a consignor model. 
 * It extends the {@link Person} class getting the 'name' and
 * 'address' attributes, and using the joined inheritance
 * type, the consignor's 'id' can be the same of person's 'id'.<br><br>
 *
 * <b>Attributes: </b><br><br>
 * 
 * <b>- CNPJ:</b> The consignor's CNPJ<br>
 *
 * @author Levy
 *
 */

@SuppressWarnings("serial")
@Entity
@PrimaryKeyJoinColumn(name="id")
public class Consignor extends Person implements Serializable{
	
	//private static final long serialVersionUID = 1L;
	@Column(nullable=false, unique=true, length=23)
	private String cnpj;

	public String getCNPJ() {
		return cnpj;
	}

	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
	}	
}
