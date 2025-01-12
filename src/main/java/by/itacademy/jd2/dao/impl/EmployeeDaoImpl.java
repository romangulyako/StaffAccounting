package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.Query;
import java.util.List;

public class EmployeeDaoImpl extends DAO<EmployeeEntity> implements EmployeeDAO {
    private static final String GET_EMPLOYEES_QUERY = "SELECT e " +
            "FROM EmployeeEntity e WHERE isFired =: isFired";
    private static final String IS_FIRED_PARAMETER = "isFired";

    public EmployeeDaoImpl() {
        super(EmployeeEntity.class);
    }

    @Override
    public List<EmployeeEntity> getAllCurrentEmployees() {
        return getEmployeesByFired(false);
    }

    @Override
    public List<EmployeeEntity> getAllFiredEmployees() {
        return getEmployeesByFired(true);
    }

    private List<EmployeeEntity> getEmployeesByFired(boolean isFired) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    em.clear();
                    Query query = em.createQuery(GET_EMPLOYEES_QUERY);
                    query.setParameter(IS_FIRED_PARAMETER, isFired);
                    return (List<EmployeeEntity>) query.getResultList();
                });
    }
}
