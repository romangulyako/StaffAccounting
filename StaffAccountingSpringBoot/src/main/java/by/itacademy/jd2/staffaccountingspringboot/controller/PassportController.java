package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.PassportDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.PassportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PassportController {
    private final PassportService passportService;

    @GetMapping("/employees/{employeeId}/passport")
    public String getPassport(@PathVariable Long employeeId, Model model) {
        model.addAttribute("passport", passportService.getPassport(employeeId));
        model.addAttribute("employeeId", employeeId);
        return "passport/info";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees/{employeeId}/passport/add")
    public String addPassportPage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newPassport", new PassportDTO());
        return "passport/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/passport/add")
    public String addPassport(@PathVariable Long employeeId,
                              @Valid @ModelAttribute("newPassport") PassportDTO passportDTO,
                              BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            passportService.saveOrUpdatePassport(passportDTO);
            return "redirect:/employees/" + employeeId + "/passport";
        }

        return "passport/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees/{employeeId}/passport/edit")
    public String editPassportPage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("passport", passportService.getPassport(employeeId));
        return "passport/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/passport/edit")
    public String editPassport(@ModelAttribute("passport") PassportDTO passportDTO) {
        passportService.saveOrUpdatePassport(passportDTO);
        return "redirect:/employees/" + passportDTO.getEmployeeId() + "/passport";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/passport/delete")
    public String deletePassport(@PathVariable Long employeeId) {
        passportService.deletePassport(employeeId);
        return "redirect:/employees/" + employeeId;
    }
}
