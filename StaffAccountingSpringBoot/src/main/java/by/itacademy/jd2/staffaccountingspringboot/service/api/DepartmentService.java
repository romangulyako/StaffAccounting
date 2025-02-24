package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.DepartmentDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.DepartmentInfoDTO;
import org.springframework.data.domain.Page;

public interface DepartmentService {
    void saveOrUpdateDepartment(DepartmentDTO departmentDTO);
    void deleteDepartment(Long id);
    DepartmentDTO getDepartment(Long id);
    DepartmentInfoDTO getDepartmentInfo(Long id, Boolean isActual, int page, int size);
    Page<DepartmentDTO> getDepartments(int page, int size, Boolean isActual);
    void reduceDepartment(Long id);
    void restoreDepartment(Long id);
}
