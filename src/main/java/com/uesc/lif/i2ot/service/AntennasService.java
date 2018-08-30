package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.AntennasDAO;
import com.uesc.lif.i2ot.model.Antennas;

@Path("antennas")
public class AntennasService {
	// Link: //http://localhost:8080/I2oT/rest/antennas
	@GET
	public String list() {
		AntennasDAO antennaDAO = new AntennasDAO();
		List<Antennas> antennas = antennaDAO.list();
		Gson gson = new Gson();

		String gsonAntennas = gson.toJson(antennas);

		return gsonAntennas;
	}

	// Link: //http://localhost:8080/I2oT/rest/antennas/[code]
	@GET
	@Path("{id}")
	public String search(@PathParam("id") Long code) {
		AntennasDAO antennaDAO = new AntennasDAO();
		Antennas antenna = antennaDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(antenna);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/antennas
	@POST
	public String save(String json) {
		AntennasDAO antennaDAO = new AntennasDAO();
		Gson gson = new Gson();
		Antennas antenna = gson.fromJson(json, Antennas.class);
		antennaDAO.save(antenna);

		json = gson.toJson(antenna);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/antennas/
	@PUT
	public String edit(String json) {
		AntennasDAO antennaDAO = new AntennasDAO();
		Gson gson = new Gson();
		Antennas antenna = gson.fromJson(json, Antennas.class);
		antennaDAO.update(antenna);
		json = gson.toJson(antenna);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/antennas/[code]
	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Long code) {
		AntennasDAO antennaDAO = new AntennasDAO();
		Antennas antenna = antennaDAO.search(code);
		antenna.setDeleted('1');
		antennaDAO.update(antenna);
		Gson gson = new Gson();
		String json = gson.toJson(antenna);

		return json;
	}

}
