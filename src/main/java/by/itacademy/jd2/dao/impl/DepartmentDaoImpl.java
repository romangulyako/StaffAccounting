package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.DepartmentDAO;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.TypedQuery;
import java.util.List;

public class DepartmentDaoImpl extends DAO<DepartmentEntity> implements DepartmentDAO {
    private static final String GET_ALL_DEPARTMENTS_QUERY = "select d from DepartmentEntity d";
    private static final String GET_DEPARTMENTS_COUNT_QUERY = "SELECT COUNT(*) FROM DepartmentEntity";

    public DepartmentDaoImpl() {
        super(DepartmentEntity.class);
    }

    @Override
    public List<DepartmentEntity> getDepartmentsByPage(Integer pageSize, Integer pageNumber) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<DepartmentEntity> query = em.createQuery(GET_ALL_DEPARTMENTS_QUERY,
                            DepartmentEntity.class);
                    query.setFirstResult((pageNumber - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    return query.getResultList();
                });
    }

    @Override
    public Long getDepartmentsCount() {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<Long> query = em.createQuery(GET_DEPARTMENTS_COUNT_QUERY, Long.class);
                    return query.getSingleResult();
                });
    }
}
