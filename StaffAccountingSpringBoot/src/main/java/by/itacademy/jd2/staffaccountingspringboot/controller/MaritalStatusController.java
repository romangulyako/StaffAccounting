package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.MaritalStatusDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.MaritalStatusService;
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
public class MaritalStatusController {
    public static final Logger LOGGER = LoggerFactory.getLogger(MaritalStatusController.class);
    private final MaritalStatusService maritalStatusService;

    @GetMapping("/employees/{employeeId}/marital-status")
    public String getMaritalStatus(@PathVariable Long employeeId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "2") int size,
                                   Model model) {
        LOGGER.info("Received request to get marital statuses for employee with id= {}", employeeId);
        Page<MaritalStatusDTO> maritalStatuses =
                maritalStatusService.getAllMaritalStatuses(employeeId, page, size);
        model.addAttribute("maritalStatuses", maritalStatuses.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", maritalStatuses.getTotalPages());
        model.addAttribute("employeeId", employeeId);

        return "marital-status/list";
    }

    @GetMapping("/employees/{employeeId}/marital-status/add")
    public String saveMaritalStatusPage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newMaritalStatus", new MaritalStatusDTO());

        return "marital-status/add";
    }

    @PostMapping("/employees/{employeeId}/marital-status/add")
    public String saveMaritalStatus(@PathVariable Long employeeId,
                                    MaritalStatusDTO maritalStatusDTO) {
        LOGGER.info("Received request to add relative for employee with id={}", maritalStatusDTO.getEmployeeId());
        maritalStatusService.saveOrUpdateMaritalStatus(maritalStatusDTO);

        return "redirect:/employees/" + employeeId + "/marital-status";
    }

    @GetMapping("/employees/{employeeId}/marital-status/edit/{id}")
    public String editMaritalStatusPage(@PathVariable Long id,
                                        Model model) {
        LOGGER.info("Received request to get for edit marital status with id={}", id);
        model.addAttribute("maritalStatus", maritalStatusService.getMaritalStatus(id));

        return "marital-status/edit";
    }

    @PostMapping("/employees/{employeeId}/marital-status/edit/{id}")
    public String editMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        LOGGER.info("Received request to edit marital status with id={}", maritalStatusDTO.getId());
        maritalStatusService.saveOrUpdateMaritalStatus(maritalStatusDTO);

        return "redirect:/employees/" + maritalStatusDTO.getEmployeeId() + "/marital-status";
    }

    @PostMapping("/employees/{employeeId}/marital-status/delete/{id}")
    public String deleteMaritalStatus(@PathVariable Long employeeId,
                                      @PathVariable Long id) {
        LOGGER.info("Received request to delete marital status with id={}", id);
        maritalStatusService.deleteMaritalStatus(id);

        return "redirect:/employees/" + employeeId + "/marital-status";
    }
}
