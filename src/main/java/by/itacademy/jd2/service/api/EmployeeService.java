package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.PassportDTO;

import java.io.Serializable;
import java.util.List;

public interface EmployeeService extends Service {
    void addEmployee(EmployeeDTO employeeDTO);
    void updateEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Serializable id);
    EmployeeDTO getEmployee(Serializable id);
    List<EmployeeDTO> getAllEmployees();
    void addPassport(PassportDTO passportDTO, EmployeeDTO employeeDTO);
    void updatePassport(PassportDTO passportDTO, EmployeeDTO employeeDTO);
    void deletePassport(Serializable id);
    PassportDTO getPassport(Serializable id);
}
