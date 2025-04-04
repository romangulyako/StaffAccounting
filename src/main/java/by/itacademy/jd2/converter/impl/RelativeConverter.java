package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.entity.RelativeEntity;

public class RelativeConverter implements
        ToDtoConverter<RelativeEntity, RelativeDTO>, ToEntityConverter<RelativeEntity, RelativeDTO> {
    @Override
    public RelativeDTO toDto(RelativeEntity entity) {
        if (entity != null) {
            return RelativeDTO.builder()
                    .id(entity.getId())
                    .personData(entity.getPersonData())
                    .typeKinship(entity.getTypeKinship())
                    .employeeId(entity.getEmployee().getId())
                    .build();
        }

        return null;
    }

    @Override
    public RelativeEntity toEntity(RelativeDTO dto) {
        if (dto != null) {
            return RelativeEntity.builder()
                    .id(dto.getId())
                    .personData(dto.getPersonData())
                    .typeKinship(dto.getTypeKinship())
                    .build();
        }

        return null;
    }
}
