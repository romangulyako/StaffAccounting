package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.entity.PassportEntity;

public class PassportConverter implements
        ToDtoConverter<PassportEntity, PassportDTO>, ToEntityConverter<PassportEntity, PassportDTO> {
    @Override
    public PassportDTO toDto(PassportEntity entity) {
        if (entity != null) {
            return PassportDTO.builder()
                    .id(entity.getId())
                    .series(entity.getSeries())
                    .number(entity.getNumber())
                    .identificationNumber(entity.getIdentificationNumber())
                    .registrationAddress(entity.getRegistrationAddress())
                    .publisher(entity.getPublisher())
                    .dateIssue(entity.getDateIssue())
                    .dateEndAction(entity.getDateEndAction())
                    .build();
        }

        return null;
    }

    @Override
    public PassportEntity toEntity(PassportDTO dto) {
        if (dto != null) {
            return PassportEntity.builder()
                    .id(dto.getId())
                    .series(dto.getSeries())
                    .number(dto.getNumber())
                    .identificationNumber(dto.getIdentificationNumber())
                    .registrationAddress(dto.getRegistrationAddress())
                    .publisher(dto.getPublisher())
                    .dateIssue(dto.getDateIssue())
                    .dateEndAction(dto.getDateEndAction())
                    .build();
        }

        return null;
    }
}
