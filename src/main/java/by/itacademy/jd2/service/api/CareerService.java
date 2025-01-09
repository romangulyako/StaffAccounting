package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.CareerStepGetDTO;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.dto.PositionHistoryDTO;
import by.itacademy.jd2.exception.MoreOneResultException;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public interface CareerService extends Service {
    void appointEmployee(CareerStepSaveDTO careerStepDTO);
    void dismissEmployee(Long employeeId, Date dateOfDismiss, String order) throws MoreOneResultException;
    void updateCareerStep(CareerStepSaveDTO careerStepDTO, Serializable id);
    void deleteCareerStep(Serializable id);
    CareerStepGetDTO getCareerStep(Serializable id);
    List<CareerStepGetDTO> getCareerOfEmployee(Serializable employeeId);
    List<PositionHistoryDTO> getPositionHistory(Serializable positionId);
    CareerStepGetDTO getCurrentPositionOfEmployee(Serializable employeeId) throws MoreOneResultException;
}
