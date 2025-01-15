package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.Converter;
import by.itacademy.jd2.dao.api.CareerDAO;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.api.PositionDAO;
import by.itacademy.jd2.dao.impl.CareerDaoImpl;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dao.impl.PositionDaoImpl;
import by.itacademy.jd2.dto.CareerStepGetDTO;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.dto.DismissDTO;
import by.itacademy.jd2.dto.PositionHistoryDTO;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.CareerService;
import by.itacademy.jd2.utils.PaginatorUtil;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CareerServiceImpl implements CareerService {
    private final CareerDAO careerDAO;
    private final EmployeeDAO employeeDAO;
    private final PositionDAO positionDAO;
    private final Converter converter;

    public CareerServiceImpl() {
        this.careerDAO = new CareerDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
        this.positionDAO = new PositionDaoImpl();
        this.converter = Converter.getConverter();
    }

    @Transactional
    @Override
    public void appointEmployee(CareerStepSaveDTO careerStepDTO) {
        EmployeeEntity employeeEntity = employeeDAO.get(careerStepDTO.getEmployeeId());
        PositionEntity positionEntity = positionDAO.get(careerStepDTO.getPositionId());
        CareerStepEntity careerStepEntity = converter.toEntity(careerStepDTO, CareerStepEntity.class);
        employeeEntity.getCareer().stream()
                .filter(CareerStepEntity::isCurrent)
                .forEach(careerStep -> {
                    careerStep.setCurrent(false);
                    careerStep.setOrderLiberation(careerStepEntity.getOrderAppointment());
                    careerStep.setDateOfLiberationPosition(careerStepEntity.getDateOfAppointment());
                });
        careerStepEntity.setEmployee(employeeEntity);
        careerStepEntity.setPosition(positionEntity);
        employeeEntity.getCareer().add(careerStepEntity);
        if (employeeEntity.isFired()) {
            employeeEntity.setFired(false);
        }
        positionEntity.getHistory().add(careerStepEntity);
        careerDAO.save(careerStepEntity);
    }

    @Transactional
    @Override
    public void dismissEmployee(DismissDTO dismissDTO) {
        EmployeeEntity employee = employeeDAO.get(dismissDTO.getEmployeeId());
        employee.getCareer().stream()
                .filter(CareerStepEntity::isCurrent)
                .forEach(careerStep -> {
                    careerStep.setCurrent(false);
                    careerStep.setOrderLiberation(dismissDTO.getOrderDismiss());
                    careerStep.setDateOfLiberationPosition(dismissDTO.getDateOfDismiss());
                });
        employee.setFired(true);
        employeeDAO.update(employee, employee.getId());
    }

    @Transactional
    @Override
    public void updateCareerStep(CareerStepSaveDTO careerStepDTO, Serializable id) {
        EmployeeEntity employeeEntity = employeeDAO.get(careerStepDTO.getEmployeeId());
        PositionEntity positionEntity = positionDAO.get(careerStepDTO.getPositionId());
        CareerStepEntity careerStepEntity = converter.toEntity(careerStepDTO, CareerStepEntity.class);
        careerStepEntity.setEmployee(employeeEntity);
        careerStepEntity.setPosition(positionEntity);
        careerDAO.update(careerStepEntity, id);
    }

    @Transactional
    @Override
    public void deleteCareerStep(Serializable id) {
        CareerStepEntity careerStep = careerDAO.get(id);
        careerStep.getPosition().getHistory().remove(careerStep);
        careerStep.getEmployee().getCareer().remove(careerStep);
        careerStep.setEmployee(null);
        careerStep.setPosition(null);
        careerDAO.delete(id);
    }

    @Override
    public CareerStepGetDTO getCareerStep(Serializable id) {
        return converter.toDto(careerDAO.get(id), CareerStepGetDTO.class);
    }

    @Transactional
    @Override
    public PageInfo<CareerStepGetDTO> getCareerOfEmployeeByPage(Serializable employeeId,
                                                                Integer pageNumber,
                                                                Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);

        List<CareerStepGetDTO> career = Optional.of(
                careerDAO.getCareerByEmployeeIdAndPage(employeeId, pageSize, pageNumber)
                        .stream().map(entity -> converter.toDto(entity, CareerStepGetDTO.class))
                        .collect(Collectors.toList()))
                .orElse(null);

        Long careerStepsCount = careerDAO.getCareerStepCountByEmployeeId(employeeId);

        return new PageInfo<>(career, pageNumber, pageSize, careerStepsCount);
    }

    @Transactional
    @Override
    public PageInfo<PositionHistoryDTO> getPositionHistoryByPage(Serializable positionId,
                                                             Integer pageNumber,
                                                             Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        List<PositionHistoryDTO> career = Optional.of(
                        careerDAO.getPositionHistoryByPage(positionId, pageSize, pageNumber)
                                .stream().map(entity -> converter.toDto(entity, PositionHistoryDTO.class))
                                .collect(Collectors.toList()))
                .orElse(null);
        Long careerStepsCount = careerDAO.getCareerStepCountByPositionId(positionId);

        return new PageInfo<>(career, pageNumber, pageSize, careerStepsCount);
    }

    @Override
    public void closeDao() {
        careerDAO.close();
        employeeDAO.close();
        positionDAO.close();
    }
}
