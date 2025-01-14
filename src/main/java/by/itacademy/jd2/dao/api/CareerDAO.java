package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.CareerStepEntity;

import java.io.Serializable;
import java.util.List;

public interface CareerDAO extends IDAO<CareerStepEntity> {
    List<CareerStepEntity> getCareerByEmployeeIdAndPage(Serializable employeeId, Integer pageSize, Integer pageNumber);
    List<CareerStepEntity> getPositionHistoryByPage(Serializable positionId, Integer pageSize, Integer pageNumber);
    Long getCareerStepCountByEmployeeId(Serializable employeeId);
    Long getCareerStepCountByPositionId(Serializable positionId);
}
