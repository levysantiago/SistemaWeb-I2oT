package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.StateDAO;
import com.uesc.lif.i2ot.model.State;

@Path("state")
public class StateService {
	// Link: //http://localhost:8080/I2oT/rest/state
	@GET
	public String list() {
		StateDAO stateDAO = new StateDAO();
		List<State> state = stateDAO.list();
		Gson gson = new Gson();

		String gsonState = gson.toJson(state);

		return gsonState;
	}

	// Link: //http://localhost:8080/I2oT/rest/state/[code]
	@GET
	@Path("{id}")
	public String search(@PathParam("id") Long code) {
		StateDAO stateDAO = new StateDAO();
		State state = stateDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(state);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/state
	@POST
	public String save(String json) {
		StateDAO stateDAO = new StateDAO();
		Gson gson = new Gson();
		State state = gson.fromJson(json, State.class);
		stateDAO.save(state);

		json = gson.toJson(state);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/state
	@PUT
	public String edit(String json) {
		StateDAO stateDAO = new StateDAO();
		Gson gson = new Gson();
		State state = gson.fromJson(json, State.class);
		stateDAO.update(state);
		json = gson.toJson(state);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/state/[code]
	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Long code) {
		StateDAO stateDAO = new StateDAO();
		State state = stateDAO.search(code);
		state.setDeleted('1');
		stateDAO.update(state);
		Gson gson = new Gson();
		String json = gson.toJson(state);

		return json;
	}
}
