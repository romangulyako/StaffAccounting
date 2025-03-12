package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.dto.PositionItemDTO;

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
