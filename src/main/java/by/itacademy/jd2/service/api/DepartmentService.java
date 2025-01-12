package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.DepartmentDTO;

import java.io.Serializable;
import java.util.List;

public interface DepartmentService extends Service {
    void addDepartment(DepartmentDTO departmentDTO);
    void updateDepartment(DepartmentDTO departmentDTO);
    void deleteDepartment(Serializable id);
    DepartmentDTO getDepartment(Serializable id);
    List<DepartmentDTO> getAllDepartments();
    Integer getTotalPages(Integer pageSize);
    List<DepartmentDTO> getDepartmentsByPage(Integer page, Integer pageSize);
}
