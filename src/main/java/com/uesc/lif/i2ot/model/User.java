package com.uesc.lif.i2ot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
	@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
	@NamedQuery(name = "User.findByDepartment", query = "SELECT u FROM User u WHERE u.department = :department"),
	@NamedQuery(name = "User.findByFunctionalEnrollment", query = "SELECT u FROM User u WHERE u.functionalEnrollment = :functionalEnrollment"),
	@NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password")
})
@Entity
@PrimaryKeyJoinColumn(name="id")
@SuppressWarnings("serial")
public class User extends Person implements Serializable{
	
	public static final String findAll = "User.findAll";
	public static final String findByUserName = "User.findByUserName";
	public static final String findByPassword = "User.findByPassword";
	public static final String findByName = "User.findByName";
	public static final String findByDepartment = "User.findByDepartment";
	public static final String findByFunctionalEnrollment = "User.findByFunctionalEnrollment";
	public static final String findByLogin = "User.findByLogin";
	
	@Column(nullable=false, unique=true, length=10)
	private String userName;
	
	@Column(nullable=false, length=8)
	private String password;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(nullable=false, unique=true, length=15)
	private Long functionalEnrollment;
	
	@JoinColumn(nullable=false)
	@ManyToOne
	private Department department;
	
	/*@OneToOne
	@JoinColumn(name="id_level_2")
	private Level_2 id_level_2;*/
	
	@Column(nullable=true, unique=false, length=15)
	private String RG;
	
	@Column(unique=true, length=15)
	private String CPF;
	
	@Column(nullable=false)
	private Boolean active;
	
	//'A' ou 'U'
	@Column(nullable=false, unique=true, length=1)
	private Character type;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Long getFunctionalEnrollment() {
		return functionalEnrollment;
	}

	public void setFunctionalEnrollment(Long functionalEnrollment) {
		this.functionalEnrollment = functionalEnrollment;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
	}

	
	
}


