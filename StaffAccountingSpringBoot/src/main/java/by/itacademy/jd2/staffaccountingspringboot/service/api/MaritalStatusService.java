package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.model.MaritalStatusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MaritalStatusService {
        void addMaritalStatus(MaritalStatusDTO maritalStatusDTO);
        void editMaritalStatus(MaritalStatusDTO maritalStatusDTO);
        void deleteMaritalStatus(Long id);
        MaritalStatusDTO getMaritalStatus(Long id);
        Page<MaritalStatusDTO> getAllMaritalStatuses(Long employeeId, Pageable pageable);
}
