package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.RelativeConverter;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.api.RelativeDAO;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dao.impl.RelativeDaoImpl;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.repository.EmployeeEntity;
import by.itacademy.jd2.repository.RelativeEntity;
import by.itacademy.jd2.service.api.RelativeService;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class RelativeServiceImpl implements RelativeService {
    private final RelativeDAO relativeDAO;
    private final EmployeeDAO employeeDAO;

    public RelativeServiceImpl() {
        this.relativeDAO = new RelativeDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
    }
    @Override
    public void addRelative(RelativeDTO relativeDTO) {
        RelativeEntity relativeEntity = RelativeConverter.toEntity(relativeDTO);
        EmployeeEntity employeeEntity = employeeDAO.get(relativeDTO.getEmployeeId());
        relativeEntity.setEmployee(employeeEntity);
        employeeEntity.getRelatives().add(relativeEntity);
        relativeDAO.save(relativeEntity);
        relativeDTO.setId(relativeEntity.getId());
    }

    @Override
    public void updateRelative(RelativeDTO relativeDTO) {
        RelativeEntity relativeEntity = RelativeConverter.toEntity(relativeDTO);
        EmployeeEntity employeeEntity = employeeDAO.get(relativeDTO.getEmployeeId());
        relativeEntity.setEmployee(employeeEntity);
        relativeDAO.update(relativeEntity, relativeEntity.getId());
    }

    @Override
    public void deleteRelative(Serializable id) {
        relativeDAO.delete(id);
    }

    @Override
    public RelativeDTO getRelative(Serializable id) {
        return RelativeConverter.toDTO(relativeDAO.get(id));
    }


    @Override
    public List<RelativeDTO> getRelatives(Long employeeId) {
        List<RelativeEntity> entities = relativeDAO.getRelativesByEmployeeId(employeeId);
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return relativeDAO.getRelativesByEmployeeId(employeeId)
                .stream()
                .map(RelativeConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void closeDao() {
        relativeDAO.close();
        employeeDAO.close();
    }
}
