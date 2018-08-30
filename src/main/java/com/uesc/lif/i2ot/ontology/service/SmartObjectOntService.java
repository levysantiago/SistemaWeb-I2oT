package com.uesc.lif.i2ot.ontology.service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;
import com.uesc.lif.i2ot.model.SmartObject;
import com.uesc.lif.i2ot.ontology.dao.SmartObjectOntDAO;
import com.uesc.lif.i2ot.ontology.model.SmartObjectOntStatus;

@Path("smartObjectOntology")
public class SmartObjectOntService {
	// Link: http://localhost:8080/I2oT/rest/smartObjectOntology/[id]
	@GET
	@Path("{id}")
	public String listStatus(@PathParam("id") Long id) {
		Gson gson = new Gson();
		SmartObjectOntDAO smartObjectOntDAO = new SmartObjectOntDAO();
		SmartObjectOntStatus smartObjectStatus = new SmartObjectOntStatus();
		String json = "";
		
		smartObjectOntDAO.beginTransaction();
		
		if(!smartObjectOntDAO.existsIndividual(id)) {
			return json;
		}
		
		smartObjectStatus.setAllowedPlace(smartObjectOntDAO.allowedPlace(id));
		smartObjectStatus.setAllowedPerson(smartObjectOntDAO.allowedPerson(id));
		smartObjectStatus.setAllowedMaterial(smartObjectOntDAO.allowedMaterial(id));
		
		smartObjectOntDAO.endTransaction();
		
		json = gson.toJson(smartObjectStatus);
		
		return json;
	}
	
	//Verifica o status de vários OI
	//Recebe uma lista de OI(json)
	// Link: http://localhost:8080/I2oT/rest/smartObjectOntology/status/[smartObjects]
	@GET
	@Path("/status/{smartObjects}")
	public String listAllStatus(@PathParam("smartObjects") String json) {
		Gson gson = new Gson();
		SmartObjectOntDAO smartObjectOntDAO = new SmartObjectOntDAO();
		SmartObjectOntStatus status = new SmartObjectOntStatus();
		//SmartObjectOntStatus smartObjectStatus = new SmartObjectOntStatus();
		
		SmartObject[] smartObjectsVector = gson.fromJson(json, SmartObject[].class);
		List<SmartObject> smartObjects = Arrays.asList(smartObjectsVector);
		
		smartObjectOntDAO.beginTransaction();
		
		for(SmartObject obj : smartObjects) {
			if(obj.isRegistered()) {
				smartObjectOntDAO.setLocation(obj.getId(), obj.getActualLocation().getName() +"_"+ obj.getActualLocation().getId());
				
				
				status.setAllowedPlace( smartObjectOntDAO.allowedPlace(obj.getId()) );
				status.setAllowedPerson( smartObjectOntDAO.allowedPerson(obj.getId()) );
				status.setAllowedMaterial( smartObjectOntDAO.allowedMaterial(obj.getId()) );
				obj.setStatus(status);
			}
		}
		
		smartObjectOntDAO.commitChanges();
		
		json = gson.toJson(smartObjects);
		
		return json;
	}
	
	// Link: http://localhost:8080/I2oT/rest/smartObjectOntology
	@POST
	public void save() {
		
	}
	
	
}
