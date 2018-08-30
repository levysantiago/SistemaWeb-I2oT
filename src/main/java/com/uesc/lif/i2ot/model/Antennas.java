package com.uesc.lif.i2ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * <b>Class Name:</b> Antennas<br>
 * <br>
 * 
 * <b>Last Modification:</b> 16/04/2018<br><br>
 * 
 * <b>Description: </b> This class represents an antenna model. It
 * extends the {@link Generic} class, inheriting the 'id' attribute. <br><br> 
 * 
 * <b>Attributes: </b><br>
 * 
 * <b>- number: </b> The antenna number. <br><br>
 * 
 * <b>- name: </b>The antenna name.<br><br>
 * 
 * <b>- rfid: </b>The rfid where the antenna is from. Foreign key for {@link Rfid} model.</b><br>
 * 
 * <b>- level_1: </b> The level 1 location of antenna. Foreign key for {@link Location} model.</b><br>
 *
 * @author Levy
 *
 */

/*@NamedQueries({
    @NamedQuery(name = "Antennas.findByNumber", query = "SELECT u FROM Antennas u WHERE u.number = :number"),
    @NamedQuery(name = "Antennas.findAll", query = "select u from Antennas u")
})*/

@SuppressWarnings("serial")
@Entity
public class Antennas extends Generic implements Serializable{
	
	/*public static final String findByNumber = "Antennas.findByNumber";
	public static final String findAll = "Antennas.findAll";*/
	
	@Column(nullable=false, unique=true)
	private Short number;
	
	@Column(nullable=false)
	private String name;
	
	@OneToOne
	@JoinColumn(name="id_rfid")
	private Rfid rfid;
	
	@OneToOne
	@JoinColumn(nullable=false, name="id_location")
	private Location location;

	public Short getNumber() {
		return number;
	}

	public void setNumber(Short number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rfid getRfid() {
		return rfid;
	}

	public void setRfid(Rfid rfid) {
		this.rfid = rfid;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}