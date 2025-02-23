package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.model.AppointmentInfoDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.CareerStepGetDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.CareerStepSaveDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.DismissDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.EditCareerDTO;
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
