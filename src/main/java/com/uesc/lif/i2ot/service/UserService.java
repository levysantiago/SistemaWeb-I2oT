package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.UserDAO;
import com.uesc.lif.i2ot.model.User;

@Path("user")
public class UserService {
	// Link: //http://localhost:8080/I2oT/rest/user
	@GET
	public String list() {
		UserDAO userDAO = new UserDAO();
		List<User> user = userDAO.list();
		Gson gson = new Gson();

		String gsonUser = gson.toJson(user);

		return gsonUser;
	}

	// Link: //http://localhost:8080/I2oT/rest/user/[code]
	@GET
	@Path("{id_user}")
	public String search(@PathParam("id_user") Long code) {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(user);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/user
	@POST
	public String save(String json) {
		UserDAO userDAO = new UserDAO();
		Gson gson = new Gson();
		User user = gson.fromJson(json, User.class);
		userDAO.save(user);

		json = gson.toJson(user);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/user
	@PUT
	public String edit(String json) {
		UserDAO userDAO = new UserDAO();
		Gson gson = new Gson();
		User user = gson.fromJson(json, User.class);
		userDAO.update(user);
		json = gson.toJson(user);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/user/[code]
	@DELETE
	@Path("{id_user}")
	public String delete(@PathParam("id_user") Long code) {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.search(code);
		user.setDeleted('1');
		userDAO.update(user);
		Gson gson = new Gson();
		String json = gson.toJson(user);

		return json;
	}
}
