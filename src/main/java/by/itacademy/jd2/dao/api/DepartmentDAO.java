package by.itacademy.jd2.dao.api;

import by.itacademy.jd2.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentDAO extends IDAO<DepartmentEntity> {
    List<DepartmentEntity> getDepartmentsByActualAndPage(Boolean isActual, Integer pageSize, Integer pageNumber);
    Long getDepartmentsCountByActual(Boolean isActual);
}
