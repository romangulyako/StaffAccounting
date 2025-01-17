package by.itacademy.jd2.utils;

import by.itacademy.jd2.utils.api.HibernateExecutor;

import jakarta.persistence.EntityManager;

public class ExecutorUtil {
    public static <T> T executeHibernate(EntityManager em,
                                         HibernateExecutor<T> hibernateExecutor) {
        try {
            em.getTransaction().begin();
            T t = hibernateExecutor.execute(em);
            em.getTransaction().commit();

            return t;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }
}
