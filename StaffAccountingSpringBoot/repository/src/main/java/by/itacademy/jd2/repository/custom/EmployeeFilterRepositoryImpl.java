package by.itacademy.jd2.repository.custom;

import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.entity.CareerStepEntity_;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.entity.DepartmentEntity_;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.EmployeeEntity_;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.entity.PositionEntity_;
import by.itacademy.jd2.entity.embedded.PersonData;
import by.itacademy.jd2.entity.embedded.PersonData_;
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

    private static final String PERCENT = "%";
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
        predicates.add(cb.equal(root.get(EmployeeEntity_.isFired), isFired));

        Join<EmployeeEntity, PersonData> personDataJoin =
                root.join(EmployeeEntity_.personData, JoinType.INNER);

        addPredicateForStringValue(predicates, cb, personDataJoin.get(PersonData_.surname), filterData.getSurname());
        addPredicateForStringValue(predicates, cb, personDataJoin.get(PersonData_.name), filterData.getName());
        addPredicateForStringValue(predicates, cb, personDataJoin.get(PersonData_.patronymic),
                filterData.getPatronymic());

        if (filterData.getAge() != null) {
            LocalDate today = LocalDate.now();
            Date minBirthDay = Date.valueOf(today.minusYears(filterData.getAge()));
            predicates.add(cb.lessThanOrEqualTo(personDataJoin.get(PersonData_.birthday), minBirthDay));
        }

        if (filterData.getDepartmentId() != null) {
            Join<EmployeeEntity, CareerStepEntity> careerStepEntityJoin =
                    root.join(EmployeeEntity_.career, JoinType.INNER);

            Join<CareerStepEntity, PositionEntity> positionEntityJoin =
                    careerStepEntityJoin.join(CareerStepEntity_.position, JoinType.INNER);

            Join<PositionEntity, DepartmentEntity> departmentEntityJoin =
                    positionEntityJoin.join(PositionEntity_.department, JoinType.INNER);
            predicates.add(cb.and(cb.equal(departmentEntityJoin.get(DepartmentEntity_.id),
                            filterData.getDepartmentId()),
                    cb.isTrue(careerStepEntityJoin.get(CareerStepEntity_.isCurrent))));
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }

    private void addPredicateForStringValue(List<Predicate> predicates, CriteriaBuilder cb,
                                            Expression<String> expression, String value) {
        if (StringUtils.isNotBlank(value)) {
            predicates.add(cb.like(cb.lower(expression), PERCENT + value.toLowerCase() + PERCENT));
        }
    }
}
