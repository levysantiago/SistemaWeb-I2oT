package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.AddressDAO;
import com.uesc.lif.i2ot.model.Address;

@Path("address")
public class AddressService {
	// Link: //http://localhost:8080/I2oT/rest/address
	@GET
	public String list() {
		AddressDAO addressDAO = new AddressDAO();
		List<Address> address = addressDAO.list();
		Gson gson = new Gson();

		String gsonAddress = gson.toJson(address);

		return gsonAddress;
	}

	// Link: //http://localhost:8080/I2oT/rest/address/[code]
	@GET
	@Path("{id}")
	public String search(@PathParam("id") Long code) {
		AddressDAO addressDAO = new AddressDAO();
		Address address = addressDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(address);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/address
	@POST
	public String save(String json) {
		AddressDAO addressDAO = new AddressDAO();
		Gson gson = new Gson();
		Address address = gson.fromJson(json, Address.class);
		Long addressId = addressDAO.saveGetId(address);
		address.setId(addressId);

		json = gson.toJson(address);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/address/
	@PUT
	public String edit(String json) {
		AddressDAO addressDAO = new AddressDAO();
		Gson gson = new Gson();
		Address address = gson.fromJson(json, Address.class);
		addressDAO.update(address);
		json = gson.toJson(address);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/address/[code]
	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Long code) {
		AddressDAO addressDAO = new AddressDAO();
		Address address = addressDAO.search(code);
		address.setDeleted('1');
		addressDAO.update(address);
		Gson gson = new Gson();
		String json = gson.toJson(address);

		return json;
	}
}
