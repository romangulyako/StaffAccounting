package by.itacademy.jd2.service.impl;

import by.itacademy.jd2.converter.CareerStepConverter;
import by.itacademy.jd2.dao.api.CareerDAO;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.dao.api.PositionDAO;
import by.itacademy.jd2.dao.impl.CareerDaoImpl;
import by.itacademy.jd2.dao.impl.EmployeeDaoImpl;
import by.itacademy.jd2.dao.impl.PositionDaoImpl;
import by.itacademy.jd2.dto.CareerStepGetDTO;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.entity.embedded.CareerStepId;
import by.itacademy.jd2.exception.MoreOneResultException;
import by.itacademy.jd2.service.api.CareerService;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CareerServiceImpl implements CareerService {
    private final CareerDAO careerDAO;
    private final EmployeeDAO employeeDAO;
    private final PositionDAO positionDAO;

    public CareerServiceImpl() {
        this.careerDAO = new CareerDaoImpl();
        this.employeeDAO = new EmployeeDaoImpl();
        this.positionDAO = new PositionDaoImpl();
    }

    @Override
    public void addCareerStep(CareerStepSaveDTO careerStepDTO) {
        EmployeeEntity employeeEntity = employeeDAO.get(careerStepDTO.getEmployeeId());
        PositionEntity positionEntity = positionDAO.get(careerStepDTO.getPositionId());
        CareerStepEntity careerStepEntity = CareerStepConverter.toEntity(careerStepDTO);
        Set<CareerStepEntity> careerStepEntities = employeeEntity.getCareer();
        employeeEntity.getCareer().stream()
                .filter(CareerStepEntity::isCurrent)
                .forEach(status -> status.setCurrent(false));
        careerStepEntity.setEmployee(employeeEntity);
        careerStepEntity.setPosition(positionEntity);
        employeeEntity.getCareer().add(careerStepEntity);
        positionEntity.getHistory().add(careerStepEntity);
        careerDAO.save(careerStepEntity);
    }

    @Override
    public void updateCareerStep(CareerStepSaveDTO careerStepDTO, Serializable id) {
        EmployeeEntity employeeEntity = employeeDAO.get(careerStepDTO.getEmployeeId());
        PositionEntity positionEntity = positionDAO.get(careerStepDTO.getPositionId());
        CareerStepEntity careerStepEntity = CareerStepConverter.toEntity(careerStepDTO);
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
        return CareerStepConverter.toDto(careerDAO.get(id));
    }

    @Override
    public List<CareerStepGetDTO> getCareerOfEmployee(Serializable employeeId) {
       List<CareerStepEntity> entities = careerDAO.getCareerByEmployeeId(employeeId);
       if (entities == null || entities.isEmpty()) {
           return null;
       }

       return entities.stream()
               .map(CareerStepConverter::toDto)
               .collect(Collectors.toList());
    }

    @Override
    public List<CareerStepGetDTO> getPositionHistory(Serializable positionId) {
        List<CareerStepEntity> entities = careerDAO.getPositionHistory(positionId);
        if (entities == null || entities.isEmpty()) {
            return null;
        }

        return entities.stream()
                .map(CareerStepConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CareerStepGetDTO getCurrentPositionOfEmployee(Serializable employeeId) throws MoreOneResultException {
        return CareerStepConverter.toDto(
                careerDAO.getCurrentCareerStepOfEmployee(employeeId));
    }

    @Override
    public void closeDao() {
        careerDAO.close();
        employeeDAO.close();
        positionDAO.close();
    }
}
