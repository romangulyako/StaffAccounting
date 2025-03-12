package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.MaritalStatusEntity;
import by.itacademy.jd2.dto.MaritalStatusDTO;

public class MaritalStatusConverter implements
        ToDtoConverter<MaritalStatusEntity, MaritalStatusDTO>,
        ToEntityConverter<MaritalStatusEntity, MaritalStatusDTO> {
    @Override
    public MaritalStatusDTO toDto(MaritalStatusEntity entity) {
        if (entity != null) {
            return MaritalStatusDTO.builder()
                    .id(entity.getId())
                    .status(entity.getStatus())
                    .registrationDate(entity.getRegistrationDate())
                    .document(entity.getDocument())
                    .employeeId(entity.getEmployee().getId())
                    .build();
        }
        return null;
    }

    @Override
    public MaritalStatusEntity toEntity(MaritalStatusDTO dto) {
        if (dto != null) {
            return MaritalStatusEntity.builder()
                    .id(dto.getId())
                    .status(dto.getStatus())
                    .registrationDate(dto.getRegistrationDate())
                    .document(dto.getDocument())
                    .employee(EmployeeEntity.builder()
                            .id(dto.getEmployeeId())
                            .build())
                    .build();
        }

        return null;
    }
}
