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

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EducationServiceImpl implements EducationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EducationServiceImpl.class);
    private final EducationDAO educationDAO;
    private final EmployeeDAO employeeDAO;

    public EducationServiceImpl() {
        this.educationDAO = new EducationDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
    }

    @Override
    public void addEducation(EducationDTO educationDTO) {
        EducationEntity educationEntity = Converter.toEntity(educationDTO, EducationEntity.class);
        EmployeeEntity employeeEntity = employeeDAO.get(educationDTO.getEmployeeId());
        educationEntity.setEmployee(employeeEntity);
        employeeEntity.getEducations().add(educationEntity);
        educationDAO.save(educationEntity);

        LOGGER.info("Added education with id={}", educationEntity.getId());
    }

    @Override
    public void updateEducation(EducationDTO educationDTO) {
        if (educationDTO != null) {
            EducationEntity newEntity = Converter.toEntity(educationDTO, EducationEntity.class);
            newEntity.setEmployee(educationDAO.get(educationDTO.getId()).getEmployee());
            educationDAO.update(newEntity, newEntity.getId());

            LOGGER.info("Updated education with id={}", newEntity.getId());
        }
    }

    @Override
    public void deleteEducation(Serializable id) {
        EducationEntity educationEntity = educationDAO.get(id);
        if (educationEntity != null) {
            educationEntity.getEmployee().getEducations().remove(educationEntity);
            educationDAO.delete(id);

            LOGGER.info("Deleted education with id={}", educationEntity.getId());
        }
    }

    @Override
    public EducationDTO getEducation(Serializable id) {
        EducationDTO education = Converter.toDto(educationDAO.get(id), EducationDTO.class);

        LOGGER.info("Education with id={} found", education.getId());
        return education;
    }

    @Transactional
    @Override
    public PageInfo<EducationDTO> getEducationsByEmployeeIdAndPage(Serializable employeeId,
                                                                   Integer pageNumber,
                                                                   Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        List<EducationDTO> education = Optional.of(
                        educationDAO.getEducationByEmployeeId(employeeId, pageSize, pageNumber).stream()
                                .map(entity -> Converter.toDto(entity, EducationDTO.class))
                                .collect(Collectors.toList()))
                .orElse(null);
        Long educationCount = educationDAO.getEducationCountByEmployeeId(employeeId);

        LOGGER.info("{} education from {} found successfully",education.size(), educationCount);

        return new PageInfo<>(education, pageNumber, pageSize, educationCount);
    }

    @Override
    public void closeDao() {
        educationDAO.close();
        employeeDAO.close();
    }
}
