package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.EmployeeItemDTO;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.PassportEntity;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.utils.PaginatorUtil;

import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Boolean DEFAULT_IS_FIRED = false;
    private final Converter converter = Converter.getConverter();
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl() {
        employeeDAO = new EmployeeDaoImpl();
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity =
                converter.toEntity(employeeDTO, EmployeeEntity.class);
        employeeDAO.save(employeeEntity);
    }

    @Transactional
    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO != null) {
            EmployeeEntity newEmployee = converter.toEntity(employeeDTO, EmployeeEntity.class);
            EmployeeEntity oldEmployee = employeeDAO.get(employeeDTO.getId());
            newEmployee.setPassport(oldEmployee.getPassport());
            newEmployee.setFired(oldEmployee.isFired());
            employeeDAO.update(newEmployee, newEmployee.getId());
        }
    }

    @Override
    public void deleteEmployee(Serializable id) {
        employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO getEmployee(Serializable id) {
        return converter.toDto(employeeDAO.get(id), EmployeeDTO.class);
    }

    @Transactional
    @Override
    public PageInfo<EmployeeDTO> getEmployeesByFiredAndPage(Boolean isFired, Integer pageSize, Integer pageNumber) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        if (isFired == null) {
            isFired = DEFAULT_IS_FIRED;
        }
        List<EmployeeDTO> employees = employeeDAO.getEmployeesByFiredAndPage(isFired, pageSize, pageNumber).stream()
                .map(entity -> converter.toDto(entity, EmployeeDTO.class))
                .collect(Collectors.toList());
        Long employeesCount = employeeDAO.getEmployeesCount(isFired);

        return new PageInfo<>(employees, pageNumber, pageSize, employeesCount);
    }

    @Override
    public List<EmployeeItemDTO> getAllEmployeeItems(boolean isCurrentOnly) {
        List<EmployeeEntity> allEmployees = employeeDAO.getAll();
        if (isCurrentOnly) {
            allEmployees.removeIf(EmployeeEntity::isFired);
        }
        return allEmployees.stream()
                .map(entity -> converter.toDto(entity, EmployeeItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addPassport(PassportDTO passportDTO) {
        EmployeeEntity employeeEntity = employeeDAO.get(passportDTO.getId());
        PassportEntity passportEntity = converter.toEntity(passportDTO, PassportEntity.class);
        employeeEntity.setPassport(passportEntity);
        passportEntity.setEmployee(employeeEntity);
        employeeDAO.update(employeeEntity, employeeEntity.getId());
    }

    @Override
    public void updatePassport(PassportDTO passportDTO) {
        PassportEntity passportEntity = converter.toEntity(passportDTO, PassportEntity.class);
        EmployeeEntity employeeEntity = employeeDAO.get(passportEntity.getId());
        employeeEntity.setPassport(passportEntity);
        employeeDAO.update(employeeEntity, employeeEntity.getId());
    }

    @Override
    public void deletePassport(Serializable id) {
        EmployeeEntity employee = employeeDAO.get(id);
        employee.setPassport(null);
        employeeDAO.update(employee, employee.getId());
    }

    @Override
    public PassportDTO getPassport(Serializable id) {
        EmployeeEntity employee = employeeDAO.get(id);
        return converter.toDto(employee.getPassport(), PassportDTO.class);
    }

    @Override
    public void closeDao() {
        this.employeeDAO.close();
    }
}
