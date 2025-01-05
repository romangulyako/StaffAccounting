package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.PositionDTO;

import java.io.Serializable;
import java.util.List;

public interface PositionService extends Service {
    void addPosition(PositionDTO positionDTO);
    void updatePosition(PositionDTO positionDTO);
    void deletePosition(Serializable id);
    PositionDTO getPosition(Serializable id);
    List<PositionDTO> getPositionsByDepartmentId(Serializable departmentId);
}
