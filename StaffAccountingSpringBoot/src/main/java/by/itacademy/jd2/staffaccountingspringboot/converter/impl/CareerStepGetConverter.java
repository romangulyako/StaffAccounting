package by.itacademy.jd2.staffaccountingspringboot.converter.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToDtoConverter;
import by.itacademy.jd2.staffaccountingspringboot.entity.CareerStepEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.CareerStepGetDTO;

public class CareerStepGetConverter implements ToDtoConverter<CareerStepEntity, CareerStepGetDTO> {
    @Override
    public CareerStepGetDTO toDto(CareerStepEntity entity) {
        if (entity != null) {
            return CareerStepGetDTO.builder()
                    .id(entity.getId())
                    .employeeId(entity.getEmployee().getId())
                    .positionId(entity.getPosition().getId())
                    .dateOfAppointment(entity.getDateOfAppointment())
                    .orderAppointment(entity.getOrderAppointment())
                    .dateOfLiberation(entity.getDateOfLiberationPosition())
                    .orderLiberation(entity.getOrderLiberation())
                    .positionName(entity.getPosition().getName())
                    .departmentGenitiveCaseName(entity.getPosition().getDepartment().getGenitiveCaseName())
                    .build();
        }

        return null;
    }
}
