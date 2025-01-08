package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.CareerDAO;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.entity.embedded.CareerStepId;
import by.itacademy.jd2.exception.MoreOneResultException;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CareerDaoImpl extends DAO<CareerStepEntity> implements CareerDAO {

    private static final String MORE_ONE_POSITIONS_MESSAGE = "У сотрудника больше одной текущей должности";

    public CareerDaoImpl() {
        super(CareerStepEntity.class);
    }

    @Override
    public CareerStepEntity update(CareerStepEntity entity, Serializable id) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(), em -> {
            CareerStepId newId = CareerStepId.builder()
                    .employee(entity.getEmployee().getId())
                    .position(entity.getPosition().getId())
                    .dateOfAppointment(entity.getDateOfAppointment())
                    .build();

            if (!id.equals(newId)) {
                em.remove(em.find(CareerStepEntity.class, id));
            }

            return em.merge(entity);
        });
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
    public CareerStepEntity getCurrentCareerStepOfEmployee(Serializable employeeId) throws MoreOneResultException {
        List<CareerStepEntity> employeeCareer = this.getCareerByEmployeeId(employeeId);
        List<CareerStepEntity> currentCareerSteps = employeeCareer.stream()
                .filter(CareerStepEntity::isCurrent)
                .collect(Collectors.toList());
        if (currentCareerSteps.isEmpty()) {
            return null;
        } else if (currentCareerSteps.size() > 1) {
            throw new MoreOneResultException(MORE_ONE_POSITIONS_MESSAGE);
        }

        return currentCareerSteps.get(0);
    }
}
