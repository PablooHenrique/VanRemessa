package br.com.netsuprema.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Application {
	private static Application applicationInstance;
	private SessionFactory sessionFactory; 
	
	public Application() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static synchronized Application getInstance() {
        if (applicationInstance == null) {
            applicationInstance = new Application();
        }

        return applicationInstance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
