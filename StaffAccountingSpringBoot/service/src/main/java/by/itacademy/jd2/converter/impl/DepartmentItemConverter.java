package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.dto.DepartmentItemDTO;

public class DepartmentItemConverter implements ToDtoConverter<DepartmentEntity, DepartmentItemDTO> {
    @Override
    public DepartmentItemDTO toDto(DepartmentEntity entity) {
        if (entity != null) {
            return DepartmentItemDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
        }

        return null;
    }
}
