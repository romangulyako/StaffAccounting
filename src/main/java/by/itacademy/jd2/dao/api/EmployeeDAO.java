package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDAO extends IDAO<EmployeeEntity> {
    List<EmployeeEntity> getAllCurrentEmployees(Integer pageSize, Integer pageNumber);
    List<EmployeeEntity> getAllFiredEmployees(Integer pageSize, Integer pageNumber);
    Long getEmployeesCount(boolean isFired);
}
