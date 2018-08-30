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
import javax.ws.rs.core.Response;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.ManufacturerDAO;
import com.uesc.lif.i2ot.model.Address;
import com.uesc.lif.i2ot.model.City;
import com.uesc.lif.i2ot.model.Manufacturer;
import com.uesc.lif.i2ot.model.State;
import com.uesc.lif.i2ot.service.ManufacturerService;

/**
 * <b>Class Name:</b> ManufacturerBean <br>
 * <br>
 * 
 * <b>Last Modification:</b> 19/03/2018<br><br>
 * 
 * <b>Description: </b>This class is the manufacturer controller (ManagedBean).
 * The methods like save, list, update, and others send requests to the Service
 * class ({@link ManufacturerService}), which will communicate the DAO class
 * ({@link ManufacturerDAO}) to make a get, post, put or delete requests to
 * database. The ViewScoped annotation define that the bean is kept until the
 * application navigates to another page.<br><br>
 * 
 * <b>Attributes: </b><br>
 * 
 * <b>- manufacturer: </b> The attribute to access all the manufacturer attributes
 * to lately be registered in database. It's model is {@link Manufacturer}<br><br>
 * 
 * <b>- manufacturers: </b> The manufacturers list that is got by sending a
 * service request.<br><br>
 * 
 * <b>- state: </b> It's used to save the selected state (selectOneMenu
 * component). It's model is {@link State}<br><br>
 * 
 * <b>- states: </b>The states list that is got by sending a service request.<br><br>
 * 
 * <b>- address: </b>The address wrote by the user. It's lately inserted into
 * manufacturer address attribute.<br><br>
 * 
 * <b>- cities: </b>The cities list that is got by sending a service request.<br>
 * 
 * @author Levy
 *
 */

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ManufacturerBean implements Serializable {
	private Manufacturer manufacturer;
	private State state;
	private Address address;
	private List<Manufacturer> manufacturers;
	private List<State> states;
	private List<City> cities;

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
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
	
	public String FlameManufacturerRegistration() {
		return "ManufacturerRegistration";
	}

	/**
	 * <b>Method Name: </b> list <br>
	 * <br>
	 * 
	 * <b>Last Modification:</b> 19/03/2018<br>
	 * <br>
	 * 
	 * <b>Description: </b>This method will send a GET request to database to get
	 * all the registred manufacturers. The PostConstruct annotation define that
	 * when the view page is rendered, this method is called.<br>
	 * <br>
	 * 
	 * @return void
	 * 
	 */
	@PostConstruct
	public void list() {
		try {
			// Configuring the client and target
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/manufacturer");
			Gson gson = new Gson();

			// Updating the manufacturer list
			String json = target.request().get(String.class);
			Manufacturer[] manufacturerVector = gson.fromJson(json, Manufacturer[].class);
			manufacturers = Arrays.asList(manufacturerVector);
		} catch (RuntimeException e) {
			// Using the dictionary to get the message (internationalization)
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarFabricanteErro"));
			e.printStackTrace();
		}
	}

	/**
	 * <b>Method Name: </b> newObj <br>
	 * <br>
	 * 
	 * <b>Last Modification:</b> 19/03/2018<br>
	 * <br>
	 * 
	 * <b>Description: </b>This method instantiate a new manufacturer object to a
	 * new registry. The state object is to save the selected state, and from this
	 * state a city list can be exhibid. The address and cities are updated too,
	 * case a state has been registered or updated. The cities is iniciated with an
	 * empty ArrayList, because it'll be listed by a specific state.<br>
	 * <br>
	 * 
	 * @return void
	 */
	public void newObj() {
		manufacturer = new Manufacturer();
		state = new State();
		address = new Address();
		cities = new ArrayList<City>();
		try {
			// Configuring the client and target
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			Gson gson = new Gson();

			// Getting states
			String json = target.path("state").request().get(String.class);
			State[] stateVector = gson.fromJson(json, State[].class);
			states = Arrays.asList(stateVector);
		} catch (RuntimeException e) {
			// Using the dictionary to get the message (internationalization)
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarErro"));
			e.printStackTrace();
		}
	}

	/**
	 * <b>Method Name: </b> save <br>
	 * <br>
	 * 
	 * <b>Last Modification:</b> 19/03/2018<br>
	 * <br>
	 * 
	 * <b>Description: </b>This method set a new insert (if the id == null) or an
	 * update (if the id != null). If id == null, before making the insert, the
	 * address object is inserted in address table (on database). It's post method
	 * returns the address as a json, but now, with it's id, then this address is
	 * setted in manufacturer. Then the newObj method is called to allow a new
	 * registry, and the manufacturer list is updated to show the user an updated
	 * information.
	 * 
	 * @return void
	 */
	public void save() {
		try {
			// Configuring the client and target
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			Gson gson = new Gson();
			String json;

			// If id != null update, else post a new registry
			if (manufacturer.getId() != null) {
				// Converting obj to json
				json = gson.toJson(manufacturer);

				// Updating a manufacturer
				target.path("manufacturer").request().put(Entity.json(json));
			} else {
				// Saving an address to set it on manufacturer
				json = gson.toJson(address);
				Response resp = target.path("address").request().post(Entity.json(json));
				json = resp.readEntity(String.class);
				address = gson.fromJson(json, Address.class);
				manufacturer.setAddress(address);

				// Converting obj to json
				json = gson.toJson(manufacturer);

				// Saving a new manufacturer
				target.path("manufacturer").request().post(Entity.json(json));
			}

			// Updating the objects
			newObj();

			// Updating manufacturer list
			json = target.path("manufacturer").request().get(String.class);
			Manufacturer[] manufacturerVector = gson.fromJson(json, Manufacturer[].class);
			manufacturers = Arrays.asList(manufacturerVector);

			// Using the dictionary to get the message (internationalization)
			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("salvoSucesso"));
		} catch (RuntimeException e) {
			// Using the dictionary to get the message (internationalization)
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("salvoErro"));
			e.printStackTrace();
		}
	}

	/**
	 * <b>Method Name: </b> delete <br>
	 * <br>
	 * 
	 * <b>Last Modification:</b> 19/03/2018<br>
	 * <br>
	 * 
	 * <b>Description: </b>This method delete a selected registry. Then updates the
	 * manufacturer list.<br>
	 * <br>
	 * 
	 * @return void
	 * 
	 * @param event
	 *            The PrimeFaces event to get the selected attribute from a
	 *            component.
	 */
	public void delete(ActionEvent event) {
		try {
			// Getting the selected registry
			manufacturer = (Manufacturer) event.getComponent().getAttributes().get("manufacturerSelected");

			// Configuring the client and target
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/manufacturer");
			// Sending a delete request
			target.path("{id}").resolveTemplate("id", manufacturer.getId()).request().delete();

			// Updating manufacturer list
			Gson gson = new Gson();
			String json = target.request().get(String.class);
			Manufacturer[] manufacturerVector = gson.fromJson(json, Manufacturer[].class);
			manufacturers = Arrays.asList(manufacturerVector);

			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("deleteFabricanteSucesso"));
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("deleteFabricanteErro"));
			e.printStackTrace();
		}
	}

	/**
	 * <b>Method Name: </b> update <br>
	 * <br>
	 * 
	 * <b>Last Modification:</b> 19/03/2018<br>
	 * <br>
	 * 
	 * <b>Description: </b>This method updates the manufacturer object with a
	 * selected registry, updates the state list, set the state object (the selected
	 * state in the view table) and calls the updateCities method, to update the
	 * city list by a specific state. <br>
	 * <br>
	 * 
	 * @return void
	 * 
	 * @param event
	 *            The PrimeFaces event to get the selected attribute from a
	 *            component.
	 */
	public void update(ActionEvent event) {
		manufacturer = (Manufacturer) event.getComponent().getAttributes().get("manufacturerSelected");
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
			state = manufacturer.getAddress().getCity().getState();

			// Updating city list by states
			updateCities();

			// Setting the address informations
			address = manufacturer.getAddress();
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarEstadoErro"));
			e.printStackTrace();
		}
	}

	/**
	 * <b>Method Name: </b> updateCities <br>
	 * <br>
	 * 
	 * <b>Last Modification:</b> 19/03/2018<br>
	 * <br>
	 * 
	 * <b>Description: </b>This method updates the city list by a specific state
	 * using the state id. <br>
	 * <br>
	 * 
	 * @return void
	 */
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
