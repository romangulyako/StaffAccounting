package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.CareerDAO;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.exception.MoreOneResultException;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CareerDaoImpl extends DAO<CareerStepEntity> implements CareerDAO {
    public CareerDaoImpl() {
        super(CareerStepEntity.class);
    }

    @Override
    public List<CareerStepEntity> getCareerByEmployeeId(Serializable employeeId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    em.clear();
                    Query query = em.createQuery(
                            "SELECT c FROM CareerStepEntity c " +
                                    "WHERE c.employee.id =:employeeId ORDER BY c.dateOfAppointment"
                    );
                    query.setParameter("employeeId", employeeId);
                    return (List<CareerStepEntity>) query.getResultList();
                });
    }

    @Override
    public List<CareerStepEntity> getPositionHistory(Serializable positionId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    em.clear();
                    Query query = em.createQuery(
                            "SELECT c FROM CareerStepEntity c " +
                                    "WHERE c.position.id =:positionId ORDER BY c.dateOfAppointment"
                    );
                    query.setParameter("positionId", positionId);
                    return (List<CareerStepEntity>) query.getResultList();
                });
    }

    @Override
    public List<CareerStepEntity> getCurrentCareerStepOfEmployee(Serializable employeeId) {
        return this.getCareerByEmployeeId(employeeId).stream()
                .filter(CareerStepEntity::isCurrent)
                .collect(Collectors.toList());
    }
}
