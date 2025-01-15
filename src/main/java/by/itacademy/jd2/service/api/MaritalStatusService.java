package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.service.PageInfo;

import java.io.Serializable;

public interface MaritalStatusService extends Service {
    void addMaritalStatus(MaritalStatusDTO maritalStatusDTO);
    void updateMaritalStatus(MaritalStatusDTO maritalStatusDTO);
    void deleteMaritalStatus(Serializable id);
    MaritalStatusDTO getMaritalStatus(Serializable id);
    PageInfo<MaritalStatusDTO> getMaritalStatusesByEmployeeAndPage(Serializable employeeId,
                                                                   Integer pageNumber,
                                                                   Integer pageSize);
}
