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

import com.uesc.lif.i2ot.model.Location;
import com.uesc.lif.i2ot.model.Manufacturer;
import com.uesc.lif.i2ot.model.Rfid;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RfidBean implements Serializable {

	private Rfid rfid;
	private List<Rfid> rfidList;
	private List<Manufacturer> manufacturers;
	private List<Location> locationList;

	public Rfid getRfid() {
		return rfid;
	}

	public void setRfid(Rfid rfid) {
		this.rfid = rfid;
	}

	public List<Rfid> getRfidList() {
		return rfidList;
	}

	public void setRfidList(List<Rfid> rfidList) {
		this.rfidList = rfidList;
	}

	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public String FlameRfidRegistration() {
		return "RfidRegistration";
	}

	public void novo() {
		rfid = new Rfid();

		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			// Updating the location list
			String json = target.path("location").request().get(String.class);
			Gson gson = new Gson();
			Location[] LocationVector = gson.fromJson(json, Location[].class);
			locationList = Arrays.asList(LocationVector);

			// Updating the manufacturer list
			json = target.path("manufacturer").request().get(String.class);
			Manufacturer[] ManufacturerVector = gson.fromJson(json, Manufacturer[].class);
			manufacturers = Arrays.asList(ManufacturerVector);
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarLocaisErro"));
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			// Creating a client to connect to the service
			Client client = ClientBuilder.newClient();
			// Defining the target link
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			Gson gson = new Gson();
			// Transforming the rfid object in json
			String json = gson.toJson(rfid);

			// Se o id do rfid for null, é para salvar, senão, é pra editar
			if (rfid.getId() != null) {
				// Sending a request to the server
				target.path("rfid").request().put(Entity.json(json));
			} else {
				// Sending a request to the server
				target.path("rfid").request().post(Entity.json(json));
			}

			// Atualizando a variável rfid
			novo();

			// Atualizando a lista de rfid
			// O retorno do listar do restful é um json contendo todos os rfid cadastrados
			json = target.path("rfid").request().get(String.class);

			// Para transformar o json em uma lista, primeiro temos que transformar em vetor
			Rfid[] rfidVector = gson.fromJson(json, Rfid[].class);

			// Agora sim transformamos para uma lista
			rfidList = Arrays.asList(rfidVector);

			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("salvoSucesso"));
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("salvoErro"));
			e.printStackTrace();
		}
	}

	public void delete(ActionEvent event) {
		try {
			// Obtendo o rfid selecionado
			rfid = (Rfid) event.getComponent().getAttributes().get("rfidSelected");
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/rfid");
			// Mandando uma requisição ao servidor com o código do rfid selecionado
			target.path("{id}").resolveTemplate("id", rfid.getId()).request().delete();

			// Atualizando a lista de rfid
			Gson gson = new Gson();
			String json = target.request().get(String.class);
			Rfid[] rfidVector = gson.fromJson(json, Rfid[].class);
			rfidList = Arrays.asList(rfidVector);

			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("deleteRfidSucesso"));
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("deleteRfidErro"));
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void list() {
		try {
			Gson gson = new Gson();
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			String json = target.path("rfid").request().get(String.class);
			Rfid[] rfidVector = gson.fromJson(json, Rfid[].class);
			rfidList = Arrays.asList(rfidVector);

		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarRfidErro"));
			e.printStackTrace();
		}
	}

	public void update(ActionEvent event) {
		rfid = (Rfid) event.getComponent().getAttributes().get("rfidSelected");
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			
			// Updating the location list
			String json = target.path("location").request().get(String.class);
			Gson gson = new Gson();
			Location[] locationVector = gson.fromJson(json, Location[].class);
			locationList = Arrays.asList(locationVector);

			// Updating the manufacturer list
			json = target.path("manufacturer").request().get(String.class);
			Manufacturer[] ManufacturerVector = gson.fromJson(json, Manufacturer[].class);
			manufacturers = Arrays.asList(ManufacturerVector);
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarErro"));
			e.printStackTrace();
		}
	}
}