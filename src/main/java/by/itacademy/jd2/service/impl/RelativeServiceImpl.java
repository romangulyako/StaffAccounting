package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.api.RelativeDAO;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dao.impl.RelativeDaoImpl;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.RelativeEntity;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.RelativeService;
import by.itacademy.jd2.utils.PaginatorUtil;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RelativeServiceImpl implements RelativeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RelativeServiceImpl.class);
    private final RelativeDAO relativeDAO;
    private final EmployeeDAO employeeDAO;

    public RelativeServiceImpl() {
        this.relativeDAO = new RelativeDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
    }

    @Override
    public void addRelative(RelativeDTO relativeDTO) {
        RelativeEntity relativeEntity = Converter.toEntity(relativeDTO, RelativeEntity.class);
        EmployeeEntity employeeEntity = employeeDAO.get(relativeDTO.getEmployeeId());
        relativeEntity.setEmployee(employeeEntity);
        employeeEntity.getRelatives().add(relativeEntity);
        relativeDAO.save(relativeEntity);

        LOGGER.info("Added relative with id={} for employee with id={}", relativeEntity.getId(), employeeEntity.getId());
    }

    @Override
    public void updateRelative(RelativeDTO relativeDTO) {
        if (relativeDTO != null) {
            RelativeEntity relativeEntity = Converter.toEntity(relativeDTO, RelativeEntity.class);
            relativeEntity.setEmployee(relativeDAO.get(relativeEntity.getId()).getEmployee());
            relativeDAO.update(relativeEntity, relativeEntity.getId());

            LOGGER.info("Updated relative with id={}", relativeEntity.getId());
        }
    }

    @Override
    public void deleteRelative(Serializable id) {
        RelativeEntity relativeEntity = relativeDAO.get(id);
        if (relativeEntity != null) {
            relativeEntity.getEmployee().getRelatives().remove(relativeEntity);
            relativeDAO.delete(id);

            LOGGER.info("Deleted relative with id={}", id);
        }
    }

    @Override
    public RelativeDTO getRelative(Serializable id) {
        RelativeDTO relative = Converter.toDto(relativeDAO.get(id), RelativeDTO.class);
        if (relative != null) {
            LOGGER.info("Found relative with id={}", id);
        }

        return relative;
    }

    @Override
    public PageInfo<RelativeDTO> getRelativesByEmployeeIdAndPage(Serializable employeeId,
                                                                 Integer pageNumber,
                                                                 Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        List<RelativeDTO> relatives = Optional.of(
                relativeDAO.getRelativesByEmployeeIdAndPage(employeeId, pageSize, pageNumber).stream()
                        .map(entity -> Converter.toDto(entity, RelativeDTO.class))
                        .collect(Collectors.toList())).orElse(null);
        Long relativesCount = relativeDAO.getRelativesCountByEmployeeId(employeeId);

        LOGGER.info("Found {} from {} relatives", relatives.size(), relativesCount);

        return new PageInfo<>(relatives, pageNumber, pageSize, relativesCount);
    }

    @Override
    public void closeDao() {
        relativeDAO.close();
        employeeDAO.close();
    }
}
