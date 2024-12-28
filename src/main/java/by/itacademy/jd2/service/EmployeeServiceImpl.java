package by.itacademy.jd2.service;

import by.itacademy.jd2.converter.EmployeeConverter;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.repository.EmployeeEntity;
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
        EmployeeEntity employeeEntity =
                EmployeeConverter.toEntity(employeeDTO);
        employeeDAO.update(employeeEntity, employeeEntity.getId());
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
}
