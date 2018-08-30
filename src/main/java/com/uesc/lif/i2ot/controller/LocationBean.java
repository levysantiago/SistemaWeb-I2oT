package com.uesc.lif.i2ot.controller;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.uesc.lif.i2ot.model.City;
import com.uesc.lif.i2ot.model.Location;
import com.uesc.lif.i2ot.model.State;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LocationBean implements Serializable{
	private Location location;
	private State state;
	private List<Location> locations;
	private List<State> states;
	private List<City> cities;
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	public String FlameLocationRegistry() {
		return "LocationRegistry";
	}
	
	public void novo() {
		location = new Location();
		state = new State();
		cities = new ArrayList<City>();
		
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			Gson gson = new Gson();
			
			// Getting states
			String json = target.path("state").request().get(String.class);
			State[] stateVector = gson.fromJson(json, State[].class);
			states = Arrays.asList(stateVector);
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
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/location");
			Gson gson = new Gson();
			//Transforming the location object in json
			String json = gson.toJson(location);
			
			if(location.getId() != null) {
				//Sending a request to the server
				target.request().put(Entity.json(json));
			}else {
				//Sending a request to the server
				target.request().post(Entity.json(json));
			}
			
			novo();
			
			json = target.request().get(String.class);
			Location[] locationVector = gson.fromJson(json, Location[].class);
			locations = Arrays.asList(locationVector);
			
			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("salvoSucesso"));
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("salvoErro"));
			e.printStackTrace();
		}
	}
	
	public void delete(ActionEvent event) {
		try {
			//Getting the selected location
			location = (Location) event.getComponent().getAttributes().get("locationSelected");
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/location");
			
			target.path("{id_location}").resolveTemplate("id_location", location.getId()).request().delete();
			
			//Updating locations
			Gson gson = new Gson();
			String json = target.request().get(String.class);
			Location[] locationVector = gson.fromJson(json, Location[].class);
			locations = Arrays.asList(locationVector);
			
			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("deleteLocalSucesso"));
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("deleteLocalErro"));
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void list() {
		try {
			Gson gson = new Gson();
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/location");
			String json = target.request().get(String.class);
			
			Location[] locationVector = gson.fromJson(json, Location[].class);
			locations = Arrays.asList(locationVector);
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarLocalErro"));
			e.printStackTrace();
		}
	}
	
	public void update(ActionEvent event) {
		location = (Location) event.getComponent().getAttributes().get("locationSelected");
		try {
			// Configuring the client and target
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/state");

			// Updating the state list
			String json = target.request().get(String.class);
			Gson gson = new Gson();
			State[] stateVector = gson.fromJson(json, State[].class);
			states = Arrays.asList(stateVector);

			// Setting the manufacturer state
			state = location.getCity().getState();

			// Updating city list by states
			updateCities();
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarEstadoErro"));
			e.printStackTrace();
		}
	}
	
	public void updateCities() {
		try {
			if (state != null) {
				// Configuring the client and target
				Client client = ClientBuilder.newClient();
				WebTarget target = client.target("http://localhost:8080/I2oT/rest/city/byState/");
				Gson gson = new Gson();

				// Using the state id to get it's list of cities
				String json = target.path("{id}").resolveTemplate("id", state.getId()).request().get(String.class);
				City[] cityVector = gson.fromJson(json, City[].class);
				cities = Arrays.asList(cityVector);
			} else {
				// If none state is selected, the city list is cleaned
				cities.clear();
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarCidadeErro"));
			e.printStackTrace();
		}
	}
	
	
}
