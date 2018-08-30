package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.InfraredSensorDAO;
import com.uesc.lif.i2ot.model.InfraredSensor;

@Path("infraredSensor")
public class InfraredSensorService {
	// Link: //http://localhost:8080/I2oT/rest/infraredSensor
	@GET
	public String list() {
		InfraredSensorDAO infraredSensorDAO = new InfraredSensorDAO();
		List<InfraredSensor> infraredSensor = infraredSensorDAO.list();
		Gson gson = new Gson();

		String gsonInfraredSensor = gson.toJson(infraredSensor);

		return gsonInfraredSensor;
	}

	// Link: //http://localhost:8080/I2oT/rest/infraredSensor/[code]
	@GET
	@Path("{id_infraredSensor}")
	public String search(@PathParam("id_infraredSensor") Long code) {
		InfraredSensorDAO infraredSensorDAO = new InfraredSensorDAO();
		InfraredSensor infraredSensor = infraredSensorDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(infraredSensor);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/infraredSensor
	@POST
	public String save(String json) {
		InfraredSensorDAO infraredSensorDAO = new InfraredSensorDAO();
		Gson gson = new Gson();
		InfraredSensor infraredSensor = gson.fromJson(json, InfraredSensor.class);
		infraredSensorDAO.save(infraredSensor);

		json = gson.toJson(infraredSensor);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/infraredSensor
	@PUT
	public String edit(String json) {
		InfraredSensorDAO infraredSensorDAO = new InfraredSensorDAO();
		Gson gson = new Gson();
		InfraredSensor infraredSensor = gson.fromJson(json, InfraredSensor.class);
		infraredSensorDAO.update(infraredSensor);
		json = gson.toJson(infraredSensor);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/infraredSensor/[code]
	@DELETE
	@Path("{id_infraredSensor}")
	public String delete(@PathParam("id_infraredSensor") Long code) {
		InfraredSensorDAO infraredSensorDAO = new InfraredSensorDAO();
		InfraredSensor infraredSensor = infraredSensorDAO.search(code);
		infraredSensor.setDeleted('1');
		infraredSensorDAO.update(infraredSensor);
		Gson gson = new Gson();
		String json = gson.toJson(infraredSensor);

		return json;
	}
}
