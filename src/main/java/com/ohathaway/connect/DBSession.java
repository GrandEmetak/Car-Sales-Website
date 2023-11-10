package com.ohathaway.connect;

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

    private static class DBSessionHolder {

        public static final DBSessionHolder HOLDER_INSTANCE = new DBSessionHolder();

        private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        private final SessionFactory factory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();

    }

    public static SessionFactory getInstance() {
        return DBSessionHolder.HOLDER_INSTANCE.factory;
    }
}






