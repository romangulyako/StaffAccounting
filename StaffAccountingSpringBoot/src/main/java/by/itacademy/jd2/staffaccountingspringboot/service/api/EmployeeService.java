package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeesPageDTO;

import java.util.List;

public interface EmployeeService {
    void saveOrUpdateEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
    EmployeeDTO getEmployee(Long id);
    EmployeesPageDTO getEmployeesPage(EmployeeFilterData filterData,
                                      Boolean isFired,
                                      int page,
                                      int size);
    void returnToCurrent(Long id);
    List<EmployeeItemDTO> getCurrentEmployeeItems();
}
