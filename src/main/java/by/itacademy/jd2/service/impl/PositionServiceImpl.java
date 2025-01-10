package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.DepartmentDAO;
import by.itacademy.jd2.dao.api.PositionDAO;
import by.itacademy.jd2.dao.impl.DepartmentDaoImpl;
import by.itacademy.jd2.dao.impl.PositionDaoImpl;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.dto.PositionItemDTO;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.service.api.PositionService;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class PositionServiceImpl implements PositionService {
    private final PositionDAO positionDAO;
    private final DepartmentDAO departmentDAO;
    private final Converter converter;

    public PositionServiceImpl() {
        this.positionDAO = new PositionDaoImpl();
        this.departmentDAO = new DepartmentDaoImpl();
        this.converter = Converter.getConverter();
    }

    @Override
    public void addPosition(PositionDTO positionDTO) {
        PositionEntity positionEntity = converter.toEntity(positionDTO, PositionEntity.class);
        DepartmentEntity departmentEntity = departmentDAO.get(positionDTO.getDepartmentId());
        positionEntity.setDepartment(departmentEntity);
        departmentEntity.getPositions().add(positionEntity);
        positionDAO.save(positionEntity);
        positionDTO.setId(positionEntity.getId());
    }

    @Override
    public void updatePosition(PositionDTO positionDTO) {
        PositionEntity positionEntity = converter.toEntity(positionDTO, PositionEntity.class);
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
        return converter.toDto(positionDAO.get(id), PositionDTO.class);
    }

    @Override
    public List<PositionItemDTO> getAllPositionItems() {
        return positionDAO.getAll().stream()
                .map(entity -> converter.toDto(entity, PositionItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PositionDTO> getPositionsByDepartmentId(Serializable departmentId) {
        List<PositionEntity> entities = positionDAO.getPositionsByDepartmentId(departmentId);
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return entities.stream()
                .map(entity -> converter.toDto(entity, PositionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void closeDao() {
        positionDAO.close();
        departmentDAO.close();
    }
}
