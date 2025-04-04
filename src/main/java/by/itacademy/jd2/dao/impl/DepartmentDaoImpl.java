package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.DepartmentDAO;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import jakarta.persistence.TypedQuery;
import java.util.List;

public class DepartmentDaoImpl extends DAO<DepartmentEntity> implements DepartmentDAO {
    private static final String IS_ACTUAL_PARAMETER = "isActual";
    private static final String GET_ALL_DEPARTMENTS_QUERY =
            "select distinct d from DepartmentEntity d " +
                    "LEFT JOIN d.positions p ON d.id = p.department.id " +
                    "where d.isActual =: isActual OR  p.isActual =: isActual";
    private static final String GET_DEPARTMENTS_COUNT_QUERY =
            "SELECT COUNT(DISTINCT d) FROM DepartmentEntity d " +
                    "LEFT JOIN d.positions p ON d.id = p.department.id " +
                    "where d.isActual =: isActual OR  p.isActual =: isActual";

    public DepartmentDaoImpl() {
        super(DepartmentEntity.class);
    }

    @Override
    public List<DepartmentEntity> getDepartmentsByActualAndPage(Boolean isActual,
                                                                Integer pageSize,
                                                                Integer pageNumber) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<DepartmentEntity> query = em.createQuery(GET_ALL_DEPARTMENTS_QUERY,
                            DepartmentEntity.class);
                    query.setParameter(IS_ACTUAL_PARAMETER, isActual);
                    query.setFirstResult((pageNumber - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    return query.getResultList();
                });
    }

    @Override
    public Long getDepartmentsCountByActual(Boolean isActual) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<Long> query = em.createQuery(GET_DEPARTMENTS_COUNT_QUERY, Long.class);
                    query.setParameter(IS_ACTUAL_PARAMETER, isActual);
                    return query.getSingleResult();
                });
    }
}
