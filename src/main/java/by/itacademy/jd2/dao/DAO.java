package by.itacademy.jd2.dao;

import by.itacademy.jd2.dao.api.IDAO;
import by.itacademy.jd2.utils.ExecutorUtil;
import by.itacademy.jd2.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class DAO<T> implements IDAO<T> {
    private final EntityManager em;
    private final Class<T> clazz;

    public DAO(Class<T> clazz) {
        this.em = HibernateUtil.getEntityManager();
        this.clazz = clazz;
    }

    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public T save(T entity) {
        return ExecutorUtil.executeHibernate(this.em, em -> {
            em.persist(entity);
            return entity;
        });
    }

    @Override
    public T update(T entity, Serializable id) {
        return ExecutorUtil.executeHibernate(this.em, em -> {
            T updatedEntity;
            if ((updatedEntity = em.find(this.clazz, id)) != null) {
                updatedEntity = em.merge(entity);
            }

            return updatedEntity;
        });
    }

    @Override
    public T get(Serializable id) {
        return ExecutorUtil.executeHibernate(this.em, em ->
                em.find(this.clazz, id));
    }

    @Override
    public List<T> getAll() {
        String query = "FROM " + this.clazz.getSimpleName();
        return ExecutorUtil.executeHibernate(this.em,
                em -> em.createQuery(query, this.clazz).getResultList());
    }

    @Override
    public boolean delete(Serializable id) {
        return Boolean.TRUE.equals(ExecutorUtil.executeHibernate(this.em, em -> {
            T t = em.find(this.clazz, id);
            if (t != null) {
                em.remove(t);
                return true;
            } else {
                return false;
            }
        }));
    }

    @Override
    public void close() {
        if (this.em.isOpen()) {
            this.em.close();
        }
    }
}
