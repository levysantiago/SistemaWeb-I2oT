package com.uesc.lif.i2ot.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.model.Consignor;
import com.uesc.lif.i2ot.model.Location;
import com.uesc.lif.i2ot.model.Manufacturer;
import com.uesc.lif.i2ot.model.SmartObject;
import com.uesc.lif.i2ot.model.User;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SmartObjectBean implements Serializable {

	SmartObject smartObject;
	private List<SmartObject> smartObjects;
	private List<Manufacturer> manufacturers;
	private List<User> users;
	private List<Consignor> consignors;
	private List<Location> locationList;

	public SmartObject getSmartObject() {
		return smartObject;
	}

	public void setSmartObject(SmartObject smartObject) {
		this.smartObject = smartObject;
	}

	public List<SmartObject> getSmartObjects() {
		return smartObjects;
	}

	public void setSmartObjects(List<SmartObject> smartObjects) {
		this.smartObjects = smartObjects;
	}

	public String FlameSmartObjectRegister() {
		return "SmartObjectRegister";
	}

	public String FlameSmartObjectReport() {
		return "SmartObjectReport";
	}

	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Consignor> getConsignors() {
		return consignors;
	}

	public void setConsignors(List<Consignor> consignors) {
		this.consignors = consignors;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	// Instancia um novo OI e atualiza as listas
	public void novo() {
		smartObject = new SmartObject();

		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			// Updating the manufacturers list
			String json = target.path("manufacturer").request().get(String.class);
			Gson gson = new Gson();
			Manufacturer[] manufacturerVector = gson.fromJson(json, Manufacturer[].class);
			manufacturers = Arrays.asList(manufacturerVector);

			// Updating the users list
			json = target.path("user").request().get(String.class);
			User[] userVector = gson.fromJson(json, User[].class);
			users = Arrays.asList(userVector);

			// Updating the consignors list
			json = target.path("consignor").request().get(String.class);
			Consignor[] consignorVector = gson.fromJson(json, Consignor[].class);
			consignors = Arrays.asList(consignorVector);

			// Updating the location list
			json = target.path("location").request().get(String.class);
			Location[] locationVector = gson.fromJson(json, Location[].class);
			locationList = Arrays.asList(locationVector);
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarErro"));
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/smartObject");
			Gson gson = new Gson();
			
			String json = gson.toJson(smartObject);
			if (smartObject.getId() != null) {
				target.request().put(Entity.json(json));
			} else {
				target.request().post(Entity.json(json));
			}

			novo();

			json = target.request().get(String.class);
			SmartObject[] smartObjectVector = gson.fromJson(json, SmartObject[].class);
			smartObjects = Arrays.asList(smartObjectVector);

			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("salvoSucesso"));
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("salvoErro"));
			e.printStackTrace();
		}
	}

	public void delete(ActionEvent event) {
		try {
			// Obtendo a antenna selecionada
			smartObject = (SmartObject) event.getComponent().getAttributes().get("smartObjectSelected");
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/smartObject");
			// Mandando uma requisição ao servidor com o código do OI selecionado
			target.path("{id}").resolveTemplate("id", smartObject.getId()).request().delete();

			// Atualizando a lista de antenas
			Gson gson = new Gson();
			String json = target.request().get(String.class);
			SmartObject[] smartObjectVector = gson.fromJson(json, SmartObject[].class);
			smartObjects = Arrays.asList(smartObjectVector);

			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("deleteOISucesso"));
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("deleteOIErro"));
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void list() {
		try {
			Gson gson = new Gson();
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/smartObject");
			String json = target.request().get(String.class);
			SmartObject[] smartObjectVector = gson.fromJson(json, SmartObject[].class);
			smartObjects = Arrays.asList(smartObjectVector);
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarOIErro"));
			e.printStackTrace();
		}
	}

	public void update(ActionEvent event) {
		smartObject = (SmartObject) event.getComponent().getAttributes().get("smartObjectSelected");
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			// Updating the manufacturers list
			String json = target.path("manufacturer").request().get(String.class);
			Gson gson = new Gson();
			Manufacturer[] manufacturerVector = gson.fromJson(json, Manufacturer[].class);
			manufacturers = Arrays.asList(manufacturerVector);

			// Updating the users list
			json = target.path("user").request().get(String.class);
			User[] userVector = gson.fromJson(json, User[].class);
			users = Arrays.asList(userVector);

			// Updating the consignors list
			json = target.path("consignor").request().get(String.class);
			Consignor[] consignorVector = gson.fromJson(json, Consignor[].class);
			consignors = Arrays.asList(consignorVector);

			// Updating the location list
			json = target.path("location").request().get(String.class);
			Location[] locationVector = gson.fromJson(json, Location[].class);
			locationList = Arrays.asList(locationVector);
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarErro"));
			e.printStackTrace();
		}
	}
}