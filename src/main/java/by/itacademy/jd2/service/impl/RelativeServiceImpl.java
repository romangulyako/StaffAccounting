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

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RelativeServiceImpl implements RelativeService {
    private final RelativeDAO relativeDAO;
    private final EmployeeDAO employeeDAO;
    private final Converter converter;

    public RelativeServiceImpl() {
        this.relativeDAO = new RelativeDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
        this.converter = Converter.getConverter();
    }

    @Transactional
    @Override
    public void addRelative(RelativeDTO relativeDTO) {
        RelativeEntity relativeEntity = converter.toEntity(relativeDTO, RelativeEntity.class);
        EmployeeEntity employeeEntity = employeeDAO.get(relativeDTO.getEmployeeId());
        relativeEntity.setEmployee(employeeEntity);
        employeeEntity.getRelatives().add(relativeEntity);
        relativeDAO.save(relativeEntity);
    }

    @Transactional
    @Override
    public void updateRelative(RelativeDTO relativeDTO) {
        if (relativeDTO != null) {
            RelativeEntity relativeEntity = converter.toEntity(relativeDTO, RelativeEntity.class);
            relativeEntity.setEmployee(relativeDAO.get(relativeEntity.getId()).getEmployee());
            relativeDAO.update(relativeEntity, relativeEntity.getId());
        }
    }

    @Transactional
    @Override
    public void deleteRelative(Serializable id) {
        RelativeEntity relativeEntity = relativeDAO.get(id);
        relativeEntity.getEmployee().getRelatives().remove(relativeEntity);
        relativeDAO.delete(id);
    }

    @Override
    public RelativeDTO getRelative(Serializable id) {
        return converter.toDto(relativeDAO.get(id), RelativeDTO.class);
    }


    @Transactional
    @Override
    public PageInfo<RelativeDTO> getRelativesByEmployeeIdAndPage(Serializable employeeId,
                                                                 Integer pageNumber,
                                                                 Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        List<RelativeDTO> relatives = Optional.of(
                relativeDAO.getRelativesByEmployeeIdAndPage(employeeId, pageSize, pageNumber).stream()
                        .map(entity -> converter.toDto(entity, RelativeDTO.class))
                        .collect(Collectors.toList())).orElse(null);
        Long relativesCount = relativeDAO.getRelativesCountByEmployeeId(employeeId);

        return new PageInfo<>(relatives, pageNumber, pageSize, relativesCount);
    }

    @Override
    public void closeDao() {
        relativeDAO.close();
        employeeDAO.close();
    }
}
