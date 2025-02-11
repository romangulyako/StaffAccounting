package by.itacademy.jd2.staffaccountingspringboot.converter.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToDtoConverter;
import by.itacademy.jd2.staffaccountingspringboot.entity.PositionEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.PositionItemDTO;

public class PositionItemConverter implements ToDtoConverter<PositionEntity, PositionItemDTO> {
    @Override
    public PositionItemDTO toDto(PositionEntity entity) {
        if (entity != null) {
            return PositionItemDTO.builder()
                    .id(entity.getId())
                    .positionName(entity.getName())
                    .departmentGenitiveCaseName(entity.getDepartment().getGenitiveCaseName())
                    .build();
        }

        return null;
    }
}
