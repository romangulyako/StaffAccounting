package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.repository.MaritalStatusEntity;

import java.util.List;

public interface MaritalStatusDAO extends IDAO<MaritalStatusEntity> {
    List<MaritalStatusEntity> getMaritalStatusesByEmployeeId(Long employeeId);
}
