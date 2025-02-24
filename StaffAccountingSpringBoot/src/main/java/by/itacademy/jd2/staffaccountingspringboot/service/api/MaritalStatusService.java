package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.MaritalStatusDTO;
import org.springframework.data.domain.Page;

public interface MaritalStatusService {
        void saveOrUpdateMaritalStatus(MaritalStatusDTO maritalStatusDTO);
        void deleteMaritalStatus(Long id);
        MaritalStatusDTO getMaritalStatus(Long id);
        Page<MaritalStatusDTO> getAllMaritalStatuses(Long employeeId, int page, int size);
}
