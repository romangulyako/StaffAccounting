package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDAO extends IDAO<EmployeeEntity> {
    Long getEmployeesCount(boolean isFired);
    List<EmployeeEntity> getEmployeesByFiredAndPage(boolean isFired, Integer pageSize, Integer pageNumber);
}
