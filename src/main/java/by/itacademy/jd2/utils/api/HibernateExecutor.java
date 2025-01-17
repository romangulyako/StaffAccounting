package by.itacademy.jd2.utils.api;

import jakarta.persistence.EntityManager;

@FunctionalInterface
public interface HibernateExecutor<T> {
    T execute(EntityManager em);
}
