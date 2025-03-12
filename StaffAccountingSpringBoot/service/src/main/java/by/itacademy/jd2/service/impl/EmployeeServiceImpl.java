package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dto.DepartmentItemDTO;
import by.itacademy.jd2.dto.EmployeesPageDTO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.repository.custom.EmployeeFilterData;
import by.itacademy.jd2.dto.EmployeeItemDTO;
import by.itacademy.jd2.repository.DepartmentRepository;
import by.itacademy.jd2.repository.EmployeeRepository;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.utils.Constant;
import by.itacademy.jd2.utils.LocaleUtils;
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
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeDTO saveOrUpdateEmployee(EmployeeDTO employeeDTO) {
        LOGGER.debug(Constant.ATTEMPT_TO_SAVE_EMPLOYEE,
                employeeDTO.getPersonData().getName(), employeeDTO.getPersonData().getSurname());
        EmployeeEntity employeeEntity = employeeRepository.save(Converter.toEntity(employeeDTO, EmployeeEntity.class));
        LOGGER.info(Constant.SAVE_EMPLOYEE_SUCCESS, employeeEntity.getId());

        return Converter.toDto(employeeEntity, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_DELETE_EMPLOYEE, id);
        employeeRepository.deleteById(id);
        LOGGER.info(Constant.DELETE_EMPLOYEE_SUCCESS, id);
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeDTO getEmployee(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_FETCH_EMPLOYEE, id);
        EmployeeEntity entity = this.findById(id);
        LOGGER.info(Constant.FETCHED_EMPLOYEE_SUCCESS, id);

        return Converter.toDto(entity, EmployeeDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeesPageDTO getEmployeesPage(EmployeeFilterData filterData,
                                             Boolean isFired, int page, int size) {
        LOGGER.debug(Constant.ATTEMPT_TO_FETCH_EMPLOYEE_LIST);
        List<DepartmentItemDTO> departments = departmentRepository.findAllByIsActualTrue().stream()
                .map(entity -> Converter.toDto(entity, DepartmentItemDTO.class))
                .toList();
        Page<EmployeeEntity> pageEntities =
                employeeRepository.findEmployeesByFilterData(filterData, isFired, PageRequest.of(page, size));

        if (pageEntities.getContent().isEmpty()) {
            LOGGER.warn(Constant.NOT_FOUND_EMPLOYEE_LIST,
                    page, size);
        } else {
            LOGGER.info(Constant.FOUND_EMPLOYEE_LIST_SUCCESS, pageEntities.getContent().size());
        }

        return EmployeesPageDTO.builder()
                .employees(pageEntities.map(entity -> Converter.toDto(entity, EmployeeDTO.class)))
                .departments(departments)
                .build();
    }

    @Override
    public void returnToCurrent(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_RETURN_EMPLOYEE, id);
        EmployeeEntity employee = this.findById(id);
        employee.setIsFired(false);
        employeeRepository.save(employee);
        LOGGER.info(Constant.RETURN_EMPLOYEE_TO_CURRENT_SUCCESS, id);
    }

    @Override
    public List<EmployeeItemDTO> getCurrentEmployeeItems() {
        return employeeRepository.findAllByIsFiredFalse()
                .stream().map(entity -> Converter.toDto(entity, EmployeeItemDTO.class))
                .toList();
    }

    private EmployeeEntity findById(final Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn(Constant.EMPLOYEE_NOT_FOUND, id);
                    return new EntityNotFoundException(LocaleUtils
                            .getMessage(Constant.EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE_KEY) + id);
                });
    }
}
