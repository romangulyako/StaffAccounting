package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeItemDTO;
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
    public void returnToCurrent(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("Employee with id={} not found", id);
                    return new EntityNotFoundException("User with id=" + id + " not found");
                });
        employee.setIsFired(false);
        employeeRepository.save(employee);
        LOGGER.info("Employee with id={} returned to current successfully", id);
    }

    @Override
    public List<EmployeeItemDTO> getCurrentEmployeeItems() {
        return employeeRepository.findAllByIsFiredFalse()
                .stream().map(entity -> Converter.toDto(entity, EmployeeItemDTO.class))
                .toList();
    }
}
