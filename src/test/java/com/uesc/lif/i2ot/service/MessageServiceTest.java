package com.uesc.lif.i2ot.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Ignore;
import org.junit.Test;

public class MessageServiceTest {
	@Test
	@Ignore
	public void setMessage() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/I2oT/rest/message");
		Response r = target.request().post(Entity.text("Teste2"));
		System.out.println("Response: "+r.getStatus());
	}
}
