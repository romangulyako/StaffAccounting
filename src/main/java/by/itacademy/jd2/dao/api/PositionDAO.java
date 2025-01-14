package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.PositionEntity;

import java.io.Serializable;
import java.util.List;

public interface PositionDAO extends IDAO<PositionEntity> {
    List<PositionEntity> getPositionsByDepartmentIdAndPage(Serializable departmentId,
                                                           Integer pageSize,
                                                           Integer pageNumber);
    Long getPositionsCountByDepartmentId(Serializable departmentId);
}
