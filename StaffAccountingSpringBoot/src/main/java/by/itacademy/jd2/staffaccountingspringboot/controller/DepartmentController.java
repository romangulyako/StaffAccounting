package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.DepartmentDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.DepartmentInfoDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PageFilter;
import by.itacademy.jd2.staffaccountingspringboot.service.api.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/departments")
    public String getDepartments(@RequestParam(defaultValue = "true") Boolean isActual,
                                 @ModelAttribute("pageFilter") PageFilter pageFilter,
                                 Model model) {
        Page<DepartmentDTO> departmentsPage =
                departmentService.getDepartments(pageFilter.getPage(), pageFilter.getSize(), isActual);
        model.addAttribute("departments", departmentsPage.getContent());
        model.addAttribute("pageFilter", pageFilter);
        model.addAttribute("isActual", isActual);
        model.addAttribute("totalPages", departmentsPage.getTotalPages());

        return "departments/list";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @GetMapping("/departments/add")
    public String addDepartmentPage(Model model) {
        model.addAttribute("newDepartment", new DepartmentDTO());
        return "departments/add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @PostMapping("departments/add")
    public String addDepartment(@ModelAttribute("newDepartment") DepartmentDTO departmentDTO) {
        departmentService.saveOrUpdateDepartment(departmentDTO);

        return "redirect:/departments";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @GetMapping("/departments/{id}/edit")
    public String editDepartmentPage(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getDepartment(id));

        return "departments/edit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @PostMapping("/departments/{id}/edit")
    public String editDepartment(@ModelAttribute("department") DepartmentDTO departmentDTO) {
        departmentService.saveOrUpdateDepartment(departmentDTO);

        return "redirect:/departments";
    }

    @GetMapping("/departments/{id}")
    public String getDepartment(@RequestParam(defaultValue = "true") Boolean isActual,
                                @PathVariable Long id,
                                @ModelAttribute("pageFilter") PageFilter pageFilter,
                                Model model) {
        DepartmentInfoDTO departmentInfo =
                departmentService.getDepartmentInfo(id, isActual, pageFilter.getPage(), pageFilter.getSize());
        model.addAttribute("department", departmentInfo.getDepartment());
        model.addAttribute("positions", departmentInfo.getPositions());
        model.addAttribute("isActual", departmentInfo.getIsActual());
        model.addAttribute("pageFilter", pageFilter);
        model.addAttribute("totalPages", departmentInfo.getTotalPages());

        return "departments/info";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @PostMapping("/departments/{id}/delete")
    public String deleteDepartment(@PathVariable Long id,
                                   @RequestParam Boolean isActual) {
        departmentService.deleteDepartment(id);

        return "redirect:/departments?isActual=" + isActual;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @PostMapping("/departments/{id}/reduce")
    public String reduceDepartment(@PathVariable Long id,
                                   @RequestParam Boolean isActual) {
        departmentService.reduceDepartment(id);

        return "redirect:/departments?isActual=" + isActual;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @PostMapping("/departments/{id}/restore")
    public String restoreDepartment(@PathVariable Long id,
                                    @RequestParam Boolean isActual) {
        departmentService.restoreDepartment(id);

        return "redirect:/departments?isActual=" + isActual;
    }
}
