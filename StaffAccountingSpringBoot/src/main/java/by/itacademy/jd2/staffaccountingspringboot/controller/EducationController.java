package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.EducationDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EducationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EducationController {
    public static final Logger LOGGER = LoggerFactory.getLogger(EducationController.class);
    private final EducationService educationService;

    @GetMapping("/employees/{employeeId}/education")
    public String getEducation(@PathVariable Long employeeId,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "2") int size,
                               Model model) {
        LOGGER.info("Received request to get education for employee with id= {}", employeeId);
        try {
            Page<EducationDTO> educations =
                    educationService.getEducationsByEmployeeId(employeeId, page, size);
            model.addAttribute("educations", educations.getContent());
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            model.addAttribute("totalPages", educations.getTotalPages());
            model.addAttribute("employeeId", employeeId);

            return "education/list";
        } catch (Exception e) {
            LOGGER.error("Error getting education for employee with id= {}", employeeId, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employees/{employeeId}/education/add")
    public String addEducationPage(@PathVariable Long employeeId,
                                   Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newEducation", new EducationDTO());
        return "education/add";
    }

    @PostMapping("/employees/{employeeId}/education/add")
    public String addEducation(@PathVariable Long employeeId,
                               EducationDTO educationDTO,
                               Model model) {
        LOGGER.info("Received request to add education for employee with id={}", employeeId);
        try {
            educationService.saveOrUpdateEducation(educationDTO);
            return "redirect:/employees/" + employeeId +"/education";
        } catch (Exception e) {
            LOGGER.error("Error adding education for employee with id={}", employeeId, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employees/{employeeId}/education/edit/{id}")
    public String editEducationPage(@PathVariable Long id,
                                    Model model) {
        LOGGER.info("Received request to get for edit education with id={}", id);
        try {
            model.addAttribute("education", educationService.getEducation(id));
            return "education/edit";
        } catch (Exception e) {
            LOGGER.error("Error getting education with id={}", id, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/employees/{employeeId}/education/edit/{id}")
    public String editEducation(EducationDTO educationDTO, Model model) {
        LOGGER.info("Received request to edit education with id={}", educationDTO.getId());
        try {
            educationService.saveOrUpdateEducation(educationDTO);
            return "redirect:/employees/" + educationDTO.getEmployeeId() +"/education";
        } catch (Exception e) {
            LOGGER.error("Error editing education with id={}", educationDTO.getId(), e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/employees/{employeeId}/education/delete/{id}")
    public String deleteEducation(@PathVariable Long employeeId,
                                  @PathVariable Long id,
                                  Model model) {
        LOGGER.info("Received request to delete education with id={}", id);
        try {
            educationService.deleteEducation(id);
            return "redirect:/employees/" + employeeId +"/education";
        } catch (Exception e) {
            LOGGER.error("Error deleting education with id={}", id, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
