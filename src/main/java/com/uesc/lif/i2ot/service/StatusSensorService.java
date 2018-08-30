package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.StatusSensorDAO;
import com.uesc.lif.i2ot.model.StatusSensor;

@Path("statusSensor")
public class StatusSensorService {
	// Link: //http://localhost:8080/I2oT/rest/statusSensor
	@GET
	public String list() {
		StatusSensorDAO statusSensorDAO = new StatusSensorDAO();
		List<StatusSensor> statusSensor = statusSensorDAO.list();
		Gson gson = new Gson();

		String gsonStatusSensor = gson.toJson(statusSensor);

		return gsonStatusSensor;
	}

	// Link: //http://localhost:8080/I2oT/rest/statusSensor/[code]
	@GET
	@Path("{id_statusSensor}")
	public String search(@PathParam("id_statusSensor") Long code) {
		StatusSensorDAO statusSensorDAO = new StatusSensorDAO();
		StatusSensor statusSensor = statusSensorDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(statusSensor);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/statusSensor
	@POST
	public String save(String json) {
		StatusSensorDAO statusSensorDAO = new StatusSensorDAO();
		Gson gson = new Gson();
		StatusSensor statusSensor = gson.fromJson(json, StatusSensor.class);
		statusSensorDAO.save(statusSensor);

		json = gson.toJson(statusSensor);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/statusSensor
	@PUT
	public String edit(String json) {
		StatusSensorDAO statusSensorDAO = new StatusSensorDAO();
		Gson gson = new Gson();
		StatusSensor statusSensor = gson.fromJson(json, StatusSensor.class);
		statusSensorDAO.update(statusSensor);
		json = gson.toJson(statusSensor);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/statusSensor/[code]
	@DELETE
	@Path("{id_statusSensor}")
	public String delete(@PathParam("id_statusSensor") Long code) {
		StatusSensorDAO statusSensorDAO = new StatusSensorDAO();
		StatusSensor statusSensor = statusSensorDAO.search(code);
		statusSensor.setDeleted('1');
		statusSensorDAO.update(statusSensor);
		Gson gson = new Gson();
		String json = gson.toJson(statusSensor);

		return json;
	}
}
