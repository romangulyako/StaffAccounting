package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.RelativeEntity;

import java.util.List;

public interface RelativeDAO extends IDAO<RelativeEntity> {
    List<RelativeEntity> getRelativesByEmployeeId(Long employeeId);
}
