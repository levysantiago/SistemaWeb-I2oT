package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.PhoneDAO;
import com.uesc.lif.i2ot.model.Phone;

@Path("phone")
public class PhoneService {
	// Link: //http://localhost:8080/I2oT/rest/phone
	@GET
	public String list() {
		PhoneDAO phoneDAO = new PhoneDAO();
		List<Phone> phone = phoneDAO.list();
		Gson gson = new Gson();

		String gsonPhone = gson.toJson(phone);

		return gsonPhone;
	}

	// Link: //http://localhost:8080/I2oT/rest/phone/[code]
	@GET
	@Path("{id_phone}")
	public String search(@PathParam("id_phone") Long code) {
		PhoneDAO phoneDAO = new PhoneDAO();
		Phone phone = phoneDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(phone);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/phone
	@POST
	public String save(String json) {
		PhoneDAO phoneDAO = new PhoneDAO();
		Gson gson = new Gson();
		Phone phone = gson.fromJson(json, Phone.class);
		phoneDAO.save(phone);

		json = gson.toJson(phone);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/phone
	@PUT
	public String edit(String json) {
		PhoneDAO phoneDAO = new PhoneDAO();
		Gson gson = new Gson();
		Phone phone = gson.fromJson(json, Phone.class);
		phoneDAO.update(phone);
		json = gson.toJson(phone);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/phone/[code]
	@DELETE
	@Path("{id_phone}")
	public String delete(@PathParam("id_phone") Long code) {
		PhoneDAO phoneDAO = new PhoneDAO();
		Phone phone = phoneDAO.search(code);
		phone.setDeleted('1');
		phoneDAO.update(phone);
		Gson gson = new Gson();
		String json = gson.toJson(phone);

		return json;
	}
}
