package by.itacademy.jd2.staffaccountingspringboot.controller;

import by.itacademy.jd2.staffaccountingspringboot.model.AppointmentInfoDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.CareerStepGetDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.CareerStepSaveDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.DismissDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.EditCareerDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.service.api.CareerService;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
import lombok.RequiredArgsConstructor;
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
    private final CareerService careerService;
    private final EmployeeService employeeService;

    @GetMapping("/appointment")
    public String appointmentPage(Model model) {
        AppointmentInfoDTO appointmentInfo = careerService.getAppointmentInfo();

        model.addAttribute("employees", appointmentInfo.getEmployees());
        model.addAttribute("positions", appointmentInfo.getPositions());
        model.addAttribute("careerStep", new CareerStepSaveDTO());
        return "career/appointment";
    }

    @PostMapping("/appointment")
    public String appointEmployee(@ModelAttribute("careerStep") CareerStepSaveDTO careerStep) {
        careerService.appointEmployee(careerStep);
        return "redirect:/appointment";
    }

    @GetMapping("/dismiss")
    public String dismissPage(Model model) {
        List<EmployeeItemDTO> employees = employeeService.getCurrentEmployeeItems();
        model.addAttribute("employees", employees);
        model.addAttribute("dismissDTO", new DismissDTO());
        return "career/dismiss";
    }

    @PostMapping("/dismiss")
    public String dismissEmployee(@ModelAttribute("dismissDTO") DismissDTO dismissDTO) {
        careerService.dismissEmployee(dismissDTO);
        return "redirect:/dismiss";
    }

    @GetMapping("employees/{id}/career")
    public String getCareerByEmployee(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "2") int size,
                                      @PathVariable Long id,
                                      Model model) {
        Page<CareerStepGetDTO> careerPage = careerService.getEmployeesCareer(id, page, size);
        model.addAttribute("career", careerPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", careerPage.getTotalPages());
        model.addAttribute("employeeId", id);
        return "career/info";
    }

    @PostMapping("/employees/{employeeId}/career/delete/{id}")
    public String deleteCareerStep(@PathVariable Long id,
                                   @PathVariable Long employeeId) {
        careerService.deleteCareerStep(id);
        return "redirect:/employees/" + employeeId + "/career";
    }

    @GetMapping("employees/{employeeId}/career/edit/{id}")
    public String editCareerPage(@PathVariable Long id,
                                 Model model) {
        EditCareerDTO editCareerDTO = careerService.getInfoForEditingCareerStep(id);
        model.addAttribute("careerStep", editCareerDTO.getCareerStep());
        model.addAttribute("positions", editCareerDTO.getPositions());
        return "career/edit";
    }

    @PostMapping("employees/{employeeId}/career/edit/{id}")
    public String editCareerStep(@ModelAttribute("careerStep") CareerStepSaveDTO careerStep) {
        careerService.editCareerStep(careerStep);
        return "redirect:/employees/" + careerStep.getEmployeeId() + "/career";
    }
}
