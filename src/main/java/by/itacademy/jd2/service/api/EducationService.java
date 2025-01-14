package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.service.PageInfo;

import java.io.Serializable;

public interface EducationService extends Service {
    void addEducation(EducationDTO educationDTO);
    void updateEducation(EducationDTO educationDTO);
    void deleteEducation(Serializable id);
    EducationDTO getEducation(Serializable id);
    PageInfo<EducationDTO> getEducationsByEmployeeIdAndPage(Serializable employeeId,
                                                            Integer pageNumber,
                                                            Integer pageSize);
}
