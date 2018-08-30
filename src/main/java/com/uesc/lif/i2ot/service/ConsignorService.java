package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.ConsignorDAO;
import com.uesc.lif.i2ot.model.Consignor;

@Path("consignor")
public class ConsignorService {
	// Link: //http://localhost:8080/I2oT/rest/consignor
	@GET
	public String list() {
		ConsignorDAO consignorDAO = new ConsignorDAO();
		List<Consignor> consignor = consignorDAO.list();
		Gson gson = new Gson();

		String gsonConsignor = gson.toJson(consignor);

		return gsonConsignor;
	}

	// Link: //http://localhost:8080/I2oT/rest/consignor/[code]
	@GET
	@Path("{id_consignor}")
	public String search(@PathParam("id_consignor") Long code) {
		ConsignorDAO consignorDAO = new ConsignorDAO();
		Consignor consignor = consignorDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(consignor);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/consignor
	@POST
	public String save(String json) {
		ConsignorDAO consignorDAO = new ConsignorDAO();
		Gson gson = new Gson();
		Consignor consignor = gson.fromJson(json, Consignor.class);
		consignorDAO.save(consignor);

		json = gson.toJson(consignor);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/consignor
	@PUT
	public String edit(String json) {
		ConsignorDAO consignorDAO = new ConsignorDAO();
		Gson gson = new Gson();
		Consignor consignor = gson.fromJson(json, Consignor.class);
		consignorDAO.update(consignor);
		json = gson.toJson(consignor);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/consignor/[code]
	@DELETE
	@Path("{id_consignor}")
	public String delete(@PathParam("id_consignor") Long code) {
		ConsignorDAO consignorDAO = new ConsignorDAO();
		Consignor consignor = consignorDAO.search(code);
		consignor.setDeleted('1');
		consignorDAO.update(consignor);
		Gson gson = new Gson();
		String json = gson.toJson(consignor);

		return json;
	}
}
