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

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CareerServiceImpl implements CareerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CareerServiceImpl.class);
    private final CareerDAO careerDAO;
    private final EmployeeDAO employeeDAO;
    private final PositionDAO positionDAO;

    public CareerServiceImpl() {
        this.careerDAO = new CareerDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
        this.positionDAO = new PositionDaoImpl();
    }

    @Override
    public void appointEmployee(CareerStepSaveDTO careerStepDTO) {
        EmployeeEntity employeeEntity = employeeDAO.get(careerStepDTO.getEmployeeId());
        PositionEntity positionEntity = positionDAO.get(careerStepDTO.getPositionId());
        CareerStepEntity careerStepEntity = Converter.toEntity(careerStepDTO, CareerStepEntity.class);
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

        LOGGER.info("Employee with id={} appointed to position with id={} successfully!",
                careerStepDTO.getEmployeeId(), careerStepDTO.getPositionId());
    }

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

        LOGGER.info("Employee with id={} dismissed successfully!", employee.getId());
    }

    @Override
    public void updateCareerStep(CareerStepSaveDTO careerStepDTO, Serializable id) {
        EmployeeEntity employeeEntity = employeeDAO.get(careerStepDTO.getEmployeeId());
        PositionEntity positionEntity = positionDAO.get(careerStepDTO.getPositionId());
        CareerStepEntity careerStepEntity = Converter.toEntity(careerStepDTO, CareerStepEntity.class);
        careerStepEntity.setEmployee(employeeEntity);
        careerStepEntity.setPosition(positionEntity);
        careerDAO.update(careerStepEntity, id);

        LOGGER.info("Career step with id={} updated successfully!", id);
    }

    @Override
    public void deleteCareerStep(Serializable id) {
        CareerStepEntity careerStep = careerDAO.get(id);
        careerStep.getPosition().getHistory().remove(careerStep);
        careerStep.getEmployee().getCareer().remove(careerStep);
        careerStep.setEmployee(null);
        careerStep.setPosition(null);
        careerDAO.delete(id);

        LOGGER.info("Career step with id={} deleted successfully!", id);
    }

    @Override
    public CareerStepGetDTO getCareerStep(Serializable id) {
        CareerStepGetDTO careerStep = Converter.toDto(careerDAO.get(id), CareerStepGetDTO.class);

        if (careerStep != null) {
            LOGGER.info("Career step with id={} found successfully!", id);
        }

        return careerStep;
    }

    @Override
    public PageInfo<CareerStepGetDTO> getCareerOfEmployeeByPage(Serializable employeeId,
                                                                Integer pageNumber,
                                                                Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);

        List<CareerStepGetDTO> career = Optional.of(
                careerDAO.getCareerByEmployeeIdAndPage(employeeId, pageSize, pageNumber)
                        .stream().map(entity -> Converter.toDto(entity, CareerStepGetDTO.class))
                        .collect(Collectors.toList()))
                .orElse(null);

        Long careerStepsCount = careerDAO.getCareerStepCountByEmployeeId(employeeId);

        LOGGER.info("CareerSteps for employee with id={} found successfully!", employeeId);

        return new PageInfo<>(career, pageNumber, pageSize, careerStepsCount);
    }

    @Override
    public PageInfo<PositionHistoryDTO> getPositionHistoryByPage(Serializable positionId,
                                                             Integer pageNumber,
                                                             Integer pageSize) {
        pageSize = PaginatorUtil.checkPageSize(pageSize);
        pageNumber = PaginatorUtil.checkPageNumber(pageNumber);
        List<PositionHistoryDTO> career = Optional.of(
                        careerDAO.getPositionHistoryByPage(positionId, pageSize, pageNumber)
                                .stream().map(entity -> Converter.toDto(entity, PositionHistoryDTO.class))
                                .collect(Collectors.toList()))
                .orElse(null);
        Long careerStepsCount = careerDAO.getCareerStepCountByPositionId(positionId);

        LOGGER.info("CareerSteps for position with id={} found successfully!", positionId);

        return new PageInfo<>(career, pageNumber, pageSize, careerStepsCount);
    }

    @Override
    public void closeDao() {
        careerDAO.close();
        employeeDAO.close();
        positionDAO.close();
    }
}
