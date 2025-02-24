package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.EducationDTO;
import org.springframework.data.domain.Page;

public interface EducationService {
    void saveOrUpdateEducation(EducationDTO educationDTO);
    void deleteEducation(Long id);
    EducationDTO getEducation(Long id);
    Page<EducationDTO> getEducationsByEmployeeId(Long employeeId, int page, int size);
}
