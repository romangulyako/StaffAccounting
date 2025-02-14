package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
import lombok.RequiredArgsConstructor;
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
    private final EmployeeService employeeService;

    @GetMapping({"/", "/employees"})
    public String getEmployees(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "2") int size,
                               @RequestParam(defaultValue = "false") Boolean isFired,
                               EmployeeFilterData filterData,
                               Model model) {
        Page<EmployeeDTO> employeesPage = employeeService.getEmployees(filterData, isFired, PageRequest.of(page, size));
        model.addAttribute("employees", employeesPage.getContent());
        model.addAttribute("isFired", isFired);
        model.addAttribute("filterData", filterData);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", employeesPage.getTotalPages());
        model.addAttribute("size", size);

        return "employees/list";
    }

    @GetMapping("/employee/add")
    public String addEmployeePage(Model model) {
        model.addAttribute("newEmployee", new EmployeeDTO());
        return "employees/add";
    }

    @PostMapping("/employee/add")
    public String addEmployee(@ModelAttribute("newEmployee") EmployeeDTO employee) {
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployeePage(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "employees/edit";
    }

    @PostMapping("/employee/edit")
    public String editEmployee(EmployeeDTO employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employee/info/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "employees/info";
    }

    @PostMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @PostMapping("/employees/clear-filter")
    public String clearFilter(Model model) {
        model.addAttribute("filterData", new EmployeeFilterData());
        return "redirect:/employees";
    }
}
