package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    void saveOrUpdateEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
    EmployeeDTO getEmployee(Long id);
    Page<EmployeeDTO> getEmployees(EmployeeFilterData filterData,
                                   Boolean isFired,
                                   Pageable pageable);
    void returnToCurrent(Long id);
    List<EmployeeItemDTO> getCurrentEmployeeItems();
}
