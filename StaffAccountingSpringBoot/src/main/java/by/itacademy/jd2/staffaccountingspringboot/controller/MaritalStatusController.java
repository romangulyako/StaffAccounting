package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.model.MaritalStatusDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.MaritalStatusService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static final Logger LOGGER = LoggerFactory.getLogger(MaritalStatusController.class);
    private final MaritalStatusService maritalStatusService;

    @GetMapping("/employee/{employeeId}/marital-status")
    public String getMaritalStatus(@PathVariable Long employeeId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "2") int size,
                                   Model model) {
        LOGGER.info("Received request to get marital statuses for employee with id= {}", employeeId);
        try {
            Page<MaritalStatusDTO> maritalStatuses =
                    maritalStatusService.getAllMaritalStatuses(employeeId, PageRequest.of(page, size));
            model.addAttribute("maritalStatuses", maritalStatuses.getContent());
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            model.addAttribute("totalPages", maritalStatuses.getTotalPages());
            model.addAttribute("employeeId", employeeId);
            return "marital-status/list";
        } catch (Exception e) {
            LOGGER.error("Error getting marital statuses for employee with id= {}", employeeId, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employee/{employeeId}/marital-status/add")
    public String saveMaritalStatusPage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newMaritalStatus", new MaritalStatusDTO());
        return "marital-status/add";
    }

    @PostMapping("/employee/{employeeId}/marital-status/add")
    public String saveMaritalStatus(@PathVariable Long employeeId,
                                    MaritalStatusDTO maritalStatusDTO,
                                    Model model) {
        LOGGER.info("Received request to add relative for employee with id={}", maritalStatusDTO.getEmployeeId());
        try {
            maritalStatusService.saveOrUpdateMaritalStatus(maritalStatusDTO);
            return "redirect:/employee/" + employeeId + "/marital-status";
        } catch (Exception e) {
            LOGGER.error("Error adding marital status for employee with id={}", employeeId, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employee/{employeeId}/marital-status/edit/{id}")
    public String editMaritalStatusPage(@PathVariable Long id,
                                        Model model) {
        LOGGER.info("Received request to get for edit marital status with id={}", id);
        try {
            model.addAttribute("maritalStatus", maritalStatusService.getMaritalStatus(id));
            return "marital-status/edit";
        } catch (Exception e) {
            LOGGER.error("Error getting marital status with id={}", id, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/employee/{employeeId}/marital-status/edit/{id}")
    public String editMaritalStatus(MaritalStatusDTO maritalStatusDTO, Model model) {
        LOGGER.info("Received request to edit marital status with id={}", maritalStatusDTO.getId());
        try {
            maritalStatusService.saveOrUpdateMaritalStatus(maritalStatusDTO);
            return "redirect:/employee/" + maritalStatusDTO.getEmployeeId() + "/marital-status";
        } catch (Exception e) {
            LOGGER.error("Error editing marital status with id={}", maritalStatusDTO.getId(), e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/employee/{employeeId}/marital-status/delete/{id}")
    public String deleteMaritalStatus(@PathVariable Long employeeId,
                                      @PathVariable Long id,
                                      Model model) {
        LOGGER.info("Received request to delete marital status with id={}", id);
        try {
            maritalStatusService.deleteMaritalStatus(id);
            return "redirect:/employee/" + employeeId + "/marital-status";
        } catch (Exception e) {
            LOGGER.error("Error deleting marital status with id={}", id, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
