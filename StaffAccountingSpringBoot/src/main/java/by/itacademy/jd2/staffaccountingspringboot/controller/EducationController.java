package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.model.EducationDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;

    @GetMapping("/employee/{employeeId}/education")
    public String getEducation(@PathVariable Long employeeId,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "2") int size,
                               Model model) {
        Page<EducationDTO> educations =
                educationService.getEducationsByEmployeeId(employeeId, PageRequest.of(page, size));
        model.addAttribute("educations", educations.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", educations.getTotalPages());
        model.addAttribute("employeeId", employeeId);

        return "education/list";
    }

    @GetMapping("/employee/{employeeId}/education/add")
    public String addEducationPage(@PathVariable Long employeeId,
                                   Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newEducation", new EducationDTO());
        return "education/add";
    }

    @PostMapping("/employee/{employeeId}/education/add")
    public String addEducation(@PathVariable Long employeeId,
                               EducationDTO educationDTO) {
        educationService.saveOrUpdateEducation(educationDTO);
        return "redirect:/employee/" + employeeId + "/education";
    }

    @GetMapping("/employee/{employeeId}/education/edit/{id}")
    public String editEducationPage(@PathVariable Long id,
                                    Model model) {
        model.addAttribute("education", educationService.getEducation(id));
        return "education/edit";
    }

    @PostMapping("/employee/{employeeId}/education/edit/{id}")
    public String editEducation(@PathVariable Long employeeId,
                                EducationDTO educationDTO) {
        educationService.saveOrUpdateEducation(educationDTO);
        return "redirect:/employee/" + employeeId + "/education";
    }

    @PostMapping("/employee/{employeeId}/education/delete/{id}")
    public String deleteEducation(@PathVariable Long employeeId,
                                  @PathVariable Long id) {
        educationService.deleteEducation(id);
        return "redirect:/employee/" + employeeId + "/education";
    }
}
