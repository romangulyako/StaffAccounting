package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.MaritalStatusDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PageFilter;
import by.itacademy.jd2.staffaccountingspringboot.service.api.MaritalStatusService;
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
public class MaritalStatusController {
    private final MaritalStatusService maritalStatusService;

    @GetMapping("/employees/{employeeId}/marital-status")
    public String getMaritalStatus(@PathVariable Long employeeId,
                                   @ModelAttribute("pageFilter") PageFilter pageFilter,
                                   Model model) {
        Page<MaritalStatusDTO> maritalStatuses =
                maritalStatusService.getMaritalStatusesByEmployee(employeeId, pageFilter.getPage(), pageFilter.getSize());
        model.addAttribute("maritalStatuses", maritalStatuses.getContent());
        model.addAttribute("pageFilter", pageFilter);
        model.addAttribute("totalPages", maritalStatuses.getTotalPages());
        model.addAttribute("employeeId", employeeId);
        return "marital-status/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees/{employeeId}/marital-status/add")
    public String saveMaritalStatusPage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newMaritalStatus", new MaritalStatusDTO());
        return "marital-status/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/marital-status/add")
    public String saveMaritalStatus(@PathVariable Long employeeId,
                                    MaritalStatusDTO maritalStatusDTO) {
        maritalStatusService.saveOrUpdateMaritalStatus(maritalStatusDTO);
        return "redirect:/employees/" + employeeId + "/marital-status";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees/{employeeId}/marital-status/edit/{id}")
    public String editMaritalStatusPage(@PathVariable Long id,
                                        Model model) {
        model.addAttribute("maritalStatus", maritalStatusService.getMaritalStatus(id));
        return "marital-status/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/marital-status/edit/{id}")
    public String editMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        maritalStatusService.saveOrUpdateMaritalStatus(maritalStatusDTO);
        return "redirect:/employees/" + maritalStatusDTO.getEmployeeId() + "/marital-status";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/marital-status/delete/{id}")
    public String deleteMaritalStatus(@PathVariable Long employeeId,
                                      @PathVariable Long id) {
        maritalStatusService.deleteMaritalStatus(id);
        return "redirect:/employees/" + employeeId + "/marital-status";
    }
}
