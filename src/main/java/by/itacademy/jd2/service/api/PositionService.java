package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.dto.PositionItemDTO;

import java.io.Serializable;
import java.util.List;

public interface PositionService extends Service {
    void addPosition(PositionDTO positionDTO);
    void updatePosition(PositionDTO positionDTO);
    void deletePosition(Serializable id);
    List<PositionItemDTO> getAllPositionItems();
    PositionDTO getPosition(Serializable id);
    List<PositionDTO> getPositionsByDepartmentId(Serializable departmentId);
}
