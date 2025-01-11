package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.EmployeeItemDTO;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.PassportEntity;
import by.itacademy.jd2.service.api.EmployeeService;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
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
        employeeDTO.setId(employeeEntity.getId());
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity oldEmployee = employeeDAO.get(employeeDTO.getId());
        EmployeeEntity newEmployee = converter.toEntity(employeeDTO, EmployeeEntity.class);
        if (oldEmployee != null) {
            newEmployee.setPassport(oldEmployee.getPassport());
        }
        employeeDAO.update(newEmployee, newEmployee.getId());
    }

    @Override
    public void deleteEmployee(Serializable id) {
        employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO getEmployee(Serializable id) {
        return converter.toDto(employeeDAO.get(id), EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAllCurrentEmployees() {
        return employeeDAO.getAllCurrentEmployees().stream()
                .map(entity -> converter.toDto(entity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getAllFiredEmployees() {
        return employeeDAO.getAllFiredEmployees().stream()
                .map(entity -> converter.toDto(entity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeItemDTO> getAllEmployeeItems(boolean isCurrentOnly) {
        List<EmployeeEntity> allEmployees = employeeDAO.getAll();
        if (isCurrentOnly) {
            allEmployees.removeIf(EmployeeEntity::isFired);
        }
        return  allEmployees.stream()
                .map(entity -> converter.toDto(entity, EmployeeItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addPassport(PassportDTO passportDTO, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = converter.toEntity(employeeDTO, EmployeeEntity.class);
        PassportEntity passportEntity = converter.toEntity(passportDTO, PassportEntity.class);
        employeeEntity.setPassport(passportEntity);
        passportEntity.setEmployee(employeeEntity);
        employeeDAO.update(employeeEntity, employeeEntity.getId());
        employeeDTO.setPassport(converter.toDto(passportEntity, PassportDTO.class));
        passportDTO.setId(passportEntity.getId());
    }

    @Override
    public void updatePassport(PassportDTO passportDTO, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = converter.toEntity(employeeDTO, EmployeeEntity.class);
        PassportEntity passportEntity = converter.toEntity(passportDTO, PassportEntity.class);
        passportEntity.setId(employeeDTO.getId());
        employeeEntity.setPassport(passportEntity);
        passportEntity.setEmployee(employeeEntity);
        employeeDAO.update(employeeEntity, employeeEntity.getId());
        employeeDTO.setPassport(converter.toDto(passportEntity, PassportDTO.class));
        passportDTO.setId(passportEntity.getId());
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
