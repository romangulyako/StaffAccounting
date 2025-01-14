package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.CareerDAO;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class CareerDaoImpl extends DAO<CareerStepEntity> implements CareerDAO {
    private static final String POSITION_ID_PARAMETER = "positionId";
    private static final String EMPLOYEE_ID_PARAMETER = "employeeId";
    private static final String GET_CAREER_STEP_COUNT_QUERY = "SELECT COUNT(c) FROM CareerStepEntity c " +
            "WHERE c.employee.id = :employeeId";
    private static final String GET_POSITION_HISTORY_COUNT_QUERY = "SELECT COUNT(c) FROM CareerStepEntity c " +
            "WHERE c.position.id = :positionId";
    private static final String GET_EMPLOYEE_CAREER_QUERY = "SELECT c FROM CareerStepEntity c " +
            "WHERE c.employee.id =:employeeId ORDER BY c.dateOfAppointment";
    private static final String GET_POSITION_HISTORY_QUERY = "SELECT c FROM CareerStepEntity c " +
            "WHERE c.position.id =:positionId ORDER BY c.dateOfAppointment";

    public CareerDaoImpl() {
        super(CareerStepEntity.class);
    }

    @Override
    public List<CareerStepEntity> getCareerByEmployeeIdAndPage(Serializable employeeId,
                                                               Integer pageSize,
                                                               Integer pageNumber) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    em.clear();
                    TypedQuery<CareerStepEntity> query = em.createQuery(
                            GET_EMPLOYEE_CAREER_QUERY, CareerStepEntity.class);
                    query.setParameter(EMPLOYEE_ID_PARAMETER, employeeId);
                    query.setFirstResult((pageNumber - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    return query.getResultList();
                });
    }

    @Override
    public List<CareerStepEntity> getPositionHistoryByPage(Serializable positionId,
                                                           Integer pageSize,
                                                           Integer pageNumber) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    em.clear();
                    TypedQuery<CareerStepEntity> query = em.createQuery(
                            GET_POSITION_HISTORY_QUERY, CareerStepEntity.class);
                    query.setParameter(POSITION_ID_PARAMETER, positionId);
                    query.setFirstResult((pageNumber - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    return query.getResultList();
                });
    }

    @Override
    public Long getCareerStepCountByEmployeeId(Serializable employeeId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<Long> query = em.createQuery(GET_CAREER_STEP_COUNT_QUERY, Long.class);
                    query.setParameter(EMPLOYEE_ID_PARAMETER, employeeId);
                    return query.getSingleResult();
                });
    }

    @Override
    public Long getCareerStepCountByPositionId(Serializable positionId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<Long> query = em.createQuery(GET_POSITION_HISTORY_COUNT_QUERY, Long.class);
                    query.setParameter(POSITION_ID_PARAMETER, positionId);
                    return query.getSingleResult();
                });
    }
}
