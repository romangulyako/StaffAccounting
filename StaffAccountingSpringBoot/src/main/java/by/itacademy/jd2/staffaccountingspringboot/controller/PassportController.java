package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.PassportDTO;
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
    public String getPassport(@PathVariable Long employeeId, Model model) {
        LOGGER.info("Received request to get passport for employee with id= {}", employeeId);
        model.addAttribute("passport", passportService.getPassport(employeeId));
        model.addAttribute("employeeId", employeeId);

        return "passport/info";
    }

    @GetMapping("/employees/{employeeId}/passport/add")
    public String addPassportPage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newPassport", new PassportDTO());

        return "passport/add";
    }

    @PostMapping("/employees/{employeeId}/passport/add")
    public String addPassport(@PathVariable Long employeeId,
                              @ModelAttribute("newPassport") PassportDTO passportDTO) {
        LOGGER.info("Received request to add passport for employee with id={}", passportDTO.getEmployeeId());
        passportService.saveOrUpdatePassport(passportDTO);

        return "redirect:/employees/" + employeeId + "/passport";
    }

    @GetMapping("/employees/{employeeId}/passport/edit")
    public String editPassportPage(@PathVariable Long employeeId, Model model) {
        LOGGER.info("Received request to get for edit passport of employee with id={}", employeeId);
        model.addAttribute("passport", passportService.getPassport(employeeId));

        return "passport/edit";
    }

    @PostMapping("/employees/{employeeId}/passport/edit")
    public String editPassport(@ModelAttribute("passport") PassportDTO passportDTO) {
        LOGGER.info("Received request to edit passport with id={}", passportDTO.getId());
        passportService.saveOrUpdatePassport(passportDTO);

        return "redirect:/employees/" + passportDTO.getEmployeeId() + "/passport";
    }

    @PostMapping("/employees/{employeeId}/passport/delete")
    public String deletePassport(@PathVariable Long employeeId) {
        LOGGER.info("Received request to delete passport of employee with id={}", employeeId);
        passportService.deletePassport(employeeId);

        return "redirect:/employees/" + employeeId;
    }
}
