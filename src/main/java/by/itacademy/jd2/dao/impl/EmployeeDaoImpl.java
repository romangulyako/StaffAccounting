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
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("isFired"), isFired));

        Join<EmployeeEntity, PersonData> personDataJoin =
                root.join("personData", JoinType.INNER);

        addPredicateForStringValue(predicates, cb, personDataJoin.get("surname"), filterData.getSurname());
        addPredicateForStringValue(predicates, cb, personDataJoin.get("name"), filterData.getName());
        addPredicateForStringValue(predicates, cb, personDataJoin.get("patronymic"), filterData.getPatronymic());

        if (filterData.getAge() != null) {
            LocalDate today = LocalDate.now();
            Date minBirthDay = Date.valueOf(today.minusYears(filterData.getAge()));
            predicates.add(cb.lessThanOrEqualTo(personDataJoin.get("birthday"), minBirthDay));
        }

        if (filterData.getDepartmentId() != null) {
            Join<EmployeeEntity, CareerStepEntity> careerStepEntityJoin =
                    root.join("career", JoinType.INNER);

            Join<CareerStepEntity, PositionEntity> positionEntityJoin =
                    careerStepEntityJoin.join("position", JoinType.INNER);

            Join<PositionEntity, DepartmentEntity> departmentEntityJoin =
                    positionEntityJoin.join("department", JoinType.INNER);
            predicates.add(cb.and(cb.equal(departmentEntityJoin.get("id"),
                            filterData.getDepartmentId()),
                    cb.isTrue(careerStepEntityJoin.get("isCurrent"))));
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }

    private void addPredicateForStringValue(List<Predicate> predicates, CriteriaBuilder cb,
                                    Expression<String> expression, String value) {
        if (StringUtils.isNotBlank(value)) {
            predicates.add(cb.like(cb.lower(expression), "%" + value.toLowerCase() + "%"));
        }
    }
}
