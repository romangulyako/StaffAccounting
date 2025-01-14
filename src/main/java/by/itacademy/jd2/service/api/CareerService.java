package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.CareerStepGetDTO;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.dto.DismissDTO;
import by.itacademy.jd2.dto.PositionHistoryDTO;
import by.itacademy.jd2.service.PageInfo;

import java.io.Serializable;

public interface CareerService extends Service {
    void appointEmployee(CareerStepSaveDTO careerStepDTO);

    void dismissEmployee(DismissDTO dismissDTO);

    void updateCareerStep(CareerStepSaveDTO careerStepDTO, Serializable id);

    void deleteCareerStep(Serializable id);

    CareerStepGetDTO getCareerStep(Serializable id);

    PageInfo<CareerStepGetDTO> getCareerOfEmployeeByPage(Serializable employeeId,
                                                         Integer pageNumber,
                                                         Integer pageSize);

    PageInfo<PositionHistoryDTO> getPositionHistoryByPage(Serializable positionId,
                                                          Integer pageNumber,
                                                          Integer pageSize);
}
