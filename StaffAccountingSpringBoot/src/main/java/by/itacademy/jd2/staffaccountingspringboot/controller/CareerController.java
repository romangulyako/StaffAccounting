package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.dto.AppointmentInfoDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.CareerStepGetDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.CareerStepSaveDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.DismissDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EditCareerDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PageFilter;
import by.itacademy.jd2.staffaccountingspringboot.service.api.CareerService;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CareerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CareerController.class);
    private final CareerService careerService;
    private final EmployeeService employeeService;

    @GetMapping("/appointment")
    public String appointmentPage(Model model) {
        LOGGER.info("Received request to get appointment info");
        AppointmentInfoDTO appointmentInfo = careerService.getAppointmentInfo();
        model.addAttribute("employees", appointmentInfo.getEmployees());
        model.addAttribute("positions", appointmentInfo.getPositions());
        model.addAttribute("careerStep", new CareerStepSaveDTO());

        return "career/appointment";
    }

    @PostMapping("/appointment")
    public String appointEmployee(@ModelAttribute("careerStep") CareerStepSaveDTO careerStep) {
        LOGGER.info("Received request to appoint employee");
        careerService.appointEmployee(careerStep);

        return "redirect:/appointment";
    }

    @GetMapping("/dismiss")
    public String dismissPage(Model model) {
        LOGGER.info("Received request to get employees for dismiss");
        List<EmployeeItemDTO> employees = employeeService.getCurrentEmployeeItems();
        model.addAttribute("employees", employees);
        model.addAttribute("dismissDTO", new DismissDTO());

        return "career/dismiss";
    }

    @PostMapping("/dismiss")
    public String dismissEmployee(@ModelAttribute("dismissDTO") DismissDTO dismissDTO) {
        LOGGER.info("Received request to dismiss employee");
        careerService.dismissEmployee(dismissDTO);

        return "redirect:/dismiss";
    }

    @GetMapping("employees/{id}/career")
    public String getCareerByEmployee(@PathVariable Long id,
                                      @ModelAttribute("pageFilter") PageFilter pageFilter,
                                      Model model) {
        LOGGER.info("Received request to get career by employee");
        Page<CareerStepGetDTO> careerPage = careerService.getEmployeesCareer(id, pageFilter.getPage(), pageFilter.getSize());
        model.addAttribute("career", careerPage.getContent());
        model.addAttribute("pageFilter", pageFilter);
        model.addAttribute("totalPages", careerPage.getTotalPages());
        model.addAttribute("employeeId", id);

        return "career/info";
    }

    @PostMapping("/employees/{employeeId}/career/delete/{id}")
    public String deleteCareerStep(@PathVariable Long id,
                                   @PathVariable Long employeeId) {
        LOGGER.info("Received request to delete career step");
        careerService.deleteCareerStep(id);

        return "redirect:/employees/" + employeeId + "/career";
    }

    @GetMapping("employees/{employeeId}/career/edit/{id}")
    public String editCareerPage(@PathVariable Long id,
                                 Model model) {
        LOGGER.info("Received request to get info for edit career step");
        EditCareerDTO editCareerDTO = careerService.getInfoForEditingCareerStep(id);
        model.addAttribute("careerStep", editCareerDTO.getCareerStep());
        model.addAttribute("positions", editCareerDTO.getPositions());

        return "career/edit";
    }

    @PostMapping("employees/{employeeId}/career/edit/{id}")
    public String editCareerStep(@ModelAttribute("careerStep") CareerStepSaveDTO careerStep) {
        LOGGER.info("Received request to edit career step");
        careerService.editCareerStep(careerStep);

        return "redirect:/employees/" + careerStep.getEmployeeId() + "/career";
    }
}
