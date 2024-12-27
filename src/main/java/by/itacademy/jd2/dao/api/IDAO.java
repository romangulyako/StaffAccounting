package by.itacademy.jd2.dao.api;

import java.io.Serializable;
import java.util.List;

public interface IDAO<T> {
    T save(T entity);
    T update(T entity, Serializable id);
    T get(Serializable id);
    List<T> getAll();
    boolean delete(Serializable id);
}
