package by.itacademy.jd2.staffaccountingspringboot.converter.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToDtoConverter;
import by.itacademy.jd2.staffaccountingspringboot.entity.CareerStepEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.PositionHistoryDTO;

public class PositionHistoryConverter implements ToDtoConverter<CareerStepEntity, PositionHistoryDTO> {
    @Override
    public PositionHistoryDTO toDto(CareerStepEntity entity) {
        if (entity != null) {
            return PositionHistoryDTO.builder()
                    .dateOfAppointment(entity.getDateOfAppointment())
                    .orderAppointment(entity.getOrderAppointment())
                    .dateOfLiberation(entity.getDateOfLiberationPosition())
                    .orderLiberation(entity.getOrderLiberation())
                    .employeeSurname(entity.getEmployee().getPersonData().getSurname())
                    .employeeName(entity.getEmployee().getPersonData().getName())
                    .employeePatronymic(entity.getEmployee().getPersonData().getPatronymic())
                    .build();
        }

        return null;
    }
}
