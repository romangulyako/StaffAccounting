package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.EmployeeFilterData;
import by.itacademy.jd2.dto.EmployeeItemDTO;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.PassportEntity;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.utils.PaginatorUtil;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private static final Boolean DEFAULT_IS_FIRED = false;
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl() {
        employeeDAO = new EmployeeDaoImpl();
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity =
                Converter.toEntity(employeeDTO, EmployeeEntity.class);
        employeeDAO.save(employeeEntity);

        LOGGER.info("Added employee with id={}", employeeEntity.getId());
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO != null) {
            EmployeeEntity newEmployee = Converter.toEntity(employeeDTO, EmployeeEntity.class);
            EmployeeEntity oldEmployee = employeeDAO.get(employeeDTO.getId());
            newEmployee.setPassport(oldEmployee.getPassport());
            newEmployee.setFired(oldEmployee.isFired());
            employeeDAO.update(newEmployee, newEmployee.getId());

            LOGGER.info("Updated employee with id={}", newEmployee.getId());
        }
    }

    @Override
    public void deleteEmployee(Serializable id) {
        employeeDAO.delete(id);

        LOGGER.info("Deleted employee with id={}", id);
    }

    @Override
    public EmployeeDTO getEmployee(Serializable id) {
        EmployeeDTO employeeDTO = Converter.toDto(employeeDAO.get(id), EmployeeDTO.class);
        if (employeeDTO != null) {
            LOGGER.info("Found employee with id={}", id);
        }

        return employeeDTO;
    }

    @Override
    public PageInfo<EmployeeDTO> getEmployeesByFiredAndPage(EmployeeFilterData filterData,
                                                            Boolean isFired,
                                                            Integer pageSize,
                                                            Integer pageNumber) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        if (isFired == null) {
            isFired = DEFAULT_IS_FIRED;
        }

        List<EmployeeDTO> employees = employeeDAO.getEmployeesByFiredAndPage(filterData, isFired, pageSize, pageNumber)
                .stream()
                .map(entity -> Converter.toDto(entity, EmployeeDTO.class))
                .collect(Collectors.toList());
        Long employeesCount = employeeDAO.getEmployeesCount(filterData, isFired);

        LOGGER.info("Found {} from {} employees", employees.size(), employeesCount);

        return new PageInfo<>(employees, pageNumber, pageSize, employeesCount);
    }

    @Override
    public List<EmployeeItemDTO> getAllEmployeeItems(boolean isCurrentOnly) {
        List<EmployeeEntity> allEmployees = employeeDAO.getAll();
        if (isCurrentOnly) {
            allEmployees.removeIf(EmployeeEntity::isFired);
        }

        List<EmployeeItemDTO> items = allEmployees.stream()
                .map(entity -> Converter.toDto(entity, EmployeeItemDTO.class))
                .collect(Collectors.toList());

        LOGGER.info("Found {} employeeItems", items.size());

        return items;
    }

    @Override
    public void addPassport(PassportDTO passportDTO) {
        EmployeeEntity employeeEntity = employeeDAO.get(passportDTO.getId());
        PassportEntity passportEntity = Converter.toEntity(passportDTO, PassportEntity.class);
        employeeEntity.setPassport(passportEntity);
        passportEntity.setEmployee(employeeEntity);
        employeeDAO.update(employeeEntity, employeeEntity.getId());

        LOGGER.info("Added passport with id={}", passportEntity.getId());
    }

    @Override
    public void updatePassport(PassportDTO passportDTO) {
        PassportEntity passportEntity = Converter.toEntity(passportDTO, PassportEntity.class);
        EmployeeEntity employeeEntity = employeeDAO.get(passportEntity.getId());
        employeeEntity.setPassport(passportEntity);
        employeeDAO.update(employeeEntity, employeeEntity.getId());

        LOGGER.info("Updated passport with id={}", passportEntity.getId());
    }

    @Override
    public void deletePassport(Serializable id) {
        EmployeeEntity employee = employeeDAO.get(id);
        employee.setPassport(null);
        employeeDAO.update(employee, employee.getId());

        LOGGER.info("Deleted passport with id={}", id);
    }

    @Override
    public PassportDTO getPassport(Serializable id) {
        EmployeeEntity employee = employeeDAO.get(id);
        PassportDTO passportDTO = Converter.toDto(employee.getPassport(), PassportDTO.class);

        if (passportDTO != null) {
            LOGGER.info("Found passport with id={}", id);
        }

        return passportDTO;
    }

    @Override
    public void closeDao() {
        this.employeeDAO.close();
    }
}
