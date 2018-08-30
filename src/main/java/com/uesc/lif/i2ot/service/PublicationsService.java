package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import com.uesc.lif.i2ot.dao.PublicationsDAO;
import com.uesc.lif.i2ot.model.Publications;

@Path("publications")
public class PublicationsService {
	// Link: //http://localhost:8080/I2oT/rest/publications
	@GET
	public String list() {
		PublicationsDAO publicationsDAO = new PublicationsDAO();
		List<Publications> publications = publicationsDAO.list();
		Gson gson = new Gson();

		String gsonPublications = gson.toJson(publications);

		return gsonPublications;
	}

	// Link: //http://localhost:8080/I2oT/rest/publications/[code]
	@GET
	@Path("{id_publications}")
	public String search(@PathParam("id_publications") Long code) {
		PublicationsDAO publicationsDAO = new PublicationsDAO();
		Publications publications = publicationsDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(publications);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/publications
	@POST
	public String save(String json) {
		PublicationsDAO publicationsDAO = new PublicationsDAO();
		Gson gson = new Gson();
		Publications publications = gson.fromJson(json, Publications.class);
		publicationsDAO.save(publications);

		json = gson.toJson(publications);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/publications
	@PUT
	public String edit(String json) {
		PublicationsDAO publicationsDAO = new PublicationsDAO();
		Gson gson = new Gson();
		Publications publications = gson.fromJson(json, Publications.class);
		publicationsDAO.update(publications);
		json = gson.toJson(publications);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/publications/[code]
	@DELETE
	@Path("{id_publications}")
	public String delete(@PathParam("id_publications") Long code) {
		PublicationsDAO publicationsDAO = new PublicationsDAO();
		Publications publications = publicationsDAO.search(code);
		publications.setDeleted('1');
		publicationsDAO.update(publications);
		Gson gson = new Gson();
		String json = gson.toJson(publications);

		return json;
	}
}
