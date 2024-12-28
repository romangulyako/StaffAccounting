package by.itacademy.jd2.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("staff_accounting");
    public static final EntityManager ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER;
    }

    public static void close() {
        if (ENTITY_MANAGER_FACTORY.isOpen()) {
            ENTITY_MANAGER_FACTORY.close();
        }
    }
}
