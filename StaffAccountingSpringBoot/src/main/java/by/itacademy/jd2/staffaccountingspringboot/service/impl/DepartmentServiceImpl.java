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
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @Override
    public void saveOrUpdateDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = Converter.toEntity(departmentDTO, DepartmentEntity.class);
        departmentRepository.save(departmentEntity);
        LOGGER.info("Department saved successfully. ID={}", departmentEntity.getId());
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
        LOGGER.info("Department with id={} deleted successfully", id);
    }

    @Override
    public DepartmentDTO getDepartment(Long id) {
        DepartmentEntity departmentEntity = this.findDepartmentById(id);
        LOGGER.info("Successfully fetched department with id={} from database", id);
        return Converter.toDto(departmentEntity, DepartmentDTO.class);
    }

    @Override
    public DepartmentInfoDTO getDepartmentInfo(Long id, Boolean isActual, int page, int size) {
        DepartmentEntity departmentEntity = this.findDepartmentById(id);
        Page<PositionEntity> positionsPage =
                positionRepository.findAllByDepartmentIdAndIsActual(id, isActual, PageRequest.of(page, size));
        LOGGER.info("Successfully fetched department's info with id={}", id);

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
        Page<DepartmentEntity> departments = departmentRepository.findAllByIsActual(PageRequest.of(page, size), isActual);
        if (departments.isEmpty()) {
            LOGGER.warn("No departments found for the provided parameters: page={}, size={}",
                    page, size);
        } else {
            LOGGER.info("Successfully fetched {} departments from database", departments.getContent().size());
        }

        return departments.map(entity -> Converter.toDto(entity, DepartmentDTO.class));
    }

    @Override
    public void reduceDepartment(Long id) {
        this.changeDepartmentStatus(id, false);
        LOGGER.info("Successfully reduced department with id={}", id);
    }

    @Override
    public void restoreDepartment(Long id) {
        this.changeDepartmentStatus(id, true);
        LOGGER.info("Successfully restored department with id={}", id);
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
                    LOGGER.error("Department with id={} not found", id);
                    return new EntityNotFoundException("Department with id=" + id + " not found");
                });
    }
}
