package com.uesc.lif.i2ot.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import com.google.gson.Gson;
import com.uesc.lif.i2ot.model.Message;
import com.uesc.lif.i2ot.model.SmartObject;
import com.uesc.lif.i2ot.ontology.model.SmartObjectOntStatus;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ObjectsPageBean implements Serializable{
	private SmartObject smartObject;
	private List<SmartObject> smartObjects;
	private String locationName = "";
	private String movedBy = "";
	private String status1, status2, status3;
	private List<Message> mensagens;
	
	public SmartObject getSmartObject() {
		return smartObject;
	}
	public void setSmartObject(SmartObject smartObject) {
		this.smartObject = smartObject;
	}
	public List<SmartObject> getSmartObjects() {
		return smartObjects;
	}
	public void setSmartObjects(List<SmartObject> smartObjects) {
		this.smartObjects = smartObjects;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getMovedBy() {
		return movedBy;
	}
	public void setMovedBy(String movedBy) {
		this.movedBy = movedBy;
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status) {
		this.status1 = status;
	}
	public String getStatus2() {
		return status2;
	}
	public void setStatus2(String status) {
		this.status2 = status;
	}
	public String getStatus3() {
		return status3;
	}
	public void setStatus3(String status) {
		this.status3 = status;
	}
	public List<Message> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<Message> mensagens) {
		this.mensagens = mensagens;
	}
	
	@PostConstruct
	public void list() {
		try {
			Gson gson = new Gson();
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			String json = target.path("smartObject").request().get(String.class);
			SmartObject[] smartObjectVector = gson.fromJson(json, SmartObject[].class);
			smartObjects = Arrays.asList(smartObjectVector);
			
			json = target.path("message").request().get(String.class);
			Message[] mensagensVector = gson.fromJson(json, Message[].class);
			mensagens = Arrays.asList(mensagensVector);
		} catch (RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarOIErro"));
			e.printStackTrace();
		}
	}
	
	public void update(ActionEvent event) {
		smartObject = (SmartObject) event.getComponent().getAttributes().get("smartObjectSelected");
		try {
			Gson gson = new Gson();
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
			String json;
			status1 = status2 = status3 = "";
			
			json = target.path("smartObject/{id}").resolveTemplate("id", smartObject.getId()).request().get(String.class);
			smartObject = gson.fromJson(json, SmartObject.class);
			
			
			json = gson.toJson(smartObject);
			json = target.path("smartObjectOntology/info/{smartObject}").resolveTemplate("smartObject", json).request().get(String.class);
			smartObject = gson.fromJson(json, SmartObject.class);
			
			
			locationName = smartObject.getActualLocation().getName() + ", " + smartObject.getActualLocation().getAddress();
			
			SmartObjectOntStatus objStatus = smartObject.getStatus();
			if(objStatus.isAllowedPlace()) {
				status1 = "Localização permitida";
			}else {
				status1 = "ATENÇÃO: Esse objeto não pode estar nesta localização!";
			}
			
			if(objStatus.isAllowedPerson()) {
				status2 = "Pessoa permitida";
			}else {
				status2 = "ATENÇÃO: Esse objeto não pode ser movido por um "+smartObject.getMovedBySome();
			}
			
			if(objStatus.isAllowedMaterial()) {
				status3 = "Material permitido";
			}else {
				status3 = "ATENÇÃO: Esse objeto tem material perigoso!";
			}
			
			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("dadosProcessados"));
		}catch(RuntimeException e) {
			Messages.addGlobalError(Faces.getResourceBundle("msg").getString("listarOIErro"));
			e.printStackTrace();
		}
	}
	
	public void refreshMensagens() {
		System.out.println("Aqui: "+ mensagens);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("msgList:formMensagem");
	}
}
