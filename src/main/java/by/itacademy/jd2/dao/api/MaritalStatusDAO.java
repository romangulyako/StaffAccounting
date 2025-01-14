package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.MaritalStatusEntity;

import java.io.Serializable;
import java.util.List;

public interface MaritalStatusDAO extends IDAO<MaritalStatusEntity> {
    List<MaritalStatusEntity> getMaritalStatusesByEmployeeIdAndPage(Serializable employeeId,
                                                                    Integer pageSize,
                                                                    Integer pageNumber);
    Long getMaritalStatusesCountByEmployeeId(Serializable employeeId);
}
