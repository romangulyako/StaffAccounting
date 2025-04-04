package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.PositionEntity;

import java.io.Serializable;
import java.util.List;

public interface PositionDAO extends IDAO<PositionEntity> {
    List<PositionEntity> getPositionsByDepartmentIdAndActualAndPage(Serializable departmentId,
                                                                    Boolean isActual,
                                                                    Integer pageSize,
                                                                    Integer pageNumber);
    Long getPositionsCountByDepartmentIdAndActual(Serializable departmentId, Boolean isActual);
}
