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
import by.itacademy.jd2.service.api.CareerService;
import java.io.Serializable;
import java.util.List;
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

    @Override
    public void updateCareerStep(CareerStepSaveDTO careerStepDTO, Serializable id) {
        EmployeeEntity employeeEntity = employeeDAO.get(careerStepDTO.getEmployeeId());
        PositionEntity positionEntity = positionDAO.get(careerStepDTO.getPositionId());
        CareerStepEntity careerStepEntity = converter.toEntity(careerStepDTO, CareerStepEntity.class);
        careerStepEntity.setEmployee(employeeEntity);
        careerStepEntity.setPosition(positionEntity);
        careerDAO.update(careerStepEntity, id);
    }

    @Override
    public void deleteCareerStep(Serializable id) {
        careerDAO.delete(id);
    }

    @Override
    public CareerStepGetDTO getCareerStep(Serializable id) {
        return converter.toDto(careerDAO.get(id), CareerStepGetDTO.class);
    }

    @Override
    public List<CareerStepGetDTO> getCareerOfEmployee(Serializable employeeId) {
        List<CareerStepEntity> entities = careerDAO.getCareerByEmployeeId(employeeId);
        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities.stream()
                .map(entity -> converter.toDto(entity, CareerStepGetDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PositionHistoryDTO> getPositionHistory(Serializable positionId) {
        List<CareerStepEntity> entities = careerDAO.getPositionHistory(positionId);
        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities.stream()
                .map(entity -> converter.toDto(entity, PositionHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void closeDao() {
        careerDAO.close();
        employeeDAO.close();
        positionDAO.close();
    }
}
