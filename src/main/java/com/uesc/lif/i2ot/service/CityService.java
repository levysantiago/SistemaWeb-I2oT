package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.CityDAO;
import com.uesc.lif.i2ot.model.City;

@Path("city")
public class CityService {
	// Link: //http://localhost:8080/I2oT/rest/city
	@GET
	public String list() {
		CityDAO cityDAO = new CityDAO();
		List<City> city = cityDAO.list();
		Gson gson = new Gson();

		String gsonCity = gson.toJson(city);

		return gsonCity;
	}

	// Link: //http://localhost:8080/I2oT/rest/city/byState/[id]
	@GET
	@Path("/byState/{id}")
	public String listByState(@PathParam("id") Long state_id) {
		CityDAO cityDAO = new CityDAO();
		List<City> cities = cityDAO.listByState(state_id);
		Gson gson = new Gson();
		String json = gson.toJson(cities);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/city/[code]
	@GET
	@Path("{id}")
	public String search(@PathParam("id") Long code) {
		CityDAO cityDAO = new CityDAO();
		City city = cityDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(city);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/city
	@POST
	public String save(String json) {
		CityDAO cityDAO = new CityDAO();
		Gson gson = new Gson();
		City city = gson.fromJson(json, City.class);
		cityDAO.save(city);

		json = gson.toJson(city);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/city/
	@PUT
	public String edit(String json) {
		CityDAO cityDAO = new CityDAO();
		Gson gson = new Gson();
		City city = gson.fromJson(json, City.class);
		cityDAO.update(city);
		json = gson.toJson(city);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/city/[code]
	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Long code) {
		CityDAO cityDAO = new CityDAO();
		City city = cityDAO.search(code);
		city.setDeleted('1');
		cityDAO.update(city);
		Gson gson = new Gson();
		String json = gson.toJson(city);

		return json;
	}
}
