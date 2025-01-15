package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDaoImpl extends DAO<EmployeeEntity> implements EmployeeDAO {
    private static final String IS_FIRED_PARAMETER = "isFired";
    private static final String GET_EMPLOYEES_QUERY =
            "SELECT e FROM EmployeeEntity e WHERE isFired =: isFired";
    private static final String GET_EMPLOYEES_COUNT_QUERY =
            "SELECT COUNT(e) FROM EmployeeEntity e WHERE isFired =: isFired";

    public EmployeeDaoImpl() {
        super(EmployeeEntity.class);
    }

    @Override
    public List<EmployeeEntity> getEmployeesByFiredAndPage(boolean isFired, Integer pageSize, Integer pageNumber) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<EmployeeEntity> query = em.createQuery(GET_EMPLOYEES_QUERY, EmployeeEntity.class);
                    query.setParameter(IS_FIRED_PARAMETER, isFired);
                    query.setFirstResult((pageNumber - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    return query.getResultList();
                });
    }

    @Override
    public Long getEmployeesCount(boolean isFired) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<Long> query = em.createQuery(GET_EMPLOYEES_COUNT_QUERY, Long.class);
                    query.setParameter(IS_FIRED_PARAMETER, isFired);
                    return query.getSingleResult();
                });
    }
}
