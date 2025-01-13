package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.CareerStepEntity;

import java.io.Serializable;
import java.util.List;

public interface CareerDAO extends IDAO<CareerStepEntity> {
    List<CareerStepEntity> getCareerByEmployeeId(Serializable employeeId);
    List<CareerStepEntity> getPositionHistory(Serializable positionId);
    List<CareerStepEntity> getCurrentCareerStepOfEmployee(Serializable employeeId);
}
