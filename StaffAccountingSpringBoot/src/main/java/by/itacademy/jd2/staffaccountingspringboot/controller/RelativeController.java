package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.model.RelativeDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.RelativeService;
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
public class RelativeController {
    private final RelativeService relativeService;

    @GetMapping("/employee/{employeeId}/relatives")
    public String getRelatives(@PathVariable Long employeeId,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "2") int size,
                               Model model) {
        Page<RelativeDTO> relatives = relativeService.getRelatives(employeeId, PageRequest.of(page, size));
        model.addAttribute("relatives", relatives.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", relatives.getTotalPages());
        model.addAttribute("employeeId", employeeId);

        return "relatives/list";
    }

    @GetMapping("/employee/{employeeId}/relatives/add")
    public String addRelativePage(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("newRelative", new RelativeDTO());
        return "relatives/add";
    }

    @PostMapping("/employee/{employeeId}/relatives/add")
    public String addRelative(@PathVariable Long employeeId, RelativeDTO relativeDTO) {
        relativeService.addRelative(relativeDTO);
        return "redirect:/employee/" + employeeId + "/relatives";
    }

    @GetMapping("/employee/{employeeId}/relatives/edit/{id}")
    public String editRelativePage(@PathVariable Long employeeId,
                                   @PathVariable Long id,
                                   Model model) {
        model.addAttribute("relative", relativeService.getRelative(id));
        return "relatives/edit";
    }

    @PostMapping("/employee/{employeeId}/relatives/edit/{id}")
    public String editRelative(@PathVariable Long employeeId,
                               @PathVariable Long id,
                               RelativeDTO relativeDTO) {
        relativeService.updateRelative(relativeDTO);
        return "redirect:/employee/" + employeeId + "/relatives";
    }

    @PostMapping("/employee/{employeeId}/relatives/delete/{id}")
    public String deleteRelative(@PathVariable Long employeeId,
                                 @PathVariable Long id) {
        relativeService.deleteRelative(id);
        return "redirect:/employee/" + employeeId + "/relatives";
    }
}
