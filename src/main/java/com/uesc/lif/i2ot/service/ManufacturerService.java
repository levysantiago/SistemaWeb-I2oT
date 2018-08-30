package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.ManufacturerDAO;
import com.uesc.lif.i2ot.model.Manufacturer;

@Path("manufacturer")
public class ManufacturerService {
	// Link: //http://localhost:8080/I2oT/rest/manufacturer
	@GET
	public String list() {
		ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
		List<Manufacturer> manufacturer = manufacturerDAO.list();
		Gson gson = new Gson();

		String gsonManufacturer = gson.toJson(manufacturer);

		return gsonManufacturer;
	}

	// Link: //http://localhost:8080/I2oT/rest/manufacturer/[code]
	@GET
	@Path("{id_manufacturer}")
	public String search(@PathParam("id_manufacturer") Long code) {
		ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
		Manufacturer manufacturer = manufacturerDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(manufacturer);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/manufacturer
	@POST
	public String save(String json) {
		ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
		Gson gson = new Gson();
		Manufacturer manufacturer = gson.fromJson(json, Manufacturer.class);
		manufacturerDAO.save(manufacturer);

		json = gson.toJson(manufacturer);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/manufacturer
	@PUT
	public String edit(String json) {
		ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
		Gson gson = new Gson();
		Manufacturer manufacturer = gson.fromJson(json, Manufacturer.class);
		manufacturerDAO.update(manufacturer);
		json = gson.toJson(manufacturer);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/manufacturer/[code]
	@DELETE
	@Path("{id_manufacturer}")
	public String delete(@PathParam("id_manufacturer") Long code) {
		ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
		Manufacturer manufacturer = manufacturerDAO.search(code);
		manufacturer.setDeleted('1');
		manufacturerDAO.update(manufacturer);
		Gson gson = new Gson();
		String json = gson.toJson(manufacturer);

		return json;
	}
}
