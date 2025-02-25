package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.DepartmentDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.DepartmentInfoDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    private final DepartmentService departmentService;

    @GetMapping("/departments")
    public String getDepartments(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "2") int size,
                                 @RequestParam(defaultValue = "true") Boolean isActual,
                                 Model model) {
        LOGGER.info("Received request to get all departments (isActual={})", isActual);
        Page<DepartmentDTO> departmentsPage =
                departmentService.getDepartments(page, size, isActual);
        model.addAttribute("departments", departmentsPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("isActual", isActual);
        model.addAttribute("totalPages", departmentsPage.getTotalPages());

        return "departments/list";
    }

    @GetMapping("/departments/add")
    public String addDepartmentPage(Model model) {
        model.addAttribute("newDepartment", new DepartmentDTO());
        return "departments/add";
    }

    @PostMapping("departments/add")
    public String addDepartment(@ModelAttribute("newDepartment") DepartmentDTO departmentDTO) {
        LOGGER.info("Received request to add new department");
        departmentService.saveOrUpdateDepartment(departmentDTO);

        return "redirect:/departments";
    }

    @GetMapping("/departments/{id}/edit")
    public String editDepartmentPage(@PathVariable Long id, Model model) {
        LOGGER.info("Received request to get for editing department with id {}", id);
        model.addAttribute("department", departmentService.getDepartment(id));

        return "departments/edit";
    }

    @PostMapping("/departments/{id}/edit")
    public String editDepartment(@ModelAttribute("department") DepartmentDTO departmentDTO) {
        LOGGER.info("Received request to edit department with id {}", departmentDTO.getId());
        departmentService.saveOrUpdateDepartment(departmentDTO);

        return "redirect:/departments";
    }

    @GetMapping("/departments/{id}")
    public String getDepartment(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "2") int size,
                                @RequestParam(defaultValue = "true") Boolean isActual,
                                @PathVariable Long id,
                                Model model) {
        LOGGER.info("Received request to get for department with id {}", id);
        DepartmentInfoDTO departmentInfo = departmentService.getDepartmentInfo(id, isActual, page, size);
        model.addAttribute("department", departmentInfo.getDepartment());
        model.addAttribute("positions", departmentInfo.getPositions());
        model.addAttribute("isActual", departmentInfo.getIsActual());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", departmentInfo.getTotalPages());

        return "departments/info";
    }

    @PostMapping("/departments/{id}/delete")
    public String deleteDepartment(@PathVariable Long id,
                                   @RequestParam Boolean isActual) {
        LOGGER.info("Received request to delete department with id {}", id);
        departmentService.deleteDepartment(id);

        return "redirect:/departments?isActual=" + isActual;
    }

    @PostMapping("/departments/{id}/reduce")
    public String reduceDepartment(@PathVariable Long id,
                                   @RequestParam Boolean isActual) {
        LOGGER.info("Received request to reduce department with id {}", id);
        departmentService.reduceDepartment(id);

        return "redirect:/departments?isActual=" + isActual;
    }

    @PostMapping("/departments/{id}/restore")
    public String restoreDepartment(@PathVariable Long id,
                                    @RequestParam Boolean isActual) {
        LOGGER.info("Received request to restore department with id {}", id);
        departmentService.restoreDepartment(id);

        return "redirect:/departments?isActual=" + isActual;
    }
}
