package com.uesc.lif.i2ot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;
import com.uesc.lif.i2ot.dao.SmartObjectDAO;
import com.uesc.lif.i2ot.model.SmartObject;

@Path("smartObject")
public class SmartObjectService {
	// Link: //http://localhost:8080/I2oT/rest/smartObject
	@GET
	public String list() {
		SmartObjectDAO smartObjectDAO = new SmartObjectDAO();
		List<SmartObject> smartObject = smartObjectDAO.list();
		Gson gson = new Gson();

		String gsonSmartObject = gson.toJson(smartObject);

		return gsonSmartObject;
	}

	// Link: //http://localhost:8080/I2oT/rest/smartObject/[code]
	@GET
	@Path("{id}")
	public String search(@PathParam("id") Long code) {
		SmartObjectDAO smartObjectDAO = new SmartObjectDAO();
		SmartObject smartObject = smartObjectDAO.search(code);
		Gson gson = new Gson();
		String json = gson.toJson(smartObject);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/smartObject/byTag/[tag]
	@GET
	@Path("/byTag/{tagsRFID}")
	public String listByTag(@PathParam("tagsRFID") String tagsJson) {
		SmartObjectDAO smartObjectDAO = new SmartObjectDAO();
		List<SmartObject> smartObjects = new ArrayList<SmartObject>();
		String json;
		Gson gson = new Gson();
		
		String[] tagsVector = gson.fromJson(tagsJson, String[].class);
		List<String> tags = Arrays.asList(tagsVector);
		
		for(String tag : tags) {
			SmartObject obj = new SmartObject();
			try {
				obj = smartObjectDAO.searchBy(SmartObject.findByTag, "tagRfid", tag);
				obj.setRegistered(true);				
				smartObjects.add( obj );
			}catch(Exception e){
				//obj.setDescription( Faces.getResourceBundle("msg").getString("naoCadastrado") );
				obj.setDescription( "Não Cadastrado" );
				obj.setTagRfid(tag);
				obj.setRegistered(false);
				smartObjects.add(obj);
			}
		}
		
		json = gson.toJson(smartObjects);
		
		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/smartObject
	@POST
	public String save(String json) {
		SmartObjectDAO smartObjectDAO = new SmartObjectDAO();
		Gson gson = new Gson();
		SmartObject smartObject = gson.fromJson(json, SmartObject.class);
		
		//Setting the actual place
		smartObject.setActualLocation(smartObject.getLocation());
		
		smartObjectDAO.save(smartObject);

		json = gson.toJson(smartObject);

		return json;
	}

	// Link: //http://localhost:8080/I2oT/rest/smartObject
	@PUT
	public String edit(String json) {
		SmartObjectDAO smartObjectDAO = new SmartObjectDAO();
		Gson gson = new Gson();
		SmartObject smartObject = gson.fromJson(json, SmartObject.class);
		smartObject = smartObjectDAO.update(smartObject);
		json = gson.toJson(smartObject);

		return json;

	}

	// Link: //http://localhost:8080/I2oT/rest/smartObject/[code]
	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Long code) {
		SmartObjectDAO smartObjectDAO = new SmartObjectDAO();
		SmartObject smartObject = smartObjectDAO.search(code);
		smartObject.setDeleted('1');
		smartObjectDAO.update(smartObject);
		Gson gson = new Gson();
		String json = gson.toJson(smartObject);

		return json;
	}
}
