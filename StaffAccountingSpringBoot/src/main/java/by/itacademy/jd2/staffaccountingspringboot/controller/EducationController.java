package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.EducationDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PageFilter;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EducationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EducationController {
    public static final Logger LOGGER = LoggerFactory.getLogger(EducationController.class);
    private final EducationService educationService;

    @GetMapping("/employees/{employeeId}/education")
    public String getEducation(@PathVariable Long employeeId,
                               @ModelAttribute("pageFilter") PageFilter pageFilter,
                               Model model) {
        LOGGER.info("Received request to get education for employee with id= {}", employeeId);
        Page<EducationDTO> educations =
                educationService.getEducationsByEmployeeId(employeeId, pageFilter.getPage(), pageFilter.getSize());
        model.addAttribute("educations", educations.getContent());
        model.addAttribute("pageFilter", pageFilter);
        model.addAttribute("totalPages", educations.getTotalPages());
        model.addAttribute("employeeId", employeeId);

        return "education/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees/{employeeId}/education/add")
    public String addEducationPage(@PathVariable Long employeeId,
                                   Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newEducation", new EducationDTO());

        return "education/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/education/add")
    public String addEducation(@PathVariable Long employeeId,
                               EducationDTO educationDTO) {
        LOGGER.info("Received request to add education for employee with id={}", employeeId);
        educationService.saveOrUpdateEducation(educationDTO);

        return "redirect:/employees/" + employeeId +"/education";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees/{employeeId}/education/edit/{id}")
    public String editEducationPage(@PathVariable Long id,
                                    Model model) {
        LOGGER.info("Received request to get for edit education with id={}", id);
        model.addAttribute("education", educationService.getEducation(id));

        return "education/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/education/edit/{id}")
    public String editEducation(EducationDTO educationDTO, Model model) {
        LOGGER.info("Received request to edit education with id={}", educationDTO.getId());
        educationService.saveOrUpdateEducation(educationDTO);

        return "redirect:/employees/" + educationDTO.getEmployeeId() +"/education";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/education/delete/{id}")
    public String deleteEducation(@PathVariable Long employeeId,
                                  @PathVariable Long id,
                                  Model model) {
        LOGGER.info("Received request to delete education with id={}", id);
        educationService.deleteEducation(id);

        return "redirect:/employees/" + employeeId +"/education";
    }
}
