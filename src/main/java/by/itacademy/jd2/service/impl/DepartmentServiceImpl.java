package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.DepartmentDAO;
import by.itacademy.jd2.dao.impl.DepartmentDaoImpl;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.dto.DepartmentItemDTO;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.utils.PaginatorUtil;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {
    private static final boolean DEFAULT_IS_ACTUAL = true;
    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImpl() {
        this.departmentDAO = new DepartmentDaoImpl();
    }

    @Override
    public void addDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity entity = Converter.toEntity(departmentDTO, DepartmentEntity.class);
        departmentDAO.save(entity);
    }

    @Override
    public void updateDepartment(DepartmentDTO departmentDTO) {
        if (departmentDTO != null) {
            DepartmentEntity oldEntity = departmentDAO.get(departmentDTO.getId());
            DepartmentEntity newEntity = Converter.toEntity(departmentDTO, DepartmentEntity.class);
            newEntity.setPositions(oldEntity.getPositions());
            departmentDAO.update(newEntity, oldEntity.getId());
        }
    }

    @Override
    public void deleteDepartment(Serializable id) {
        departmentDAO.delete(id);
    }

    @Override
    public DepartmentDTO getDepartment(Serializable id) {
        return Converter.toDto(departmentDAO.get(id), DepartmentDTO.class);
    }

    @Override
    public PageInfo<DepartmentDTO> getDepartmentsByActualAndPage(Boolean isActual,
                                                                 Integer pageNumber,
                                                                 Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        if (isActual == null) {
            isActual = DEFAULT_IS_ACTUAL;
        }
        List<DepartmentDTO> departments = departmentDAO.getDepartmentsByActualAndPage(isActual, pageSize, pageNumber)
                .stream()
                .map(entity -> Converter.toDto(entity, DepartmentDTO.class))
                .collect(Collectors.toList());
        Long departmentCount = departmentDAO.getDepartmentsCountByActual(isActual);

        return new PageInfo<>(departments, pageNumber, pageSize, departmentCount);
    }

    @Override
    public List<DepartmentItemDTO> getDepartmentItems() {
        return departmentDAO.getAll().stream()
                .map(entity -> Converter.toDto(entity, DepartmentItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void reduceDepartment(Serializable id) {
        this.changeDepartmentStatus(id, false);
    }

    @Override
    public void restoreDepartment(Serializable id) {
        this.changeDepartmentStatus(id, true);
    }

    @Override
    public void closeDao() {
        departmentDAO.close();
    }

    private void changeDepartmentStatus(Serializable id, boolean status) {
        DepartmentEntity departmentEntity = departmentDAO.get(id);
        departmentEntity.setIsActual(status);
        departmentEntity.getPositions()
                .forEach(position -> position.setIsActual(status));
        departmentDAO.update(departmentEntity, id);
    }
}
