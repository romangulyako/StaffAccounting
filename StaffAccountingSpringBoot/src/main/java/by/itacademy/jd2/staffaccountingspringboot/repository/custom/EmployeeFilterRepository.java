package by.itacademy.jd2.staffaccountingspringboot.repository.custom;

import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeFilterData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeFilterRepository {
    Page<EmployeeEntity> findEmployeesByFilterData(EmployeeFilterData filterData, Boolean isFired, Pageable pageable);
}
