package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.PositionDAO;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class PositionDaoImpl extends DAO<PositionEntity> implements PositionDAO {
    private static final String DEPARTMENT_ID_PARAMETER = "departmentId";
    private static final String IS_ACTUAL_PARAMETER = "isActual";
    private static final String GET_POSITIONS_BY_DEPARTMENT =
            "SELECT p FROM PositionEntity p where p.department.id =: departmentId AND p.isActual =: isActual";
    private static final String GET_POSITIONS_COUNT_QUERY =
            "SELECT COUNT(p) FROM PositionEntity p WHERE p.department.id =: departmentId AND p.isActual =: isActual";

    public PositionDaoImpl() {
        super(PositionEntity.class);
    }

    @Override
    public List<PositionEntity> getPositionsByDepartmentIdAndActualAndPage(Serializable departmentId,
                                                                           Boolean isActual,
                                                                           Integer pageSize,
                                                                           Integer pageNumber) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<PositionEntity> query = em.createQuery(GET_POSITIONS_BY_DEPARTMENT,
                            PositionEntity.class);
                    query.setParameter(DEPARTMENT_ID_PARAMETER, departmentId);
                    query.setParameter(IS_ACTUAL_PARAMETER, isActual);
                    query.setFirstResult((pageNumber - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    return query.getResultList();
                });
    }

    @Override
    public Long getPositionsCountByDepartmentIdAndActual(Serializable departmentId, Boolean isActual) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<Long> query = em.createQuery(GET_POSITIONS_COUNT_QUERY, Long.class);
                    query.setParameter(DEPARTMENT_ID_PARAMETER, departmentId);
                    query.setParameter(IS_ACTUAL_PARAMETER, isActual);
                    return query.getSingleResult();
                });
    }
}
