package by.itacademy.jd2.staffaccountingspringboot.repository.custom;

import by.itacademy.jd2.staffaccountingspringboot.entity.CareerStepEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.DepartmentEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.PositionEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.embedded.PersonData;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeFilterData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeFilterRepositoryImpl implements EmployeeFilterRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<EmployeeEntity> findEmployeesByFilterData(EmployeeFilterData filterData,
                                                          Boolean isFired,
                                                          Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> query = cb.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> root = query.from(EmployeeEntity.class);

        query.where(this.createPredicate(cb, root, filterData, isFired));

        List<EmployeeEntity> employees = em.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<EmployeeEntity> countRoot = countQuery.from(EmployeeEntity.class);

        countQuery.select(cb.count(countRoot)).where(createPredicate(cb, countRoot, filterData, isFired));

        Long count = em.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(employees, pageable, count);
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
