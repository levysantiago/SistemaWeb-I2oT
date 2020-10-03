package com.uesc.lif.i2ot.dao;

import org.junit.Test;

import com.uesc.lif.i2ot.model.Message;

public class MessageDAOTest {
	@Test
	public void saveMessage() {
		MessageDAO messageDAO = new MessageDAO();
		Message msg = new Message();
		msg.setMessage("Teste");
		//messageDAO.save(msg);
		
		msg = messageDAO.search(1l);
		System.out.println("Mensagem salva no banco: "+msg.getMessage());
	}
}
