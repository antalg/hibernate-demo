package hu.aensys.tutorial.java.hibernate.common;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private static final String PERSISTENCE_UNIT_NAME = "HibernateDemo";

    public static EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

}
