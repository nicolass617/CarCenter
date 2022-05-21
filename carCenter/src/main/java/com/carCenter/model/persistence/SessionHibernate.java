package com.carCenter.model.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionHibernate {

	private static final SessionFactory sf = buildSessionFactory();

	/**
	 * Metodo que configura la sesion
	 * 
	 * @return la configuracion de la sesion
	 */
	private static SessionFactory buildSessionFactory() {
		return new Configuration().configure().buildSessionFactory();
	}

	/**
	 * Metodo get del SessionFactory
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSf() {
		return sf;
	}

}
