package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.dto.EmployeeItemDTO;

public class EmployeeItemConverter implements ToDtoConverter<EmployeeEntity, EmployeeItemDTO> {
    @Override
    public EmployeeItemDTO toDto(EmployeeEntity entity) {
        if (entity != null) {
            return EmployeeItemDTO.builder()
                    .id(entity.getId())
                    .surname(entity.getPersonData().getSurname())
                    .name(entity.getPersonData().getName())
                    .patronymic(entity.getPersonData().getPatronymic())
                    .build();
        }

        return null;
    }
}
