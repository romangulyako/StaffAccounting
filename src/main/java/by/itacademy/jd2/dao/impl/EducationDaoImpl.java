package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.EducationDAO;
import by.itacademy.jd2.entity.EducationEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class EducationDaoImpl extends DAO<EducationEntity> implements EducationDAO {
    private static final String EMPLOYEE_ID_PARAMETER = "employeeId";
    private static final String GET_EDUCATION_BY_EMPLOYEE = "SELECT e FROM EducationEntity e where e.employee.id =: employeeId";
    private static final String GET_EDUCATION_COUNT_QUERY = "SELECT COUNT(e) FROM EducationEntity e where e.employee.id =: employeeId";

    public EducationDaoImpl() {
        super(EducationEntity.class);
    }

    @Override
    public List<EducationEntity> getEducationByEmployeeId(Serializable employeeId,
                                                          Integer pageSize,
                                                          Integer pageNumber) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<EducationEntity> query = em.createQuery(
                            GET_EDUCATION_BY_EMPLOYEE, EducationEntity.class);
                    query.setParameter(EMPLOYEE_ID_PARAMETER, employeeId);
                    query.setFirstResult((pageNumber - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    return query.getResultList();
                });
    }

    @Override
    public Long getEducationCountByEmployeeId(Serializable employeeId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<Long> query = em.createQuery(GET_EDUCATION_COUNT_QUERY, Long.class);
                    query.setParameter(EMPLOYEE_ID_PARAMETER, employeeId);
                    return query.getSingleResult();
                });
    }
}
