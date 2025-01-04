package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.MaritalStatusConverter;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.api.MaritalStatusDAO;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dao.impl.MaritalStatusDaoImpl;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.MaritalStatusEntity;
import by.itacademy.jd2.service.api.MaritalStatusService;
import by.itacademy.jd2.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class MaritalStatusServiceImpl implements MaritalStatusService {
    private final MaritalStatusDAO maritalStatusDAO;
    private final EmployeeDAO employeeDAO;

    public MaritalStatusServiceImpl() {
        this.maritalStatusDAO = new MaritalStatusDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
    }

    @Override
    public void addMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        MaritalStatusEntity maritalStatusEntity =
                MaritalStatusConverter.toEntity(maritalStatusDTO);
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
                MaritalStatusConverter.toEntity(maritalStatusDTO);
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
        return MaritalStatusConverter.toDto(maritalStatusDAO.get(id));
    }

    @Override
    public List<MaritalStatusDTO> getAllMaritalStatuses(Long employeeId) {
        List<MaritalStatusEntity> entities =
                maritalStatusDAO.getMaritalStatusesByEmployeeId(employeeId);
        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities.stream()
                .map(MaritalStatusConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void closeDao() {
        maritalStatusDAO.close();
        HibernateUtil.close();
    }
}
