package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.DepartmentDAO;
import by.itacademy.jd2.dao.impl.DepartmentDaoImpl;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.service.api.DepartmentService;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDAO departmentDAO;
    private final Converter converter;

    public DepartmentServiceImpl() {
        this.departmentDAO = new DepartmentDaoImpl();
        this.converter = Converter.getConverter();
    }

    @Override
    public void addDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity entity = converter.toEntity(departmentDTO, DepartmentEntity.class);
        departmentDAO.save(entity);
        departmentDTO.setId(entity.getId());
    }

    @Override
    public void updateDepartment(DepartmentDTO departmentDTO) {
        if (departmentDTO != null) {
            DepartmentEntity oldEntity = departmentDAO.get(departmentDTO.getId());
            DepartmentEntity newEntity = converter.toEntity(departmentDTO, DepartmentEntity.class);
            if (Objects.equals(oldEntity.getId(), newEntity.getId())) {
                departmentDAO.update(newEntity, oldEntity.getId());
            }
        }

    }

    @Override
    public void deleteDepartment(Serializable id) {
        departmentDAO.delete(id);
    }

    @Override
    public DepartmentDTO getDepartment(Serializable id) {
        return converter.toDto(departmentDAO.get(id), DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentDAO.getAll().stream()
                .map(entity -> converter.toDto(entity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void closeDao() {
        departmentDAO.close();
    }
}
