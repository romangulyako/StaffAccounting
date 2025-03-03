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
import by.itacademy.jd2.staffaccountingspringboot.utils.Constant;
import by.itacademy.jd2.staffaccountingspringboot.utils.LocaleUtils;
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
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @Override
    public void saveOrUpdateDepartment(DepartmentDTO departmentDTO) {
        LOGGER.debug(Constant.ATTEMPT_TO_SAVE_DEPARTMENT, departmentDTO.getName());
        DepartmentEntity departmentEntity = Converter.toEntity(departmentDTO, DepartmentEntity.class);
        departmentRepository.save(departmentEntity);
        LOGGER.info(Constant.SAVE_DEPARTMENT_SUCCESS, departmentEntity.getId());
    }

    @Override
    public void deleteDepartment(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_DELETE_DEPARTMENT, id);
        departmentRepository.deleteById(id);
        LOGGER.info(Constant.DELETE_DEPARTMENT_SUCCESS, id);
    }

    @Transactional(readOnly = true)
    @Override
    public DepartmentDTO getDepartment(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_FETCH_DEPARTMENT, id);
        DepartmentEntity departmentEntity = this.findDepartmentById(id);
        LOGGER.info(Constant.DEPARTMENT_FOUND_SUCCESS, id);
        return Converter.toDto(departmentEntity, DepartmentDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public DepartmentInfoDTO getDepartmentInfo(Long id, Boolean isActual, int page, int size) {
        LOGGER.debug(Constant.ATTEMPT_TO_FETCH_INFO_OF_DEPARTMENT, id);
        DepartmentEntity departmentEntity = this.findDepartmentById(id);
        Page<PositionEntity> positionsPage =
                positionRepository.findAllByDepartmentIdAndIsActual(id, isActual, PageRequest.of(page, size));
        LOGGER.info(Constant.FETCHED_DEPARTMENT_INFO_SUCCESS, id);

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

    @Transactional(readOnly = true)
    @Override
    public Page<DepartmentDTO> getDepartments(int page, int size, Boolean isActual) {
        LOGGER.debug(Constant.ATTEMPT_TO_FETCH_DEPARTMENTS_LIST);
        Page<DepartmentEntity> departments =
                departmentRepository.findAllByIsActual(PageRequest.of(page, size), isActual);
        if (departments.isEmpty()) {
            LOGGER.warn(Constant.NO_DEPARTMENTS_FOUND, page, size);
        } else {
            LOGGER.info(Constant.DEPARTMENTS_LIST_FOUND_SUCCESS, departments.getContent().size());
        }

        return departments.map(entity -> Converter.toDto(entity, DepartmentDTO.class));
    }

    @Override
    public void reduceDepartment(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_REDUCE_DEPARTMENT, id);
        this.updateDepartmentStatus(id, false);
        LOGGER.info(Constant.REDUCE_DEPARTMENT_SUCCESS, id);
    }

    @Override
    public void restoreDepartment(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_RESTORE_DEPARTMENT, id);
        this.updateDepartmentStatus(id, true);
        LOGGER.info(Constant.RESTORE_DEPARTMENT_SUCCESS, id);
    }

    private void updateDepartmentStatus(Long id, boolean status) {
        DepartmentEntity departmentEntity = this.findDepartmentById(id);
        departmentEntity.setIsActual(status);
        departmentEntity.getPositions()
                .forEach(position -> position.setIsActual(status));
        departmentRepository.save(departmentEntity);
    }

    private DepartmentEntity findDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error(Constant.DEPARTMENT_NOT_FOUND, id);
                    return new EntityNotFoundException(LocaleUtils
                            .getMessage(Constant.DEPARTMENT_NOT_FOUND_EXCEPTION_MESSAGE_KEY) + id);
                });
    }
}
