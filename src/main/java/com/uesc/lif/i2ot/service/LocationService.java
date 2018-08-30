package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;
import com.uesc.lif.i2ot.dao.LocationDAO;
import com.uesc.lif.i2ot.model.Location;

@Path("location")
public class LocationService {
	// Link: //http://localhost:8080/I2oT/rest/location
	@GET
	public String list() {
		LocationDAO locationDAO = new LocationDAO();
		List<Location> location = locationDAO.list();
		Gson gson = new Gson();

		String gsonlocation = gson.toJson(location);

		return gsonlocation;
	}

	// Link: //http://localhost:8080/I2oT/rest/location/[code]
	@GET
	@Path("{id}")
	public String search(@PathParam("id") Long code) {
		LocationDAO locationDAO = new LocationDAO();
		Location location = locationDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(location);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/location
	@POST
	public String save(String json) {
		LocationDAO locationDAO = new LocationDAO();
		Gson gson = new Gson();
		Location location = gson.fromJson(json, Location.class);
		locationDAO.save(location);

		json = gson.toJson(location);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/location
	@PUT
	public String edit(String json) {
		LocationDAO locationDAO = new LocationDAO();
		Gson gson = new Gson();
		Location location = gson.fromJson(json, Location.class);
		locationDAO.update(location);
		json = gson.toJson(location);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/location/[code]
	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Long code) {
		LocationDAO locationDAO = new LocationDAO();
		Location location = locationDAO.search(code);
		location.setDeleted('1');
		locationDAO.update(location);
		Gson gson = new Gson();
		String json = gson.toJson(location);

		return json;
	}
}
