package com.uesc.lif.i2ot.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.google.gson.Gson;
import com.uesc.lif.i2ot.model.Location;
import com.uesc.lif.i2ot.model.SmartObject;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RfidReaderPageBean implements Serializable {
	//private final int ONE_TAG_SIZE = 24;
	private String tags;
	private List<SmartObject> smartObjects;

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<SmartObject> getSmartObjects() {
		return smartObjects;
	}

	public void setSmartObjects(List<SmartObject> smartObjects) {
		this.smartObjects = smartObjects;
	}
	
	public String FlameRfidReaderPage() {
		return "RfidReaderPage";
	}
	
	public String FlameRfidReaderPageFull() {
		return "RfidReaderPageFull";
	}

	public void newObj() {
		smartObjects = new ArrayList<SmartObject>();
	}

	/*public List<String> tagsToList() {
		int tagsSize = tags.length();
		int begin = 0, end = ONE_TAG_SIZE;
		List<String> tagsList = new ArrayList<String>();

		while (end <= tagsSize) {
			tagsList.add(tags.substring(begin, end));
			begin = end;
			end = end + ONE_TAG_SIZE;
		}

		return tagsList;
	}*/
	
	/*A test method with the phidget*/
	public List<String> tagsToList() {
		String[] tagsVector = tags.split(";");
		List<String> tagsList = Arrays.asList(tagsVector);

		return tagsList;
	}

	public void verifyTags(ActionEvent event) {
		try {
			List<String> tags = tagsToList();
			System.out.println("lista tags: " + tags);

			if (!tags.isEmpty()) {
				Client client = ClientBuilder.newClient();
				WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
				String json, jsonLocation;
				Gson gson = new Gson();
				SmartObject[] smartObjectsVector;
				//smartObjects = new ArrayList<SmartObject>();
				
				//Requesting each smart objects by tag
				json = target.path("smartObject/byTag/{tagsRFID}").resolveTemplate("tagsRFID", tags).request().get(String.class);
				
				smartObjectsVector = gson.fromJson(json, SmartObject[].class);
				smartObjects = Arrays.asList(smartObjectsVector);
				
				//Setando o local (Saleta) atual diretamente
				jsonLocation = target.path("location/{id_location}").resolveTemplate("id_location", 1l).request().get(String.class);
				Location testeActualLocation = gson.fromJson(jsonLocation, Location.class);
				for (SmartObject smartObject : smartObjects) {
					smartObject.setActualLocation(testeActualLocation);
				}
				
				//Setting the json smart objects again, to update it
				json = gson.toJson(smartObjects);
				
				//Getting the status of all smart objects
				json = target.path("smartObjectOntology/status/{smartObjects}").resolveTemplate("smartObjects", json).request().get(String.class);
				
				//Getting the smart objects from json
				smartObjectsVector = gson.fromJson(json, SmartObject[].class);
				smartObjects = Arrays.asList(smartObjectsVector);
				
				Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("dadosProcessados"));
			} else {
				Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("invalidEntry"));
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarOIErro"));
			e.printStackTrace();
		}
	}

}
