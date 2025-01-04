package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.EducationConverter;
import by.itacademy.jd2.dao.api.EducationDAO;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.impl.EducationDaoImpl;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.entity.EducationEntity;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.service.api.EducationService;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class EducationServiceImpl implements EducationService {
    private final EducationDAO educationDAO = new EducationDaoImpl();
    private final EmployeeDAO employeeDAO = new EmployeeDaoImpl();

    @Override
    public void addEducation(EducationDTO educationDTO) {
        EducationEntity educationEntity = EducationConverter.toEntity(educationDTO);
        EmployeeEntity employeeEntity = employeeDAO.get(educationDTO.getEmployeeId());
        educationEntity.setEmployee(employeeEntity);
        employeeEntity.getEducations().add(educationEntity);
        educationDAO.save(educationEntity);
        educationDTO.setId(educationEntity.getId());
    }

    @Override
    public void updateEducation(EducationDTO educationDTO) {
        EducationEntity educationEntity = EducationConverter.toEntity(educationDTO);
        EmployeeEntity employeeEntity = employeeDAO.get(educationDTO.getEmployeeId());
        educationEntity.setEmployee(employeeEntity);
        educationDAO.update(educationEntity, educationEntity.getId());
    }

    @Override
    public void deleteEducation(Serializable id) {
        educationDAO.delete(id);
    }

    @Override
    public EducationDTO getEducation(Serializable id) {
        return EducationConverter.toDto(educationDAO.get(id));
    }

    @Override
    public List<EducationDTO> getEducationsByEmployeeId(Serializable employeeId) {
        List<EducationEntity> entities = educationDAO.getEducationByEmployeeId(employeeId);
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return entities.stream()
                .map(EducationConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void closeDao() {
        educationDAO.close();
        employeeDAO.close();
    }
}
