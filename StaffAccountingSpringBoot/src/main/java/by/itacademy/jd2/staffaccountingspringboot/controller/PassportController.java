package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.model.PassportDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.PassportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PassportController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PassportController.class);
    private final PassportService passportService;

    @GetMapping("/employees/{employeeId}/passport")
    public String getPassport(@PathVariable Long employeeId,  Model model) {
        LOGGER.info("Received request to get passport for employee with id= {}", employeeId);
        try {
            model.addAttribute("passport", passportService.getPassport(employeeId));
            model.addAttribute("employeeId", employeeId);
            return "passport/info";
        } catch (Exception e) {
            LOGGER.error("Error getting passport for employee with id= {}", employeeId, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employees/{employeeId}/passport/add")
    public String addPassportPage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newPassport", new PassportDTO());
        return "passport/add";
    }

    @PostMapping("/employees/{employeeId}/passport/add")
    public String addPassport(@PathVariable Long employeeId,
                              @ModelAttribute("newPassport") PassportDTO passportDTO,
                              Model model) {
        LOGGER.info("Received request to add passport for employee with id={}", passportDTO.getEmployeeId());
        try {
            passportService.saveOrUpdatePassport(passportDTO);
            return "redirect:/employees/" + employeeId + "/passport";
        } catch (Exception e) {
            LOGGER.error("Error adding passport for employee with id={}", employeeId, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/employees/{employeeId}/passport/edit")
    public String editPassportPage(@PathVariable Long employeeId, Model model) {
        LOGGER.info("Received request to get for edit passport of employee with id={}", employeeId);
        try {
            model.addAttribute("passport", passportService.getPassport(employeeId));
            return "passport/edit";
        } catch (Exception e) {
            LOGGER.error("Error getting passport of employee with id={}", employeeId, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/employees/{employeeId}/passport/edit")
    public String editPassport(@ModelAttribute("passport") PassportDTO passportDTO, Model model) {
        LOGGER.info("Received request to edit passport with id={}", passportDTO.getId());
        try {
            passportService.saveOrUpdatePassport(passportDTO);
            return "redirect:/employees/" + passportDTO.getEmployeeId() + "/passport";
        } catch (Exception e) {
            LOGGER.error("Error editing passport with id={}", passportDTO.getId(), e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/employees/{employeeId}/passport/delete")
    public String deletePassport(@PathVariable Long employeeId, Model model) {
        LOGGER.info("Received request to delete passport of employee with id={}", employeeId);
        try {
            passportService.deletePassport(employeeId);
            return "redirect:/employees/" + employeeId;
        } catch (Exception e) {
            LOGGER.error("Error deleting passport of employee with id={}", employeeId, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
