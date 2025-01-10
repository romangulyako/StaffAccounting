package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.entity.CareerStepEntity;

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
                    .build();
        }

        return null;
    }
}
