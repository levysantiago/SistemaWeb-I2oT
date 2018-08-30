package com.uesc.lif.i2ot.util;

import org.junit.Test;

public class RfidReaderTest {
	@Test
	public void on() {
		RfidReader rfid = new RfidReader();
		try {
			rfid.ligaRFID();
		} catch (Exception e) {
			System.out.println("Conecte o aparelho");
		}
		
		while(true);
	}
}
