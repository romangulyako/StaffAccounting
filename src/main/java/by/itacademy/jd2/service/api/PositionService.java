package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.dto.PositionItemDTO;
import by.itacademy.jd2.service.PageInfo;

import java.io.Serializable;
import java.util.List;

public interface PositionService extends Service {
    void addPosition(PositionDTO positionDTO);
    void updatePosition(PositionDTO positionDTO);
    void deletePosition(Serializable id);
    List<PositionItemDTO> getAllPositionItems();
    PositionDTO getPosition(Serializable id);
    PageInfo<PositionDTO> getPositionsByDepartmentIdAndActualAndPage(Serializable departmentId,
                                                                     Boolean isActual,
                                                                     Integer pageNumber,
                                                                     Integer pageSize);
    void reducePosition(Serializable id);
    void restorePosition(Serializable id);
}
