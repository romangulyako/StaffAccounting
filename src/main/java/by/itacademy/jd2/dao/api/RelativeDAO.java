package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.RelativeEntity;

import java.io.Serializable;
import java.util.List;

public interface RelativeDAO extends IDAO<RelativeEntity> {
    List<RelativeEntity> getRelativesByEmployeeIdAndPage(Serializable employeeId,
                                                         Integer pageSize,
                                                         Integer pageNumber);
    Long getRelativesCountByEmployeeId(Serializable employeeId);
}
