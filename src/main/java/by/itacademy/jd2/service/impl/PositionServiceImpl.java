package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.PositionConverter;
import by.itacademy.jd2.dao.api.DepartmentDAO;
import by.itacademy.jd2.dao.api.PositionDAO;
import by.itacademy.jd2.dao.impl.DepartmentDaoImpl;
import by.itacademy.jd2.dao.impl.PositionDaoImpl;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.service.api.PositionService;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class PositionServiceImpl implements PositionService {
    private final PositionDAO positionDAO;
    private final DepartmentDAO departmentDAO;

    public PositionServiceImpl() {
        this.positionDAO = new PositionDaoImpl();
        this.departmentDAO = new DepartmentDaoImpl();
    }

    @Override
    public void addPosition(PositionDTO positionDTO) {
        PositionEntity positionEntity = PositionConverter.toEntity(positionDTO);
        DepartmentEntity departmentEntity = departmentDAO.get(positionDTO.getDepartmentId());
        positionEntity.setDepartment(departmentEntity);
        departmentEntity.getPositions().add(positionEntity);
        positionDAO.save(positionEntity);
        positionDTO.setId(positionEntity.getId());
    }

    @Override
    public void updatePosition(PositionDTO positionDTO) {
        PositionEntity positionEntity = PositionConverter.toEntity(positionDTO);
        DepartmentEntity departmentEntity = departmentDAO.get(positionDTO.getDepartmentId());
        positionEntity.setDepartment(departmentEntity);
        positionDAO.update(positionEntity, positionEntity.getId());
    }

    @Override
    public void deletePosition(Serializable id) {
        positionDAO.delete(id);
    }

    @Override
    public PositionDTO getPosition(Serializable id) {
        return PositionConverter.toDto(positionDAO.get(id));
    }

    @Override
    public List<PositionDTO> getPositionsByDepartmentId(Serializable departmentId) {
        List<PositionEntity> entities = positionDAO.getPositionsByDepartmentId(departmentId);
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return entities.stream()
                .map(PositionConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void closeDao() {
        positionDAO.close();
        departmentDAO.close();
    }
}
