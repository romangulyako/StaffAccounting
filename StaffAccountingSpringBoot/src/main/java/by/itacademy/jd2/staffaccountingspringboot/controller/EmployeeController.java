package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeesPageDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    @GetMapping({"/", "/employees"})
    public String getEmployees(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "2") int size,
                               @RequestParam(defaultValue = "false") Boolean isFired,
                               EmployeeFilterData filterData,
                               Model model) {
        LOGGER.info("Received request to get employees (isFired = {})", isFired);
        EmployeesPageDTO employeesPage = employeeService.getEmployeesPage(filterData, isFired, page, size);
        model.addAttribute("employees", employeesPage.getEmployees().getContent());
        model.addAttribute("isFired", isFired);
        model.addAttribute("filterData", filterData);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", employeesPage.getEmployees().getTotalPages());
        model.addAttribute("departments", employeesPage.getDepartments());
        model.addAttribute("size", size);

        return "employees/list";
    }

    @GetMapping("/employees/add")
    public String addEmployeePage(Model model) {
        model.addAttribute("newEmployee", new EmployeeDTO());

        return "employees/add";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute("newEmployee") EmployeeDTO employee) {
        employeeService.saveOrUpdateEmployee(employee);

        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}/edit")
    public String editEmployeePage(@PathVariable Long id, Model model) {
        LOGGER.info("Received request to get for edit employee with id {}", id);
        model.addAttribute("employee", employeeService.getEmployee(id));

        return "employees/edit";
    }

    @PostMapping("/employees/edit")
    public String editEmployee(EmployeeDTO employee) {
        LOGGER.info("Received request to edit employee with id={}", employee.getId());
        employeeService.saveOrUpdateEmployee(employee);

        return "redirect:/employees/" + employee.getId();
    }

    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {
        LOGGER.info("Received request to get employee with id={}", id);
        model.addAttribute("employee", employeeService.getEmployee(id));

        return "employees/info";
    }

    @PostMapping("/employees/{id}/delete")
    public String deleteEmployee(@PathVariable Long id) {
        LOGGER.info("Received request to delete employee with id={}", id);
        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }

    @PostMapping("/employees/clear-filter")
    public String clearFilter(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "2") int size,
                              Model model) {
        model.addAttribute("filterData", new EmployeeFilterData());

        return "redirect:/employees?page=" + page + "&size=" + size;
    }

    @PostMapping("/employees/{id}/return")
    public String returnToCurrent(@PathVariable Long id) {
        LOGGER.info("Received request to return employee with id={}", id);
        employeeService.returnToCurrent(id);

        return "redirect:/employees";
    }
}
