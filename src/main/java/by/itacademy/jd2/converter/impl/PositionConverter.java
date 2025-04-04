package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.entity.PositionEntity;

public class PositionConverter implements
        ToDtoConverter<PositionEntity, PositionDTO>, ToEntityConverter<PositionEntity, PositionDTO> {
    @Override
    public PositionDTO toDto(PositionEntity entity) {
        if (entity != null) {
            return PositionDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .educationLevel(entity.getEducationLevel())
                    .salary(entity.getSalary())
                    .departmentId(entity.getDepartment().getId())
                    .build();
        }

        return null;
    }

    @Override
    public PositionEntity toEntity(PositionDTO dto) {
        if (dto != null) {
            return PositionEntity.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .educationLevel(dto.getEducationLevel())
                    .salary(dto.getSalary())
                    .build();
        }

        return null;
    }
}
