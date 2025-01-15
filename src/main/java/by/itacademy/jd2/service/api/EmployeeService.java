package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.EmployeeItemDTO;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.service.PageInfo;

import java.io.Serializable;
import java.util.List;

public interface EmployeeService extends Service {
    void addEmployee(EmployeeDTO employeeDTO);
    void updateEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Serializable id);
    EmployeeDTO getEmployee(Serializable id);
    PageInfo<EmployeeDTO> getEmployeesByFiredAndPage(Boolean isFired, Integer pageSize, Integer pageNumber);
    List<EmployeeItemDTO> getAllEmployeeItems(boolean isCurrentOnly);
    void addPassport(PassportDTO passportDTO);
    void updatePassport(PassportDTO passportDTO);
    void deletePassport(Serializable id);
    PassportDTO getPassport(Serializable id);
}
