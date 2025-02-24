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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private static final String SAVE_SUCCESS_LOG = "Employee saved successfully. ID={}";
    private static final String DELETE_SUCCESS_LOG = "Employee with ID={} deleted successfully";
    private static final String FOUND_SUCCESS_LOG = "Successfully fetched employee with ID={} from database";
    private static final String NOT_FOUND_LIST_LOG = "No employees found for the provided parameters: page={}, size={}";
    private static final String FOUND_LIST_SUCCESS_LOG = "Successfully fetched {} employees from the database";
    private static final String RETURN_TO_CURRENT_SUCCESS_LOG = "Employee with ID={} returned to current successfully";
    private static final String NOT_FOUND_LOG = "Employee with ID={} not found";
    private static final String NOT_FOUND_EXCEPTION = "Employee not found. ID=";
    private final EmployeeRepository employeeRepository;

    @Override
    public void saveOrUpdateEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity =
                Converter.toEntity(employeeDTO, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);
        LOGGER.info(SAVE_SUCCESS_LOG, employeeEntity.getId());
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        LOGGER.info(DELETE_SUCCESS_LOG, id);
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        EmployeeEntity entity = this.findById(id);
        LOGGER.info(FOUND_SUCCESS_LOG, id);
        return Converter.toDto(entity, EmployeeDTO.class);
    }

    @Override
    public Page<EmployeeDTO> getEmployees(EmployeeFilterData filterData,
                                          Boolean isFired, int page, int size) {
        Page<EmployeeEntity> pageEntities =
                employeeRepository.findEmployeesByFilterData(filterData, isFired, PageRequest.of(page, size));

        if (pageEntities.getContent().isEmpty()) {
            LOGGER.warn(NOT_FOUND_LIST_LOG,
                    page, size);
        } else {
            LOGGER.info(FOUND_LIST_SUCCESS_LOG, pageEntities.getContent().size());
        }

        return pageEntities.map(entity -> Converter.toDto(entity, EmployeeDTO.class));
    }

    @Override
    public void returnToCurrent(Long id) {
        EmployeeEntity employee = this.findById(id);
        employee.setIsFired(false);
        employeeRepository.save(employee);
        LOGGER.info(RETURN_TO_CURRENT_SUCCESS_LOG, id);
    }

    @Override
    public List<EmployeeItemDTO> getCurrentEmployeeItems() {
        return employeeRepository.findAllByIsFiredFalse()
                .stream().map(entity -> Converter.toDto(entity, EmployeeItemDTO.class))
                .toList();
    }

    private EmployeeEntity findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn(NOT_FOUND_LOG, id);
                    return new EntityNotFoundException(NOT_FOUND_EXCEPTION + id);
                });
    }
}
