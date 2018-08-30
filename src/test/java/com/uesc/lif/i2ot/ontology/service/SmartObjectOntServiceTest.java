package com.uesc.lif.i2ot.ontology.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class SmartObjectOntServiceTest {
	@Test
	public void status() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/I2oT/rest/smartObjectOntology");
		String json = target.path("{id}").resolveTemplate("id", 11l).request().get(String.class);
		System.out.println(json);
	}
}

/*
 *  SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
	SLF4J: Defaulting to no-operation (NOP) logger implementation
	SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
 * */
