package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        try {
            Page<EmployeeDTO> employeesPage = employeeService.getEmployees(filterData, isFired, PageRequest.of(page, size));
            model.addAttribute("employees", employeesPage.getContent());
            model.addAttribute("isFired", isFired);
            model.addAttribute("filterData", filterData);
            model.addAttribute("page", page);
            model.addAttribute("totalPages", employeesPage.getTotalPages());
            model.addAttribute("size", size);

            return "employees/list";
        } catch (Exception e) {
            LOGGER.error("Error getting employees", e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employees/add")
    public String addEmployeePage(Model model) {
        model.addAttribute("newEmployee", new EmployeeDTO());
        return "employees/add";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute("newEmployee") EmployeeDTO employee, Model model) {
        LOGGER.info("Received request to add new employee");
        try {
            employeeService.saveOrUpdateEmployee(employee);
            return "redirect:/employees";
        } catch (Exception e) {
            LOGGER.error("Error adding new employee", e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employees/{id}/edit")
    public String editEmployeePage(@PathVariable Long id, Model model) {
        LOGGER.info("Received request to get for edit employee with id {}", id);
        try {
            model.addAttribute("employee", employeeService.getEmployee(id));
            return "employees/edit";
        } catch (Exception e) {
            LOGGER.error("Error getting employee with id {}", id, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/employees/edit")
    public String editEmployee(EmployeeDTO employee, Model model) {
        LOGGER.info("Received request to edit employee with id={}", employee.getId());
        try {
            employeeService.saveOrUpdateEmployee(employee);
            return "redirect:/employees/" + employee.getId();
        } catch (Exception e) {
            LOGGER.error("Error updating employee with id={}", employee.getId());
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {
        LOGGER.info("Received request to get employee with id={}", id);
        try {
            model.addAttribute("employee", employeeService.getEmployee(id));
            return "employees/info";
        } catch (Exception e) {
            LOGGER.error("Error getting employee with id={}", id);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/employees/{id}/delete")
    public String deleteEmployee(@PathVariable Long id, Model model) {
        LOGGER.info("Received request to delete employee with id={}", id);
        try {
            employeeService.deleteEmployee(id);
            return "redirect:/employees";
        } catch (Exception e) {
            LOGGER.error("Error deleting employee with id={}", id);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/employees/clear-filter")
    public String clearFilter(Model model) {
        model.addAttribute("filterData", new EmployeeFilterData());
        return "redirect:/employees";
    }

    @PostMapping("/employees/{id}/return")
    public String returnToCurrent(@PathVariable Long id, Model model) {
        LOGGER.info("Received request to return employee with id={}", id);
        try {
            employeeService.returnToCurrent(id);
            return "redirect:/employees";
        } catch (Exception e) {
            LOGGER.error("Error returning employee with id={}", id);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
