package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/*public enum DepartmentType{

    FINANCIAL("Financeiro"),
    JUDICIAL("Jurídico"),
    MARKETING("Marketing"),
    SHOPPING("Compra"),
    DISPOSAL("Venda"),
    ADMINISTRATIVE("Administrativo"),
    OPERATIONAL("Operacional"),
    HUMAN_RESOURCES("Recursos Humanos");

    DepartmentType(String department){
        this.department = department;
    }

    //Attributes
    private String department;


    //Properties
    public String getDepartment(){
       return department;
    }
}*/

/**
 * <b>Class Name:</b> Department<br>
 * <br>
 * 
 * <b>Last Modification:</b> 16/04/2018<br><br>
 * 
 * <b>Description: </b> This class represents a department model. It
 * extends the {@link Generic} class, inheriting the 'id' attribute. 
 * The department types are: <br><br> 
 * 
 * <b>Attributes: </b>
 * 
 * <b>- name: </b>The department name.<br><br>
 *
 * @author Levy
 *
 */

@SuppressWarnings("serial")
@Entity
public class Department extends Generic implements Serializable{
	@Column(nullable=false, unique=true, length=30)
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}