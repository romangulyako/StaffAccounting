package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.dto.DepartmentItemDTO;
import by.itacademy.jd2.service.PageInfo;

import java.io.Serializable;
import java.util.List;

public interface DepartmentService extends Service {
    void addDepartment(DepartmentDTO departmentDTO);
    void updateDepartment(DepartmentDTO departmentDTO);
    void deleteDepartment(Serializable id);
    DepartmentDTO getDepartment(Serializable id);
    List<DepartmentItemDTO> getDepartmentItems();
    PageInfo<DepartmentDTO> getDepartmentsByActualAndPage(Boolean isActual, Integer pageNumber, Integer pageSize);
    void reduceDepartment(Serializable id);
    void restoreDepartment(Serializable id);
}
