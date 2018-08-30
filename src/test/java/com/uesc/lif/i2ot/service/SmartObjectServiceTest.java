package com.uesc.lif.i2ot.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

import com.google.gson.Gson;
import com.uesc.lif.i2ot.model.SmartObject;

public class SmartObjectServiceTest {
	@Test
	public void getSmartObjectsByTag() {
		List<String> tags = new ArrayList<String>();
		Client client = ClientBuilder.newClient();
		tags.add("000000000000000000000118");
		tags.add("000000000000000000000119");
		WebTarget target = client.target("http://localhost:8080/I2oT/rest/");
		String json = target.path("smartObject/byTag/{tagsRFID}").resolveTemplate("tagsRFID", tags).request().get(String.class);
		Gson gson = new Gson();
		SmartObject[] objs = gson.fromJson(json, SmartObject[].class);
		System.out.println(objs[0]);
		System.out.println(json);
	}
}
