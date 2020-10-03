package com.uesc.lif.i2ot.controller;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class ObjectsPageBeanTest {
	@Test
	public void updateMensagens() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/I2oT/rest/mensagens/");
		target.request().get();
	}
}
