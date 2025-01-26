package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.dto.EmployeeFilterData;
import by.itacademy.jd2.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDAO extends IDAO<EmployeeEntity> {
    Long getEmployeesCount(EmployeeFilterData filterData, Boolean isFired);
    List<EmployeeEntity> getEmployeesByFiredAndPage(EmployeeFilterData filterData,
                                                    Boolean isFired,
                                                    Integer pageSize,
                                                    Integer pageNumber);
}
