package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.RelativeDAO;
import by.itacademy.jd2.entity.RelativeEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.Query;
import java.util.List;

public class RelativeDaoImpl extends DAO<RelativeEntity> implements RelativeDAO {
    public RelativeDaoImpl() {
        super(RelativeEntity.class);
    }

    @Override
    public List<RelativeEntity> getRelativesByEmployeeId(Long employeeId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    em.clear();
                    Query query = em.createQuery(
                            "SELECT r FROM RelativeEntity r where r.employee.id =: employeeId"
                    );
                    query.setParameter("employeeId", employeeId);
                    return (List<RelativeEntity>) query.getResultList();
                });
    }
}
