package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.model.PassportDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PassportController {
    private final PassportService passportService;

    @GetMapping("/employee/{employeeId}/passport/info")
    public String getPassport(@PathVariable Long employeeId,  Model model) {
        model.addAttribute("passport", passportService.getPassport(employeeId));
        model.addAttribute("employeeId", employeeId);
        return "passport/info";
    }

    @GetMapping("/employee/{employeeId}/passport/add")
    public String addPassportPage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newPassport", new PassportDTO());
        return "passport/add";
    }

    @PostMapping("/employee/{employeeId}/passport/add")
    public String addPassport(@PathVariable Long employeeId, @ModelAttribute("newPassport") PassportDTO passportDTO) {
        passportService.addPassport(passportDTO);
        return "redirect:/employee/" + employeeId + "/passport/info";
    }

    @GetMapping("/employee/{employeeId}/passport/edit")
    public String editPassportPage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("passport", passportService.getPassport(employeeId));
        return "passport/edit";
    }

    @PostMapping("/employee/{employeeId}/passport/edit")
    public String editPassport(@PathVariable Long employeeId, @ModelAttribute("passport") PassportDTO passportDTO) {
        passportService.updatePassport(passportDTO);
        return "redirect:/employee/" + employeeId + "/passport/info";
    }

    @PostMapping("/employee/passport/delete/{employeeId}")
    public String deletePassport(@PathVariable Long employeeId) {
        passportService.deletePassport(employeeId);
        return "redirect:/employee/info/" + employeeId;
    }
}
