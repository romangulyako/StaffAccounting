package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.service.PageInfo;

import java.io.Serializable;

public interface DepartmentService extends Service {
    void addDepartment(DepartmentDTO departmentDTO);
    void updateDepartment(DepartmentDTO departmentDTO);
    void deleteDepartment(Serializable id);
    DepartmentDTO getDepartment(Serializable id);
    PageInfo<DepartmentDTO> getDepartmentsByPage(Integer pageNumber, Integer pageSize);
}
