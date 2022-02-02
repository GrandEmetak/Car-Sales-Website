package ru.job4j.cars.connect;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Singleton Data Base connection Hibernate
 */
public class DBSession {

    private static SessionFactory factory;

    private DBSession() {
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure().build();
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        return factory;
    }
}
