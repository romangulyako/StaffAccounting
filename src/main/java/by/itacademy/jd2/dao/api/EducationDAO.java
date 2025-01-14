package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.EducationEntity;

import java.io.Serializable;
import java.util.List;

public interface EducationDAO extends IDAO<EducationEntity> {
    List<EducationEntity> getEducationByEmployeeId(Serializable employeeId, Integer pageSize, Integer pageNumber);
    Long getEducationCountByEmployeeId(Serializable employeeId);
}
