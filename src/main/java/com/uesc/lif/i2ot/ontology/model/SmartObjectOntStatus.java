package com.uesc.lif.i2ot.ontology.model;

public class SmartObjectOntStatus {
	private boolean allowedPlace;
	private boolean allowedPerson;
	private boolean allowedMaterial;
	private boolean isMoving;
	
	public boolean isAllowedPlace() {
		return allowedPlace;
	}
	public void setAllowedPlace(boolean allowedPlace) {
		this.allowedPlace = allowedPlace;
	}
	public boolean isAllowedPerson() {
		return allowedPerson;
	}
	public void setAllowedPerson(boolean allowedPerson) {
		this.allowedPerson = allowedPerson;
	}
	public boolean isAllowedMaterial() {
		return allowedMaterial;
	}
	public void setAllowedMaterial(boolean allowedMaterial) {
		this.allowedMaterial = allowedMaterial;
	}
	public boolean isMoving() {
		return isMoving;
	}
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
}
