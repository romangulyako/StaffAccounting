package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.dto.CareerStepSaveDTO;

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
