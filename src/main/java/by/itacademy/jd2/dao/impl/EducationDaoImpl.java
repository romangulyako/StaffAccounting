package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.EducationDAO;
import by.itacademy.jd2.entity.EducationEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class EducationDaoImpl extends DAO<EducationEntity> implements EducationDAO {
    public EducationDaoImpl() {
        super(EducationEntity.class);
    }

    @Override
    public List<EducationEntity> getEducationByEmployeeId(Serializable employeeId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    em.clear();
                    Query query = em.createQuery(
                            "SELECT e FROM EducationEntity e where e.employee.id =: employeeId"
                    );
                    query.setParameter("employeeId", employeeId);
                    return (List<EducationEntity>) query.getResultList();
                });
    }
}
