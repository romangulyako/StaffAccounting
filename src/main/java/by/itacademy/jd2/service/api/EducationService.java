package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.EducationDTO;

import java.io.Serializable;
import java.util.List;

public interface EducationService extends Service {
    void addEducation(EducationDTO educationDTO);
    void updateEducation(EducationDTO educationDTO);
    void deleteEducation(Serializable id);
    EducationDTO getEducation(Serializable id);
    List<EducationDTO> getEducationsByEmployeeId(Serializable employeeId);
}
