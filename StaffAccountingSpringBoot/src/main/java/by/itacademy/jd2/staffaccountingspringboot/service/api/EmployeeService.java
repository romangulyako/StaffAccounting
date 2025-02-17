package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeItemDTO;
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
    List<EmployeeItemDTO> getAllEmployeeItems(boolean isCurrentOnly);
}
