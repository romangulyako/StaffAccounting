package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.RelativeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RelativeService {
    void saveOrUpdateRelative(RelativeDTO relativeDTO);
    void deleteRelative(Long id);
    RelativeDTO getRelative(Long id);
    Page<RelativeDTO> getRelatives(Long employeeId, Pageable pageable);
}
