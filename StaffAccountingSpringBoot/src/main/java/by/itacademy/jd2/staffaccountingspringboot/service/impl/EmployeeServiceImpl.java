package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.EmployeeRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    @Override
    public void saveOrUpdateEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity =
                Converter.toEntity(employeeDTO, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);
        LOGGER.info("Employee saved successfully. ID={}", employeeEntity.getId());
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        LOGGER.info("Employee with id={} deleted successfully", id);
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        EmployeeEntity entity = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("Employee with id={} not found", id);
                    return new EntityNotFoundException("User with id=" + id + " not found");
                });
        LOGGER.info("Successfully fetched employee with id={} from database", id);
        return Converter.toDto(entity, EmployeeDTO.class);
    }

    @Override
    public Page<EmployeeDTO> getEmployees(EmployeeFilterData filterData,
                                          Boolean isFired,
                                          Pageable pageable) {
        Page<EmployeeEntity> pageEntities = employeeRepository.findEmployeesByFilterData(filterData, isFired, pageable);

        if (pageEntities.getContent().isEmpty()) {
            LOGGER.warn("No employees found for the provided parameters: page={}, size={}",
                    pageable.getPageNumber(), pageEntities.getSize());
        } else {
            LOGGER.info("Successfully fetched {} employees from the database", pageEntities.getContent().size());
        }

        return pageEntities.map(entity -> Converter.toDto(entity, EmployeeDTO.class));
    }

    @Override
    public List<EmployeeItemDTO> getAllEmployeeItems(boolean isCurrentOnly) {
        List<EmployeeEntity> entities;
        if (isCurrentOnly) {
            entities = employeeRepository.findAllByIsFired(false);
        } else {
            entities = employeeRepository.findAll();
        }

        return entities.stream()
                .map(entity -> Converter.toDto(entity, EmployeeItemDTO.class))
                .collect(Collectors.toList());
    }
}
