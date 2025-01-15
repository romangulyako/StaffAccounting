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
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.PositionService;
import by.itacademy.jd2.utils.PaginatorUtil;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    @Override
    public void addPosition(PositionDTO positionDTO) {
        PositionEntity positionEntity = converter.toEntity(positionDTO, PositionEntity.class);
        DepartmentEntity departmentEntity = departmentDAO.get(positionDTO.getDepartmentId());
        positionEntity.setDepartment(departmentEntity);
        departmentEntity.getPositions().add(positionEntity);
        positionDAO.save(positionEntity);
    }

    @Transactional
    @Override
    public void updatePosition(PositionDTO positionDTO) {
        if (positionDTO != null) {
            PositionEntity positionEntity = converter.toEntity(positionDTO, PositionEntity.class);
            positionEntity.setDepartment(positionDAO.get(positionEntity.getId()).getDepartment());
            positionDAO.update(positionEntity, positionEntity.getId());
        }
    }

    @Transactional
    @Override
    public void deletePosition(Serializable id) {
        PositionEntity positionEntity = positionDAO.get(id);
        positionEntity.getDepartment().getPositions().remove(positionEntity);
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

    @Transactional
    @Override
    public PageInfo<PositionDTO> getPositionsByDepartmentIdAndPage(Serializable departmentId,
                                                                   Integer pageNumber,
                                                                   Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        List<PositionDTO> positions = Optional.of(
                        positionDAO.getPositionsByDepartmentIdAndPage(departmentId, pageSize, pageNumber).stream()
                                .map(entity -> converter.toDto(entity, PositionDTO.class))
                                .collect(Collectors.toList()))
                .orElse(null);
        Long positionsCount = positionDAO.getPositionsCountByDepartmentId(departmentId);

        return new PageInfo<>(positions, pageNumber, pageSize, positionsCount);
    }

    @Override
    public void closeDao() {
        positionDAO.close();
        departmentDAO.close();
    }
}
