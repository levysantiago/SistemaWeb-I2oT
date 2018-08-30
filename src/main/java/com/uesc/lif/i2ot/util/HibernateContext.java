package com.uesc.lif.i2ot.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * Esta classe serve para resolver o 
 * problema de tempo gasto para inicializar 
 * o hibernate ao carregar a aplicação pela 
 * primeira vez
 * 
 * */
public class HibernateContext implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory().close();
	}

}
