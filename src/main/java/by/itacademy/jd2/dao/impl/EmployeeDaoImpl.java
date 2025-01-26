package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dto.EmployeeFilterData;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.entity.embedded.PersonData;
import by.itacademy.jd2.utils.ExecutorUtil;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDaoImpl extends DAO<EmployeeEntity> implements EmployeeDAO {
    public EmployeeDaoImpl() {
        super(EmployeeEntity.class);
    }

    @Override
    public List<EmployeeEntity> getEmployeesByFiredAndPage(EmployeeFilterData filterData,
                                                           Boolean isFired,
                                                           Integer pageSize,
                                                           Integer pageNumber) {

        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    CriteriaBuilder cb = em.getCriteriaBuilder();
                    CriteriaQuery<EmployeeEntity> query = cb.createQuery(EmployeeEntity.class);
                    Root<EmployeeEntity> root = query.from(EmployeeEntity.class);

                    query.where(this.createPredicate(cb, root, filterData, isFired));
                    TypedQuery<EmployeeEntity> typedQuery = em.createQuery(query);
                    typedQuery.setFirstResult((pageNumber - 1) * pageSize);
                    typedQuery.setMaxResults(pageSize);
                    return typedQuery.getResultList();
                });
    }

    @Override
    public Long getEmployeesCount(EmployeeFilterData filterData, Boolean isFired) {

        return ExecutorUtil.executeHibernate(super.getEntityManager(),
                em -> {
                    CriteriaBuilder cb = em.getCriteriaBuilder();
                    CriteriaQuery<Long> query = cb.createQuery(Long.class);
                    Root<EmployeeEntity> root = query.from(EmployeeEntity.class);

                    query.select(cb.count(root))
                            .where(createPredicate(cb, root, filterData, isFired));
                    TypedQuery<Long> typedQuery = em.createQuery(query);
                    return typedQuery.getSingleResult();
                });
    }

    private Predicate createPredicate(CriteriaBuilder cb,
                                      Root<EmployeeEntity> root,
                                      EmployeeFilterData filterData,
                                      Boolean isFired) {
        Predicate predicate = cb.conjunction();
        predicate = cb.and(predicate, cb.equal(root.get("isFired"), isFired));

        if (filterData.getSurname() != null && !filterData.getSurname().isEmpty()) {
            Join<EmployeeEntity, PersonData> personDataJoin =
                    root.join("personData", JoinType.INNER);
            predicate = cb.and(predicate, cb.like(cb.lower(personDataJoin.get("surname")),
                    "%" + filterData.getSurname().toLowerCase() + "%"));
        }

        if (filterData.getName() != null && !filterData.getName().isEmpty()) {
            Join<EmployeeEntity, PersonData> personDataJoin = root.join("personData", JoinType.INNER);
            predicate = cb.and(predicate, cb.like(cb.lower(personDataJoin.get("name")),
                    "%" + filterData.getName().toLowerCase() + "%"));
        }

        if (filterData.getPatronymic() != null && !filterData.getPatronymic().isEmpty()) {
            Join<EmployeeEntity, PersonData> personDataJoin =
                    root.join("personData", JoinType.INNER);
            predicate = cb.and(predicate, cb.like(cb.lower(personDataJoin.get("patronymic")),
                    "%" + filterData.getPatronymic().toLowerCase() + "%"));
        }

        if (filterData.getAge() != null) {
            Join<EmployeeEntity, PersonData> personDataJoin =
                    root.join("personData", JoinType.INNER);

            LocalDate today = LocalDate.now();
            Date minBirthDay = Date.valueOf(today.minusYears(filterData.getAge()));

            predicate = cb.and(predicate, cb.lessThanOrEqualTo(personDataJoin.get("birthday"), minBirthDay));
        }

        if (filterData.getDepartmentId() != null) {
            Join<EmployeeEntity, CareerStepEntity> careerStepEntityJoin =
                    root.join("career", JoinType.INNER);

            Join<CareerStepEntity, PositionEntity> positionEntityJoin =
                    careerStepEntityJoin.join("position", JoinType.INNER);

            Join<PositionEntity, DepartmentEntity> departmentEntityJoin =
                    positionEntityJoin.join("department", JoinType.INNER);

            predicate = cb.and(predicate, cb.equal(departmentEntityJoin.get("id"),
                            filterData.getDepartmentId()),
                    cb.isTrue(careerStepEntityJoin.get("isCurrent")));
        }

        return predicate;
    }
}
