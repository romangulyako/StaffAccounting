package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.dto.DepartmentItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeesPageDTO;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.DepartmentRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.EmployeeRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.EmployeeService;
import by.itacademy.jd2.staffaccountingspringboot.utils.Constant;
import by.itacademy.jd2.staffaccountingspringboot.utils.EmployeeUtils;
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
        EmployeeEntity entity = EmployeeUtils.findById(id);
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
        EmployeeEntity employee = EmployeeUtils.findById(id);
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
}
