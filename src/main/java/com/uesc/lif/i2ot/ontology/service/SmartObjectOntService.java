package com.uesc.lif.i2ot.ontology.service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import com.google.gson.Gson;
import com.uesc.lif.i2ot.model.Location;
import com.uesc.lif.i2ot.model.RfidReader;
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
	
	// ESSE É O MÉTODO QUE A PÁGINA WEB VAI CHAMAR
	
	// Link: http://localhost:8080/I2oT/rest/smartObjectOntology/[smartObject]
	// Se o objeto não está cadastrado na ontologia, o json envia vazio ""
	@GET
	@Path("/info/{smartObject}")
	public String listPageInformation(@PathParam("smartObject") String json) {
		Gson gson = new Gson();
		SmartObjectOntDAO smartObjectOntDAO = new SmartObjectOntDAO();
		SmartObjectOntStatus smartObjectStatus = new SmartObjectOntStatus();
		Long id;
		
		//Transformando json para smart object
		SmartObject smartObject = gson.fromJson(json, SmartObject.class);
		id = smartObject.getId();
		
		smartObjectOntDAO.beginTransaction();
		
		if(!smartObjectOntDAO.existsIndividual(id)) {
			return "";
		}
		
		smartObjectStatus.setAllowedPlace(smartObjectOntDAO.allowedPlace(id));
		smartObjectStatus.setAllowedPerson(smartObjectOntDAO.allowedPerson(id));
		smartObjectStatus.setAllowedMaterial(smartObjectOntDAO.allowedMaterial(id));
		smartObject.setMovedBySome( smartObjectOntDAO.movedBySome(id) );
		
		smartObjectOntDAO.endTransaction();
		
		smartObject.setStatus(smartObjectStatus);
		json = gson.toJson(smartObject);
		
		return json;
	}
	
	// ESSE É O MÉTODO QUE O MIDDLEWARE VAI CHAMAR
	
	//Caso o retorno seja "", a tag não está cadastrada
	// Link: http://localhost:8080/I2oT/rest/smartObjectOntology/sensor/[sensorJson]
	@POST
	@Path("/sensor")
	public void listStatusByTag(String sensorJson) {
		String json = "";
		String actualLocation;
		Gson gson = new Gson();
		SmartObject smartObject;
		SmartObjectOntStatus status;
		Long id;
		Long locationId;
		Location location;
		RfidReader rfid = gson.fromJson(sensorJson, RfidReader.class);
		SmartObjectOntDAO smartObjectOntDAO = new SmartObjectOntDAO();
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
		json = target.path("/smartObject/idByTag/{tagRFID}").resolveTemplate("tagRFID", rfid.getTag_lida()).request().get(String.class);
		
		if(json != "") {
			smartObject = gson.fromJson(json, SmartObject.class);
			id = smartObject.getId();
			//Armazenando o nome da localização e recuperando o id da string
			actualLocation = rfid.getLocal();
			locationId = Long.parseLong( actualLocation.split("_")[1] );
			
			System.out.println("Atualizando localização com "+actualLocation+" id: "+locationId);
			//Atualizando objeto com a localização atual (Assumindo que a localização está cadastrada na ontologia)
			smartObjectOntDAO.beginTransaction();
			smartObjectOntDAO.setLocation(id, actualLocation);
			smartObjectOntDAO.commitChanges();
			
			//Obtendo a localização (no BD) recebida como string pelo middleware
			json = target.path("location/{id}").resolveTemplate("id", locationId).request().get(String.class);
			location = gson.fromJson(json, Location.class);
			
			//Se o objeto foi lido em um outro local diferente do que ele estava
			if(smartObject.getActualLocation() == null || smartObject.getActualLocation().getId() != locationId) {
				smartObject.setActualLocation(location);
				json = gson.toJson(smartObject);
				target.path("smartObject/").request().put(Entity.json(json));
				
				json = listStatus(id);
				
				System.out.println(json);
				
				//Emitindo alerta no Middleware
				status = gson.fromJson(json, SmartObjectOntStatus.class);
				smartObject.setStatus(status);
				json = gson.toJson(smartObject);
				
				boolean decider = status.isAllowedMaterial() && status.isAllowedPerson() && status.isAllowedPlace();
				if(!decider) {
					target = client.target("http://192.168.43.27:5001/rfid/alert/");
					target.request().post(Entity.json(json));
				}
				
				//Verificando se a nova localização é aceita
				if(status.isAllowedPlace()) {
					target = client.target("http://localhost:8080/I2oT/rest/message/smartObject");
					target.path("{id}").resolveTemplate("id", id).request().delete();
				}
			}
		}
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
		
		SmartObject[] smartObjectsVector = gson.fromJson(json, SmartObject[].class);
		List<SmartObject> smartObjects = Arrays.asList(smartObjectsVector);
		
		smartObjectOntDAO.beginTransaction();
		
		for(SmartObject obj : smartObjects) {
			if(obj.isRegistered()) {
				smartObjectOntDAO.setLocation(obj.getId(), obj.getActualLocation().getName() +"_"+ obj.getActualLocation().getId());
				
				
				status.setAllowedPlace( smartObjectOntDAO.allowedPlace(obj.getId()) );
				status.setAllowedPerson( smartObjectOntDAO.allowedPerson(obj.getId()) );
				status.setAllowedMaterial( smartObjectOntDAO.allowedMaterial(obj.getId()) );
				status.setMoving( smartObjectOntDAO.isMoving(obj.getId()) );
				obj.setStatus(status);
			}
		}
		
		smartObjectOntDAO.commitChanges();
		
		json = gson.toJson(smartObjects);
		
		return json;
	}
}
