package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.EmployeeConverter;
import by.itacademy.jd2.converter.PassportConverter;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.repository.EmployeeEntity;
import by.itacademy.jd2.repository.PassportEntity;
import by.itacademy.jd2.service.api.EmployeeService;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl() {
        employeeDAO = new EmployeeDaoImpl();
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity =
                EmployeeConverter.toEntity(employeeDTO);
        employeeDAO.save(employeeEntity);
        employeeDTO.setId(employeeEntity.getId());
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity oldEmployee = employeeDAO.get(employeeDTO.getId());
        EmployeeEntity newEmployee =  EmployeeConverter.toEntity(employeeDTO);
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
        return EmployeeConverter.toDTO(
                employeeDAO.get(id)
        );
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeDAO.getAll().stream()
                .map(EmployeeConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addPassport(PassportDTO passportDTO, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = EmployeeConverter.toEntity(employeeDTO);
        PassportEntity passportEntity = PassportConverter.toEntity(passportDTO);
        employeeEntity.setPassport(passportEntity);
        passportEntity.setEmployee(employeeEntity);
        employeeDAO.update(employeeEntity, employeeEntity.getId());
        employeeDTO.setPassport(PassportConverter.toDTO(passportEntity));
        passportDTO.setId(passportEntity.getId());
    }

    @Override
    public void updatePassport(PassportDTO passportDTO, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = EmployeeConverter.toEntity(employeeDTO);
        PassportEntity passportEntity = PassportConverter.toEntity(passportDTO);
        passportEntity.setId(employeeDTO.getId());
        employeeEntity.setPassport(passportEntity);
        passportEntity.setEmployee(employeeEntity);
        employeeDAO.update(employeeEntity, employeeEntity.getId());
        employeeDTO.setPassport(PassportConverter.toDTO(passportEntity));
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
        return PassportConverter.toDTO(employee.getPassport());
    }

    @Override
    public void closeDao() {
        this.employeeDAO.close();
    }
}
