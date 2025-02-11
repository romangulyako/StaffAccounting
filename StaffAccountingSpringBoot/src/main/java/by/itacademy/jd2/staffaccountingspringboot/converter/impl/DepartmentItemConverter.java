package by.itacademy.jd2.staffaccountingspringboot.converter.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToDtoConverter;
import by.itacademy.jd2.staffaccountingspringboot.entity.DepartmentEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.DepartmentItemDTO;

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
