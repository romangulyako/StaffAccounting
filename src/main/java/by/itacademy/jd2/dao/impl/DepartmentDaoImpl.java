package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.DepartmentDAO;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.utils.ExecutorUtil;

import javax.persistence.TypedQuery;
import java.util.List;

public class DepartmentDaoImpl extends DAO<DepartmentEntity> implements DepartmentDAO {
    public DepartmentDaoImpl() {
        super(DepartmentEntity.class);
    }

    @Override
    public List<DepartmentEntity> getDepartmentsByPage(Integer pageSize, Integer pageNumber) {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    em.clear();
                    TypedQuery<DepartmentEntity> query = em.createQuery("select d from DepartmentEntity d",
                            DepartmentEntity.class);
                    query.setFirstResult((pageNumber - 1) * pageSize);
                    query.setMaxResults(pageSize);
                    return query.getResultList();
                });
    }

    @Override
    public Long getDepartmentsCount() {
        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    TypedQuery<Long> query = em.createQuery("SELECT COUNT(*) FROM DepartmentEntity ", Long.class);
                    return query.getSingleResult();
                });
    }
}
