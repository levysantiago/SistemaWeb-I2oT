package com.uesc.lif.i2ot.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class I2oTResourceConfig extends ResourceConfig{
	public I2oTResourceConfig() {
		packages("com.uesc.lif.i2ot.service;com.uesc.lif.i2ot.ontology.service");
	}
}
