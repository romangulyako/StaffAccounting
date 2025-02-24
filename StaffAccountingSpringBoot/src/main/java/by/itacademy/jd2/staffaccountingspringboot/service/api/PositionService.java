package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.PositionDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PositionHistoryDTO;
import org.springframework.data.domain.Page;

public interface PositionService {
    void saveOrUpdatePosition(PositionDTO positionDTO);
    void deletePosition(Long id);
    PositionDTO getPositionById(Long id);
    Page<PositionDTO> getPositionsByDepartmentAndActual(Long departmentId,
                                                        Boolean isActual,
                                                        int page,
                                                        int size);
    void reducePosition(Long id);
    void restorePosition(Long id);
    Page<PositionHistoryDTO> getPositionHistory(Long id, int page, int size);
}
