package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.DepartmentDAO;
import by.itacademy.jd2.dao.api.PositionDAO;
import by.itacademy.jd2.dao.impl.DepartmentDaoImpl;
import by.itacademy.jd2.dao.impl.PositionDaoImpl;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.dto.PositionItemDTO;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.PositionService;
import by.itacademy.jd2.utils.PaginatorUtil;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PositionServiceImpl implements PositionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);
    private static final boolean DEFAULT_IS_ACTUAL = true;
    private final PositionDAO positionDAO;
    private final DepartmentDAO departmentDAO;

    public PositionServiceImpl() {
        this.positionDAO = new PositionDaoImpl();
        this.departmentDAO = new DepartmentDaoImpl();
    }

    @Override
    public void addPosition(PositionDTO positionDTO) {
        PositionEntity positionEntity = Converter.toEntity(positionDTO, PositionEntity.class);
        DepartmentEntity departmentEntity = departmentDAO.get(positionDTO.getDepartmentId());
        positionEntity.setDepartment(departmentEntity);
        departmentEntity.getPositions().add(positionEntity);
        positionDAO.save(positionEntity);

        LOGGER.info("Added position with id={}", positionEntity.getId());
    }

    @Override
    public void updatePosition(PositionDTO positionDTO) {
        if (positionDTO != null) {
            PositionEntity newPosition = Converter.toEntity(positionDTO, PositionEntity.class);
            PositionEntity oldPosition = positionDAO.get(positionDTO.getId());
            newPosition.setDepartment(oldPosition.getDepartment());
            newPosition.setIsActual(oldPosition.getIsActual());
            positionDAO.update(newPosition, newPosition.getId());

            LOGGER.info("Updated position with id={}", newPosition.getId());
        }
    }

    @Override
    public void deletePosition(Serializable id) {
        PositionEntity positionEntity = positionDAO.get(id);
        if (positionEntity != null) {
            positionEntity.getDepartment().getPositions().remove(positionEntity);
            positionDAO.delete(id);

            LOGGER.info("Deleted position with id={}", id);
        }
    }

    @Override
    public PositionDTO getPosition(Serializable id) {
        PositionDTO positionDTO = Converter.toDto(positionDAO.get(id), PositionDTO.class);

        if (positionDTO != null) {
            LOGGER.info("Found position with id={}", id);
        }

        return positionDTO;
    }

    @Override
    public List<PositionItemDTO> getAllPositionItems() {
        List<PositionItemDTO> items = positionDAO.getAll().stream()
                .filter(PositionEntity::getIsActual)
                .map(entity -> Converter.toDto(entity, PositionItemDTO.class))
                .collect(Collectors.toList());

        LOGGER.info("Found {} position items", items.size());

        return items;
    }

    @Override
    public PageInfo<PositionDTO> getPositionsByDepartmentIdAndActualAndPage(Serializable departmentId,
                                                                            Boolean isActual,
                                                                            Integer pageNumber,
                                                                            Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        if (isActual == null) {
            isActual = DEFAULT_IS_ACTUAL;
        }
        List<PositionDTO> positions = Optional.of(
                        positionDAO.getPositionsByDepartmentIdAndActualAndPage(departmentId,
                                        isActual,
                                        pageSize,
                                        pageNumber)
                                .stream()
                                .map(entity -> Converter.toDto(entity, PositionDTO.class))
                                .collect(Collectors.toList()))
                .orElse(null);
        Long positionsCount = positionDAO.getPositionsCountByDepartmentIdAndActual(departmentId, isActual);

        LOGGER.info("Found {} from {} positions", positions.size(), positionsCount);

        return new PageInfo<>(positions, pageNumber, pageSize, positionsCount);
    }

    @Override
    public void reducePosition(Serializable id) {
        PositionEntity position = positionDAO.get(id);
        position.getHistory().stream()
                .filter(CareerStepEntity::isCurrent)
                .forEach(careerStep -> {
                    careerStep.setCurrent(false);
                    careerStep.setDateOfLiberationPosition(Date.valueOf(LocalDate.now()));
                    careerStep.setOrderLiberation("По сокращению");
                });
        positionDAO.update(position, position.getId());
        this.changePositionStatus(id, false);

        LOGGER.info("Reduced position with id={}", id);
    }

    @Override
    public void restorePosition(Serializable id) {
        this.changePositionStatus(id, true);

        LOGGER.info("Restored position with id={}", id);
    }

    @Override
    public void closeDao() {
        positionDAO.close();
        departmentDAO.close();
    }

    private void changePositionStatus(Serializable id, boolean status) {
        PositionEntity positionEntity = positionDAO.get(id);
        positionEntity.setIsActual(status);
        positionDAO.update(positionEntity, id);
    }
}
