package ru.job4j.cars.connect;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Singleton Data Base connection Hibernate (inner holder)
 * Ленивая инициализация.
 * Потокобезопасность.
 * Высокая производительность в многопоточной среде.
 */
public class DBSession {

    private static SessionFactory factory;

    private DBSession() {
    }

    private static class DBSessionHolder {
        public static final DBSession HOLDER_INSTANCE = new DBSession();
    }

    public static DBSession getInstance() {
        return DBSessionHolder.HOLDER_INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        if (factory == null) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure().build();
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        return factory;
    }
}





