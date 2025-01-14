package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.EducationDAO;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.impl.EducationDaoImpl;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.entity.EducationEntity;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.EducationService;
import by.itacademy.jd2.utils.PaginatorUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EducationServiceImpl implements EducationService {
    private final EducationDAO educationDAO;
    private final EmployeeDAO employeeDAO;
    private final Converter converter;

    public EducationServiceImpl() {
        this.educationDAO = new EducationDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
        this.converter = Converter.getConverter();
    }

    @Override
    public void addEducation(EducationDTO educationDTO) {
        EducationEntity educationEntity = converter.toEntity(educationDTO, EducationEntity.class);
        EmployeeEntity employeeEntity = employeeDAO.get(educationDTO.getEmployeeId());
        educationEntity.setEmployee(employeeEntity);
        employeeEntity.getEducations().add(educationEntity);
        educationDAO.save(educationEntity);
        educationDTO.setId(educationEntity.getId());
    }

    @Override
    public void updateEducation(EducationDTO educationDTO) {
        EducationEntity educationEntity = converter.toEntity(educationDTO, EducationEntity.class);
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
        return converter.toDto(educationDAO.get(id), EducationDTO.class);
    }

    @Override
    public PageInfo<EducationDTO> getEducationsByEmployeeIdAndPage(Serializable employeeId,
                                                                   Integer pageNumber,
                                                                   Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        List<EducationDTO> education = Optional.of(
                        educationDAO.getEducationByEmployeeId(employeeId, pageSize, pageNumber).stream()
                                .map(entity -> converter.toDto(entity, EducationDTO.class))
                                .collect(Collectors.toList()))
                .orElse(null);
        Long educationCount = educationDAO.getEducationCountByEmployeeId(employeeId);

        return new PageInfo<>(education, pageNumber, pageSize, educationCount);
    }

    @Override
    public void closeDao() {
        educationDAO.close();
        employeeDAO.close();
    }
}
