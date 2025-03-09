package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.PageFilter;
import by.itacademy.jd2.staffaccountingspringboot.dto.PositionDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PositionHistoryDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @GetMapping("/positions/add")
    public String addPositionPage(@RequestParam Long departmentId,
                                  Model model) {
        model.addAttribute("newPosition", new PositionDTO());
        model.addAttribute("departmentId", departmentId);
        return "position/add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @PostMapping("/positions/add")
    public String addPosition(@ModelAttribute("newPosition") PositionDTO positionDTO) {
        positionService.saveOrUpdatePosition(positionDTO);
        return "redirect:/departments/" + positionDTO.getDepartmentId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @GetMapping("/positions/{id}/edit")
    public String editPositionPage(@PathVariable Long id, Model model) {
        model.addAttribute("position", positionService.getPositionById(id));
        return "position/edit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @PostMapping("positions/{id}/edit")
    public String editPosition(@ModelAttribute("position") PositionDTO positionDTO) {
        positionService.saveOrUpdatePosition(positionDTO);
        return "redirect:/departments/" + positionDTO.getDepartmentId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @PostMapping("positions/{id}/delete")
    public String deletePosition(@PathVariable Long id,
                                 @RequestParam Long departmentId,
                                 @RequestParam Boolean isActual) {
        positionService.deletePosition(id);
        return "redirect:/departments/" + departmentId + "?isActual=" + isActual;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @PostMapping("positions/{id}/reduce")
    public String reducePosition(@PathVariable Long id,
                                 @RequestParam Long departmentId,
                                 @RequestParam Boolean isActual) {
        positionService.reducePosition(id);
        return "redirect:/departments/" + departmentId + "?isActual=" + isActual;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STRUCTURE')")
    @PostMapping("positions/{id}/restore")
    public String restorePosition(@PathVariable Long id,
                                  @RequestParam Long departmentId,
                                  @RequestParam Boolean isActual) {
        positionService.restorePosition(id);
        return "redirect:/departments/" + departmentId + "?isActual=" + isActual;
    }

    @GetMapping("/positions/{id}/history")
    public String getPositionHistory(@PathVariable Long id,
                                     @RequestParam Long departmentId,
                                     @RequestParam Boolean isActual,
                                     @ModelAttribute("pageFilter") PageFilter pageFilter,
                                     Model model) {
        Page<PositionHistoryDTO> historyPage =
                positionService.getPositionHistory(id, pageFilter.getPage(), pageFilter.getSize());
        model.addAttribute("history", historyPage.getContent());
        model.addAttribute("pageFilter", pageFilter);
        model.addAttribute("totalPages", historyPage.getTotalPages());
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("positionId", id);
        model.addAttribute("isActual", isActual);
        return "position/history";
    }
}
