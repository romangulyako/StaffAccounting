package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.DepartmentEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.PositionEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.DepartmentDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.DepartmentInfoDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PositionDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.DepartmentRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.PositionRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private static final String SAVE_SUCCESS_LOG = "Department saved successfully. ID={}";
    private static final String DELETE_SUCCESS_LOG = "Department with ID={} deleted successfully";
    private static final String DEPARTMENT_FOUND_SUCCESS_LOG =
            "Successfully fetched department with ID={} from database";
    private static final String FETCHED_DEPARTMENT_INFO_SUCCESS = "Successfully fetched department's info with ID={}";
    private static final String NO_DEPARTMENTS_FOUND_LOG =
            "No departments found for the provided parameters: page={}, size={}";
    private static final String DEPARTMENTS_FOUND_SUCCESS_LOG = "Successfully fetched {} departments from database";
    private static final String REDUCE_SUCCESS_LOG = "Successfully reduced department with ID={}";
    private static final String RESTORE_SUCCESS_LOG = "Successfully restored department with ID={}";
    private static final String NOT_FOUND_LOG = "Department with ID={} not found";
    private static final String NOT_FOUND_EXCEPTION = "Department not found. ID=";
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @Override
    public void saveOrUpdateDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = Converter.toEntity(departmentDTO, DepartmentEntity.class);
        departmentRepository.save(departmentEntity);
        LOGGER.info(SAVE_SUCCESS_LOG, departmentEntity.getId());
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
        LOGGER.info(DELETE_SUCCESS_LOG, id);
    }

    @Override
    public DepartmentDTO getDepartment(Long id) {
        DepartmentEntity departmentEntity = this.findDepartmentById(id);
        LOGGER.info(DEPARTMENT_FOUND_SUCCESS_LOG, id);
        return Converter.toDto(departmentEntity, DepartmentDTO.class);
    }

    @Override
    public DepartmentInfoDTO getDepartmentInfo(Long id, Boolean isActual, int page, int size) {
        DepartmentEntity departmentEntity = this.findDepartmentById(id);
        Page<PositionEntity> positionsPage =
                positionRepository.findAllByDepartmentIdAndIsActual(id, isActual, PageRequest.of(page, size));
        LOGGER.info(FETCHED_DEPARTMENT_INFO_SUCCESS, id);

        return DepartmentInfoDTO.builder()
                .department(Converter.toDto(departmentEntity, DepartmentDTO.class))
                .positions(positionsPage.getContent().stream()
                        .map(entity -> Converter.toDto(entity, PositionDTO.class))
                        .collect(Collectors.toList()))
                .page(page)
                .size(size)
                .isActual(isActual)
                .totalPages(positionsPage.getTotalPages())
                .build();
    }

    @Override
    public Page<DepartmentDTO> getDepartments(int page, int size, Boolean isActual) {
        Page<DepartmentEntity> departments =
                departmentRepository.findAllByIsActual(PageRequest.of(page, size), isActual);
        if (departments.isEmpty()) {
            LOGGER.warn(NO_DEPARTMENTS_FOUND_LOG, page, size);
        } else {
            LOGGER.info(DEPARTMENTS_FOUND_SUCCESS_LOG, departments.getContent().size());
        }

        return departments.map(entity -> Converter.toDto(entity, DepartmentDTO.class));
    }

    @Override
    public void reduceDepartment(Long id) {
        this.changeDepartmentStatus(id, false);
        LOGGER.info(REDUCE_SUCCESS_LOG, id);
    }

    @Override
    public void restoreDepartment(Long id) {
        this.changeDepartmentStatus(id, true);
        LOGGER.info(RESTORE_SUCCESS_LOG, id);
    }

    private void changeDepartmentStatus(Long id, boolean status) {
        DepartmentEntity departmentEntity = this.findDepartmentById(id);
        departmentEntity.setIsActual(status);
        departmentEntity.getPositions()
                .forEach(position -> position.setIsActual(status));
        departmentRepository.save(departmentEntity);
    }

    private DepartmentEntity findDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error(NOT_FOUND_LOG, id);
                    return new EntityNotFoundException(NOT_FOUND_EXCEPTION + id);
                });
    }
}
