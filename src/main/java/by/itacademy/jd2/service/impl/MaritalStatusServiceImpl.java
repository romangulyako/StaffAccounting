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
import by.itacademy.jd2.utils.PaginatorUtil;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaritalStatusServiceImpl implements MaritalStatusService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MaritalStatusServiceImpl.class);
    private final MaritalStatusDAO maritalStatusDAO;
    private final EmployeeDAO employeeDAO;

    public MaritalStatusServiceImpl() {
        this.maritalStatusDAO = new MaritalStatusDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
    }

    @Override
    public void addMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        MaritalStatusEntity maritalStatusEntity =
                Converter.toEntity(maritalStatusDTO, MaritalStatusEntity.class);
        EmployeeEntity employeeEntity = employeeDAO.get(maritalStatusDTO.getEmployeeId());
        employeeEntity.getMaritalStatuses().stream()
                .filter(MaritalStatusEntity::isCurrent)
                .forEach(status -> status.setCurrent(false));
        maritalStatusEntity.setEmployee(employeeEntity);
        employeeEntity.getMaritalStatuses().add(maritalStatusEntity);
        maritalStatusDAO.save(maritalStatusEntity);

        LOGGER.info("Added marital status for employee with id={}", maritalStatusDTO.getEmployeeId());
    }

    @Override
    public void updateMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        if (maritalStatusDTO != null) {
            MaritalStatusEntity maritalStatusEntity =
                    Converter.toEntity(maritalStatusDTO, MaritalStatusEntity.class);
            maritalStatusEntity.setEmployee(employeeDAO.get(maritalStatusDTO.getEmployeeId()));
            maritalStatusDAO.update(maritalStatusEntity, maritalStatusEntity.getId());

            LOGGER.info("Updated marital status for employee with id={}", maritalStatusDTO.getEmployeeId());
        }
    }

    @Override
    public void deleteMaritalStatus(Serializable id) {
        MaritalStatusEntity maritalStatusEntity = maritalStatusDAO.get(id);
        maritalStatusEntity.getEmployee().getMaritalStatuses().remove(maritalStatusEntity);
        maritalStatusDAO.delete(id);

        LOGGER.info("Deleted marital status with id={} for employee with id={}", id, maritalStatusEntity.getEmployee().getId());
    }

    @Override
    public MaritalStatusDTO getMaritalStatus(Serializable id) {
        MaritalStatusDTO maritalStatusDTO = Converter.toDto(maritalStatusDAO.get(id), MaritalStatusDTO.class);

        if (maritalStatusDTO != null) {
            LOGGER.info("Found marital status with id={}", id);
        }
        return maritalStatusDTO;
    }

    @Override
    public PageInfo<MaritalStatusDTO> getMaritalStatusesByEmployeeAndPage(Serializable employeeId,
                                                                          Integer pageNumber,
                                                                          Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        List<MaritalStatusDTO> maritalStatuses = Optional.of(
                        maritalStatusDAO.getMaritalStatusesByEmployeeIdAndPage(employeeId, pageSize, pageNumber)
                                .stream()
                                .map(entity -> Converter.toDto(entity, MaritalStatusDTO.class))
                                .collect(Collectors.toList()))
                .orElse(null);
        Long maritalStatusesCount = maritalStatusDAO.getMaritalStatusesCountByEmployeeId(employeeId);

        LOGGER.info("Found {} from {} marital statuses for employee with id={}",
                maritalStatuses.size(), maritalStatusesCount, employeeId);

        return new PageInfo<>(maritalStatuses, pageNumber, pageSize, maritalStatusesCount);
    }

    @Override
    public void closeDao() {
        maritalStatusDAO.close();
    }
}
