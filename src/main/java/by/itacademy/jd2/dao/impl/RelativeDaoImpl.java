package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.RelativeDAO;
import by.itacademy.jd2.entity.RelativeEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class RelativeDaoImpl extends DAO<RelativeEntity> implements RelativeDAO {
    private static final String EMPLOYEE_ID_PARAMETER = "employeeId";
    private static final String GET_RELATIVE_BY_EMPLOYEE_QUERY =
            "SELECT r FROM RelativeEntity r where r.employee.id =: employeeId";
    private static final String GET_RELATIVES_COUNT_QUERY =
            "SELECT COUNT(r) FROM RelativeEntity r WHERE r.employee.id =: employeeId";

    public RelativeDaoImpl() {
        super(RelativeEntity.class);
    }

    @Override
    public List<RelativeEntity> getRelativesByEmployeeIdAndPage(Serializable employeeId,
                                                                Integer pageSize,
                                                                Integer pageNumber) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<RelativeEntity> query = em.createQuery(
                            GET_RELATIVE_BY_EMPLOYEE_QUERY, RelativeEntity.class);
                    query.setParameter(EMPLOYEE_ID_PARAMETER, employeeId);
                    query.setFirstResult((pageNumber - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    return query.getResultList();
                });
    }

    @Override
    public Long getRelativesCountByEmployeeId(Serializable employeeId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<Long> query = em.createQuery(GET_RELATIVES_COUNT_QUERY, Long.class);
                    query.setParameter(EMPLOYEE_ID_PARAMETER, employeeId);
                    return query.getSingleResult();
                });
    }
}
