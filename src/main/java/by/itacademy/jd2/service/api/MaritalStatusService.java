package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.MaritalStatusDTO;

import java.io.Serializable;
import java.util.List;

public interface MaritalStatusService extends Service {
    void addMaritalStatus(MaritalStatusDTO maritalStatusDTO);
    void updateMaritalStatus(MaritalStatusDTO maritalStatusDTO);
    void deleteMaritalStatus(Serializable id);
    MaritalStatusDTO getMaritalStatus(Serializable id);
    List<MaritalStatusDTO> getAllMaritalStatuses(Long employeeId);
}
