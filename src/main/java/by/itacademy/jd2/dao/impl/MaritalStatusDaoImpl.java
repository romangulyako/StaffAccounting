package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.MaritalStatusDAO;
import by.itacademy.jd2.repository.MaritalStatusEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.Query;
import java.util.List;

public class MaritalStatusDaoImpl extends DAO<MaritalStatusEntity> implements MaritalStatusDAO {
    public MaritalStatusDaoImpl() {
        super(MaritalStatusEntity.class);
    }

    @Override
    public List<MaritalStatusEntity> getMaritalStatusesByEmployeeId(Long employeeId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    em.clear();
                    Query query = em.createQuery(
                            "SELECT m FROM MaritalStatusEntity m where m.employee.id =: employeeId"
                    );
                    query.setParameter("employeeId", employeeId);
                    return (List<MaritalStatusEntity>) query.getResultList();
                });
    }
}
