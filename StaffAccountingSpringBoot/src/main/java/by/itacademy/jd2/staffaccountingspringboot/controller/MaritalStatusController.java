package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.model.MaritalStatusDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.MaritalStatusService;
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
public class MaritalStatusController {
    private final MaritalStatusService maritalStatusService;

    @GetMapping("/employee/{employeeId}/marital-status")
    public String getMaritalStatus(@PathVariable Long employeeId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "2") int size,
                                   Model model) {
        Page<MaritalStatusDTO> maritalStatuses =
                maritalStatusService.getAllMaritalStatuses(employeeId, PageRequest.of(page, size));
        model.addAttribute("maritalStatuses", maritalStatuses.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", maritalStatuses.getTotalPages());
        model.addAttribute("employeeId", employeeId);
        return "marital-status/list";
    }

    @GetMapping("/employee/{employeeId}/marital-status/add")
    public String saveMaritalStatusPage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newMaritalStatus", new MaritalStatusDTO());
        return "marital-status/add";
    }

    @PostMapping("/employee/{employeeId}/marital-status/add")
    public String saveMaritalStatus(@PathVariable Long employeeId, MaritalStatusDTO maritalStatusDTO) {
        maritalStatusService.addMaritalStatus(maritalStatusDTO);
        return "redirect:/employee/" + employeeId + "/marital-status";
    }

    @GetMapping("/employee/{employeeId}/marital-status/edit/{id}")
    public String editMaritalStatusPage(@PathVariable Long employeeId,
                                        @PathVariable Long id,
                                        Model model) {
        model.addAttribute("maritalStatus", maritalStatusService.getMaritalStatus(id));
        return "marital-status/edit";
    }

    @PostMapping("/employee/{employeeId}/marital-status/edit/{id}")
    public String editMaritalStatus(@PathVariable Long employeeId,
                                    @PathVariable Long id,
                                    MaritalStatusDTO maritalStatusDTO) {
        maritalStatusService.editMaritalStatus(maritalStatusDTO);
        return "redirect:/employee/" + employeeId + "/marital-status";
    }

    @PostMapping("/employee/{employeeId}/marital-status/delete/{id}")
    public String deleteMaritalStatus(@PathVariable Long employeeId,
                                      @PathVariable Long id) {
        maritalStatusService.deleteMaritalStatus(id);
        return "redirect:/employee/" + employeeId + "/marital-status";
    }

}
