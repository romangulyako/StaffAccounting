package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.MaritalStatusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MaritalStatusService {
        void saveOrUpdateMaritalStatus(MaritalStatusDTO maritalStatusDTO);
        void deleteMaritalStatus(Long id);
        MaritalStatusDTO getMaritalStatus(Long id);
        Page<MaritalStatusDTO> getAllMaritalStatuses(Long employeeId, Pageable pageable);
}
