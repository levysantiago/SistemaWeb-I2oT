package com.uesc.lif.i2ot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.uesc.lif.i2ot.ontology.model.SmartObjectOntProperties;
import com.uesc.lif.i2ot.ontology.model.SmartObjectOntStatus;

@NamedQueries({ @NamedQuery(name = "SmartObject.findAll", query = "SELECT u FROM SmartObject u"),
		@NamedQuery(name = "SmartObject.findByTag", query = "SELECT u FROM SmartObject u WHERE EXISTS(SELECT u FROM SmartObject u WHERE u.tagRfid = :tagRfid) and u.tagRfid = :tagRfid order by u.description") })
@Entity
@SuppressWarnings("serial")
public class SmartObject extends Generic implements Serializable {
	public static final String findAll = "SmartObject.findAll";
	public static final String findByTag = "SmartObject.findByTag";
	
	//Defined only when the objects are read
	@Transient
	private boolean registered;

	@Transient
	private SmartObjectOntStatus status;
	
	@Transient
	private String movedBySome;
	
	@Transient
	private SmartObjectOntProperties properties;
	
	@Column(nullable = false, length = 30)
	private String tagRfid;

	@Column(nullable = false, length = 100)
	private String description;

	@Column(nullable = false, length = 50)
	private String category;

	@Column(name = "conditionXXX")
	private String condition;

	@Column(nullable = false)
	private Date purchaseDate;

	@Column
	private int purchasePrice;

	@Column
	private int currentValue;

	@OneToOne
	@JoinColumn(name = "id_manufacturer", nullable = true)
	private Manufacturer manufacturer;

	@Column(nullable = false, length = 30)
	private String model;

	@OneToOne
	@JoinColumn(name = "id_consignor", nullable = true)
	private Consignor consignor;

	@OneToOne
	@JoinColumn(name = "id_user", nullable = true)
	private User user;

	@OneToOne
	@JoinColumn(name = "id_location", nullable = false)
	private Location location;
	
	@OneToOne
	@JoinColumn(name = "id_actualLocation", nullable = false)
	private Location actualLocation;
	
	public SmartObjectOntStatus getStatus() {
		return status;
	}
	
	public void setStatus(SmartObjectOntStatus status) {
		this.status = status;
	}

	public String getMovedBySome() {
		return movedBySome;
	}

	public void setMovedBySome(String movedBySome) {
		this.movedBySome = movedBySome;
	}

	public String getTagRfid() {
		return tagRfid;
	}

	public void setTagRfid(String tagRfid) {
		this.tagRfid = tagRfid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Consignor getConsignor() {
		return consignor;
	}

	public void setConsignor(Consignor consignor) {
		this.consignor = consignor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getActualLocation() {
		return actualLocation;
	}
	
	public void setActualLocation(Location actualLocation) {
		this.actualLocation = actualLocation;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

}
