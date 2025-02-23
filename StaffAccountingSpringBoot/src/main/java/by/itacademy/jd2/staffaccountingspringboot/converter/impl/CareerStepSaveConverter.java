package by.itacademy.jd2.staffaccountingspringboot.converter.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToEntityConverter;
import by.itacademy.jd2.staffaccountingspringboot.entity.CareerStepEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.PositionEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.CareerStepSaveDTO;

public class CareerStepSaveConverter
        implements ToEntityConverter<CareerStepEntity, CareerStepSaveDTO> {
    @Override
    public CareerStepEntity toEntity(CareerStepSaveDTO dto) {
        if (dto != null) {
            return CareerStepEntity.builder()
                    .id(dto.getId())
                    .dateOfAppointment(dto.getDateOfAppointment())
                    .orderAppointment(dto.getOrderAppointment())
                    .dateOfLiberationPosition(dto.getDateOfLiberation())
                    .orderLiberation(dto.getOrderLiberation())
                    .employee(EmployeeEntity.builder()
                            .id(dto.getEmployeeId())
                            .build())
                    .position(PositionEntity.builder()
                            .id(dto.getPositionId())
                            .build())
                    .build();
        }

        return null;
    }
}
