package com.uesc.lif.i2ot.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;
import com.uesc.lif.i2ot.dao.MessageDAO;
import com.uesc.lif.i2ot.model.Message;

@Path("message")
public class MessageService {
	
	// Link: http://localhost:8080/I2oT/rest/message
	
	@POST
	public String save(String json) {
		Gson gson = new Gson();
		MessageDAO messageDAO = new MessageDAO();
		System.out.println(json);
		Message message = gson.fromJson(json, Message.class);
		messageDAO.save(message);
		
		return json;
	}
	
	// Link: http://localhost:8080/I2oT/rest/message/smartObject/{id}
	@DELETE
	@Path("smartObject/{id}")
	public void deleteBySmartObject(@PathParam("id") Long id) {
		MessageDAO messageDAO = new MessageDAO();
		List<Message> messages = messageDAO.list();
		for(Message msg : messages) {
			if(msg.getSmartObject().getId() == id) {
				msg.setDeleted('1');
				messageDAO.update(msg);
				break;
			}
		}
	}
	
	// Link: http://localhost:8080/I2oT/rest/message/{id}
	@GET
	@Path("{id}")
	public String search(@PathParam("id") Long id) {
		MessageDAO messageDAO = new MessageDAO();
		Message msg = messageDAO.search(id);
		Gson gson = new Gson();
		String json = gson.toJson(msg);
		
		return json;
	}
	
	// Link: http://localhost:8080/I2oT/rest/message
	@GET
	public String list() {
		MessageDAO messageDAO = new MessageDAO();
		Gson gson = new Gson();
		List<Message> messages = messageDAO.list();
		
		return gson.toJson(messages);
	}
}
