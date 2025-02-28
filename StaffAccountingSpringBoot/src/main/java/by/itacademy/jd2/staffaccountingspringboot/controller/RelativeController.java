package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.PageFilter;
import by.itacademy.jd2.staffaccountingspringboot.dto.RelativeDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.RelativeService;
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
public class RelativeController {
    public static final Logger LOGGER = LoggerFactory.getLogger(RelativeController.class);
    private final RelativeService relativeService;

    @GetMapping("/employees/{employeeId}/relatives")
    public String getRelatives(@PathVariable Long employeeId,
                               @ModelAttribute("pageFilter") PageFilter pageFilter,
                               Model model) {
        LOGGER.info("Received request to get relatives for employee with id= {}", employeeId);
        Page<RelativeDTO> relatives =
                relativeService.getRelatives(employeeId, pageFilter.getPage(), pageFilter.getSize());
        model.addAttribute("relatives", relatives.getContent());
        model.addAttribute("pageFilter", pageFilter);
        model.addAttribute("totalPages", relatives.getTotalPages());
        model.addAttribute("employeeId", employeeId);

        return "relatives/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees/{employeeId}/relatives/add")
    public String addRelativePage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newRelative", new RelativeDTO());

        return "relatives/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/relatives/add")
    public String addRelative(@PathVariable Long employeeId,
                              RelativeDTO relativeDTO) {
        LOGGER.info("Received request to add relative for employee with id={}", relativeDTO.getEmployeeId());
        relativeService.saveOrUpdateRelative(relativeDTO);

        return "redirect:/employees/" + employeeId + "/relatives";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employees/{employeeId}/relatives/edit/{id}")
    public String editRelativePage(@PathVariable Long id,
                                   Model model) {
        LOGGER.info("Received request to get for edit relative with id={}", id);
        model.addAttribute("relative", relativeService.getRelative(id));

        return "relatives/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/relatives/edit/{id}")
    public String editRelative(RelativeDTO relativeDTO) {
        LOGGER.info("Received request to edit relative with id={}", relativeDTO.getId());
        relativeService.saveOrUpdateRelative(relativeDTO);

        return "redirect:/employees/" + relativeDTO.getEmployeeId() + "/relatives";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employees/{employeeId}/relatives/delete/{id}")
    public String deleteRelative(@PathVariable Long employeeId,
                                 @PathVariable Long id) {
        LOGGER.info("Received request to delete relative with id={}", id);
        relativeService.deleteRelative(id);

        return "redirect:/employees/" + employeeId + "/relatives";
    }
}
