package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeesPageDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PageFilter;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public String getEmployees(@ModelAttribute("pageFilter") PageFilter pageFilter,
                               @RequestParam(defaultValue = "false") Boolean isFired,
                               EmployeeFilterData filterData,
                               Model model) {
        EmployeesPageDTO employeesPage =
                employeeService.getEmployeesPage(filterData, isFired, pageFilter.getPage(), pageFilter.getSize());
        model.addAttribute("employees", employeesPage.getEmployees().getContent());
        model.addAttribute("isFired", isFired);
        model.addAttribute("filterData", filterData);
        model.addAttribute("pageFilter", pageFilter);
        model.addAttribute("totalPages", employeesPage.getEmployees().getTotalPages());
        model.addAttribute("departments", employeesPage.getDepartments());

        return "employees/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees/add")
    public String addEmployeePage(Model model) {
        model.addAttribute("newEmployee", new EmployeeDTO());

        return "employees/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute("newEmployee") EmployeeDTO employee) {
        employeeService.saveOrUpdateEmployee(employee);

        return "redirect:/employees";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees/{id}/edit")
    public String editEmployeePage(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployee(id));

        return "employees/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/edit")
    public String editEmployee(EmployeeDTO employee) {
        employeeService.saveOrUpdateEmployee(employee);

        return "redirect:/employees/" + employee.getId();
    }

    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployee(id));

        return "employees/info";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{id}/delete")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }

    @PostMapping("/employees/clear-filter")
    public String clearFilter(@ModelAttribute("pageFilter") PageFilter pageFilter,
                              Model model) {
        model.addAttribute("filterData", new EmployeeFilterData());

        return "redirect:/employees?page=" + pageFilter.getPage() + "&size=" + pageFilter.getSize();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{id}/return")
    public String returnToCurrent(@PathVariable Long id) {
        employeeService.returnToCurrent(id);

        return "redirect:/employees";
    }
}
