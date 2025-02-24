package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.model.PositionDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.PositionHistoryDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.PositionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(PositionController.class);
    private final PositionService positionService;

    @GetMapping("/positions/add")
    public String addPositionPage(@RequestParam Long departmentId,
                                  Model model) {
        model.addAttribute("newPosition", new PositionDTO());
        model.addAttribute("departmentId", departmentId);
        return "position/add";
    }

    @PostMapping("/positions/add")
    public String addPosition(@ModelAttribute("newPosition") PositionDTO positionDTO,
                              Model model) {
        LOGGER.info("Received request to add position");
        try {
            positionService.saveOrUpdatePosition(positionDTO);
            return "redirect:/departments/" + positionDTO.getDepartmentId();
        } catch (Exception e) {
            LOGGER.error("Error saving new position", e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/positions/{id}/edit")
    public String editPositionPage(@PathVariable Long id, Model model) {
        LOGGER.info("Received request to get for edit position");
        try {
            model.addAttribute("position", positionService.getPositionById(id));
            return "position/edit";
        } catch (Exception e) {
            LOGGER.error("Error getting for editing position", e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("positions/{id}/edit")
    public String editPosition(@ModelAttribute("position") PositionDTO positionDTO,
                               Model model) {
        LOGGER.info("Received request to edit position");
        try {
            positionService.saveOrUpdatePosition(positionDTO);
            return "redirect:/departments/" + positionDTO.getDepartmentId();
        } catch (Exception e) {
            LOGGER.error("Error editing position with id={}",positionDTO.getId(), e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("positions/{id}/delete")
    public String deletePosition(@PathVariable Long id,
                                 @RequestParam Long departmentId,
                                 @RequestParam Boolean isActual,
                                 Model model) {
        LOGGER.info("Received request to delete position");
        try {
            positionService.deletePosition(id);
            return "redirect:/departments/" + departmentId + "?isActual=" + isActual;
        } catch (Exception e) {
            LOGGER.error("Error deleting position with id={}",id, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("positions/{id}/reduce")
    public String reducePosition(@PathVariable Long id,
                                 @RequestParam Long departmentId,
                                 @RequestParam Boolean isActual,
                                 Model model) {
        LOGGER.info("Received request to reduce position");
        try {
            positionService.reducePosition(id);
            return "redirect:/departments/" + departmentId + "?isActual=" + isActual;
        } catch (Exception e) {
            LOGGER.error("Error reducing position with id={}",id, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("positions/{id}/restore")
    public String restorePosition(@PathVariable Long id,
                                  @RequestParam Long departmentId,
                                  @RequestParam Boolean isActual,
                                  Model model) {
        LOGGER.info("Received request to restore position");
        try {
            positionService.restorePosition(id);
            return "redirect:/departments/" + departmentId + "?isActual=" + isActual;
        } catch (Exception e) {
            LOGGER.error("Error restoring position with id={}",id, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/positions/{id}/history")
    public String getPositionHistory(@PathVariable Long id,
                                     @RequestParam Long departmentId,
                                     @RequestParam Boolean isActual,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "2") int size,
                                     Model model) {
        LOGGER.info("Received request to get history for position");
        try {
            Page<PositionHistoryDTO> historyPage = positionService.getPositionHistory(id, page, size);
            model.addAttribute("history", historyPage.getContent());
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            model.addAttribute("totalPages", historyPage.getTotalPages());
            model.addAttribute("departmentId", departmentId);
            model.addAttribute("positionId", id);
            model.addAttribute("isActual", isActual);
            return "position/history";
        } catch (Exception e) {
            LOGGER.error("Error getting history for position with id={}",id, e);
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
