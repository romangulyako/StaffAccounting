package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.AppointmentInfoDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.CareerStepGetDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.CareerStepSaveDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.DismissDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EditCareerDTO;
import org.springframework.data.domain.Page;

public interface CareerService {
    void appointEmployee(CareerStepSaveDTO careerStepDTO);
    void dismissEmployee(DismissDTO dismissDTO);
    void editCareerStep(CareerStepSaveDTO careerStepDTO);
    void deleteCareerStep(Long id);
    EditCareerDTO getInfoForEditingCareerStep(Long id);
    Page<CareerStepGetDTO> getEmployeesCareer(Long employeeId, int page, int size);
    AppointmentInfoDTO getAppointmentInfo();
}
