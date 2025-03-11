package by.itacademy.jd2.staffaccountingspringboot.rest;

import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeesPageDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PageFilter;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees-rest")
@RequiredArgsConstructor
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<EmployeesPageDTO> getEmployees(@ModelAttribute("pageFilter") PageFilter pageFilter,
                                                         @RequestParam(defaultValue = "false") Boolean isFired,
                                                         EmployeeFilterData filterData) {
        EmployeesPageDTO employeesPage = employeeService.getEmployeesPage(filterData, isFired,
                pageFilter.getPage(), pageFilter.getSize());

        return ResponseEntity.ok(employeesPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }
}
