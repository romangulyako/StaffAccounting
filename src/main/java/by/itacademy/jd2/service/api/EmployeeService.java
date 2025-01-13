package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.EmployeeItemDTO;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.paginator.Paginator;

import java.io.Serializable;
import java.util.List;

public interface EmployeeService extends Service {
    void addEmployee(EmployeeDTO employeeDTO);
    void updateEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Serializable id);
    EmployeeDTO getEmployee(Serializable id);
    Paginator<EmployeeDTO> getEmployeesByFiredAndPage(Boolean isFired, Integer pageSize, Integer pageNumber);
    List<EmployeeItemDTO> getAllEmployeeItems(boolean isCurrentOnly);
    void addPassport(PassportDTO passportDTO, EmployeeDTO employeeDTO);
    void updatePassport(PassportDTO passportDTO, EmployeeDTO employeeDTO);
    void deletePassport(Serializable id);
    PassportDTO getPassport(Serializable id);
}
