package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.PositionDAO;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class PositionDaoImpl extends DAO<PositionEntity> implements PositionDAO {
    public PositionDaoImpl() {
        super(PositionEntity.class);
    }

    @Override
    public List<PositionEntity> getPositionsByDepartmentId(Serializable departmentId) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    em.clear();
                    Query query = em.createQuery(
                            "SELECT p FROM PositionEntity p where p.department.id =: departmentId");
                    query.setParameter("departmentId", departmentId);
                    return (List<PositionEntity>) query.getResultList();
                });
    }
}
