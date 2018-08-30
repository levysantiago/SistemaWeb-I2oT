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

import com.uesc.lif.i2ot.model.Antennas;
import com.uesc.lif.i2ot.model.Location;
import com.uesc.lif.i2ot.model.Rfid;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class AntennasBean implements Serializable{
	private Antennas antennas;
	private List<Antennas> antennasList;
	private List<Location> locationList;
	private List<Rfid> rfidList;

	public Antennas getAntennas() {
		return antennas;
	}

	public void setAntennas(Antennas antennas) {
		this.antennas = antennas;
	}

	public List<Antennas> getAntennasList() {
		return antennasList;
	}
	
	public void setAntennasList(List<Antennas> antennasList) {
		this.antennasList = antennasList;
	}
	
	public List<Location> getLocationList() {
		return locationList;
	}
	
	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public String FlameAntennaRegistry() {
		return "AntennaRegistry";
	}
	
	public List<Rfid> getRfidList() {
		return rfidList;
	}
	
	public void setRfidList(List<Rfid> rfidList) {
		this.rfidList = rfidList;
	}

	//Instancia uma nova antenna e atualiza a lista de location e rfid
	public void novo() {
		antennas = new Antennas();
		
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			
			//Updating the location list
			String json = target.path("location").request().get(String.class);
			Gson gson = new Gson();
			Location[] locationVector = gson.fromJson(json, Location[].class);
			locationList = Arrays.asList(locationVector);
			
			//Updating the rfid list
			json = target.path("rfid").request().get(String.class);
			Rfid[] rfidVector = gson.fromJson(json, Rfid[].class);
			rfidList = Arrays.asList(rfidVector);
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarLocaisRfidErro"));
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			//Creating a client to connect to the service
			Client client = ClientBuilder.newClient();
			//Defining the target link
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/antennas");
			Gson gson = new Gson();
			//Transforming the antennas object in json
			String json = gson.toJson(antennas);
			
			//Se o id da antenna for null, é para salvar, senão, é pra editar
			if(antennas.getId() != null) {
				//Sending a request to the server
				target.request().put(Entity.json(json));
			}else {
				//Sending a request to the server
				target.request().post(Entity.json(json));
			}
			
			//Atualizando a variável antennas
			novo();
			
			//Atualizando a lista de antenas
			//O retorno do listar do restful é um json contendo todas as antennas cadastradas
			json = target.request().get(String.class);
			
			//Para transformar o json em uma lista, primeiro temos que transformar em vetor
			Antennas[] antennasVector = gson.fromJson(json, Antennas[].class);
			
			//Agora sim transformamos para uma lista
			antennasList = Arrays.asList(antennasVector);
			
			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("salvoSucesso"));
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("salvoErro"));
			e.printStackTrace();
		}
	}
	
	public void delete(ActionEvent event) {
		try {
			//Obtendo a antenna selecionada
			antennas = (Antennas) event.getComponent().getAttributes().get("antennaSelected");
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/antennas");
			//Mandando uma requisição ao servidor com o código da antenna selecionada
			target.path("{id_antennas}").resolveTemplate("id_antennas", antennas.getId()).request().delete();
			
			//Atualizando a lista de antenas
			Gson gson = new Gson();
			String json = target.request().get(String.class);
			Antennas[] antennasVector = gson.fromJson(json, Antennas[].class);
			antennasList = Arrays.asList(antennasVector);
			
			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("deleteAntenaSucesso"));
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("deleteAntenaErro"));
			e.printStackTrace();
		}
	}
	
	//Anotação para que esse método seja executado quando a página abrir
	@PostConstruct
	public void list() {
		try {
			Gson gson = new Gson();
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/antennas");
			//O retorno do listar do restful é um json contendo todas as antennas cadastradas
			String json = target.request().get(String.class);
			
			//Para transformar o json em uma lista, primeiro temos que transformar em vetor
			Antennas[] antennasVector = gson.fromJson(json, Antennas[].class);
			
			//Agora sim transformamos para uma lista
			antennasList = Arrays.asList(antennasVector);
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarAntenasErro"));
			e.printStackTrace();
		}
	}
	
	//Vamos usar o próprio salvar para editar um campo, então no editar só tem isso mesmo
	public void update(ActionEvent event) {
		antennas = (Antennas) event.getComponent().getAttributes().get("antennaSelected");
	}
}
