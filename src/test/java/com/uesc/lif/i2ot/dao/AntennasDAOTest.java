package com.uesc.lif.i2ot.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.uesc.lif.i2ot.model.Antennas;

public class AntennasDAOTest {
	@Test
	public void list() {
		AntennasDAO antennaDAO = new AntennasDAO();
		List<Antennas> antennas = antennaDAO.list();
		for(Antennas a : antennas) {
			System.out.println(a.getName());
		}
	}
	
	@Test
	@Ignore
	public void save() {
		AntennasDAO antennaDAO = new AntennasDAO();
		Antennas antenna = new Antennas();
		antenna.setName("Antenna1");
		Short n = 1;
		antenna.setNumber(n);
		antennaDAO.save(antenna);
	}
}
