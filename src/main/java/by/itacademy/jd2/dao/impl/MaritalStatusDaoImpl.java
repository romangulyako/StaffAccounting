package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.MaritalStatusDAO;
import by.itacademy.jd2.entity.MaritalStatusEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class MaritalStatusDaoImpl extends DAO<MaritalStatusEntity> implements MaritalStatusDAO {

    private static final String EMPLOYEE_ID_PARAMETER = "employeeId";
    private static final String GET_MARITAL_STATUSES_BY_EMPLOYEE_QUERY =
            "SELECT m FROM MaritalStatusEntity m where m.employee.id =: employeeId";
    private static final String GET_MARITAL_STATUSES_COUNT_QUERY =
            "SELECT COUNT(m) FROM MaritalStatusEntity m WHERE m.employee.id =: employeeId";

    public MaritalStatusDaoImpl() {
        super(MaritalStatusEntity.class);
    }

    @Override
    public List<MaritalStatusEntity> getMaritalStatusesByEmployeeIdAndPage(Serializable employeeId,
                                                                           Integer pageSize,
                                                                           Integer pageNumber) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<MaritalStatusEntity> query = em.createQuery(
                            GET_MARITAL_STATUSES_BY_EMPLOYEE_QUERY, MaritalStatusEntity.class);
                    query.setParameter(EMPLOYEE_ID_PARAMETER, employeeId);
                    query.setFirstResult((pageNumber - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    return query.getResultList();
                });
    }

    @Override
    public Long getMaritalStatusesCountByEmployeeId(Serializable employeeId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<Long> query = em.createQuery(GET_MARITAL_STATUSES_COUNT_QUERY, Long.class);
                    query.setParameter(EMPLOYEE_ID_PARAMETER, employeeId);
                    return query.getSingleResult();
                });
    }
}
