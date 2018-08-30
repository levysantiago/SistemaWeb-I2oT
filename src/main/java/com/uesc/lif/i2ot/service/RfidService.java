package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.RfidDAO;
import com.uesc.lif.i2ot.model.Rfid;

@Path("rfid")
public class RfidService {
	// Link: //http://localhost:8080/I2oT/rest/rfid
	@GET
	public String list() {
		RfidDAO rfidDAO = new RfidDAO();
		List<Rfid> rfid = rfidDAO.list();
		Gson gson = new Gson();

		String gsonRfid = gson.toJson(rfid);

		return gsonRfid;
	}

	// Link: //http://localhost:8080/I2oT/rest/rfid/[code]
	@GET
	@Path("{id}")
	public String search(@PathParam("id") Long code) {
		RfidDAO rfidDAO = new RfidDAO();
		Rfid rfid = rfidDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(rfid);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/rfid
	@POST
	public String save(String json) {
		RfidDAO rfidDAO = new RfidDAO();
		Gson gson = new Gson();
		Rfid rfid = gson.fromJson(json, Rfid.class);
		rfidDAO.save(rfid);

		json = gson.toJson(rfid);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/rfid
	@PUT
	public String edit(String json) {
		RfidDAO rfidDAO = new RfidDAO();
		Gson gson = new Gson();
		Rfid rfid = gson.fromJson(json, Rfid.class);
		rfidDAO.update(rfid);
		json = gson.toJson(rfid);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/rfid/[code]
	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Long code) {
		RfidDAO rfidDAO = new RfidDAO();
		Rfid rfid = rfidDAO.search(code);
		rfid.setDeleted('1');
		rfidDAO.update(rfid);
		Gson gson = new Gson();
		String json = gson.toJson(rfid);

		return json;
	}
}
