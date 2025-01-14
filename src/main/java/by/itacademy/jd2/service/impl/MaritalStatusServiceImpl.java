package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.api.MaritalStatusDAO;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dao.impl.MaritalStatusDaoImpl;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.MaritalStatusEntity;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.MaritalStatusService;
import by.itacademy.jd2.utils.HibernateUtil;
import by.itacademy.jd2.utils.PaginatorUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaritalStatusServiceImpl implements MaritalStatusService {
    private final MaritalStatusDAO maritalStatusDAO;
    private final EmployeeDAO employeeDAO;
    private final Converter converter;

    public MaritalStatusServiceImpl() {
        this.maritalStatusDAO = new MaritalStatusDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
        this.converter = Converter.getConverter();
    }

    @Override
    public void addMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        MaritalStatusEntity maritalStatusEntity =
                converter.toEntity(maritalStatusDTO, MaritalStatusEntity.class);
        EmployeeEntity employeeEntity = employeeDAO.get(maritalStatusDTO.getEmployeeId());
        employeeEntity.getMaritalStatuses().stream()
                .filter(MaritalStatusEntity::isCurrent)
                .forEach(status -> status.setCurrent(false));
        maritalStatusEntity.setEmployee(employeeEntity);
        employeeEntity.getMaritalStatuses().add(maritalStatusEntity);
        maritalStatusDAO.save(maritalStatusEntity);
        maritalStatusDTO.setId(maritalStatusEntity.getId());
    }

    @Override
    public void updateMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        MaritalStatusEntity maritalStatusEntity =
                converter.toEntity(maritalStatusDTO, MaritalStatusEntity.class);
        EmployeeEntity employeeEntity = employeeDAO.get(maritalStatusDTO.getEmployeeId());
        maritalStatusEntity.setEmployee(employeeEntity);
        maritalStatusDAO.update(maritalStatusEntity, maritalStatusEntity.getId());
    }

    @Override
    public void deleteMaritalStatus(Serializable id) {
        maritalStatusDAO.delete(id);
    }

    @Override
    public MaritalStatusDTO getMaritalStatus(Serializable id) {
        return converter.toDto(maritalStatusDAO.get(id), MaritalStatusDTO.class);
    }

    @Override
    public PageInfo<MaritalStatusDTO> getMaritalStatusesByEmployeeAndPage(Serializable employeeId,
                                                                          Integer pageNumber,
                                                                          Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        List<MaritalStatusDTO> maritalStatuses = Optional.of(
                maritalStatusDAO.getMaritalStatusesByEmployeeIdAndPage(employeeId, pageSize, pageNumber).stream()
                        .map(entity -> converter.toDto(entity, MaritalStatusDTO.class))
                        .collect(Collectors.toList()))
                .orElse(null);
        Long maritalStatusesCount = maritalStatusDAO.getMaritalStatusesCountByEmployeeId(employeeId);

        return new PageInfo<>(maritalStatuses, pageNumber, pageSize, maritalStatusesCount);
    }

    @Override
    public void closeDao() {
        maritalStatusDAO.close();
        HibernateUtil.close();
    }
}
