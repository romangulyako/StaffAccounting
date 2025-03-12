package by.itacademy.jd2.repository.custom;

import by.itacademy.jd2.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeFilterRepository {
    Page<EmployeeEntity> findEmployeesByFilterData(EmployeeFilterData filterData, Boolean isFired, Pageable pageable);
}
