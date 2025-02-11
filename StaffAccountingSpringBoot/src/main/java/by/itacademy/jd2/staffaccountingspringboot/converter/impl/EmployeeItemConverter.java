package by.itacademy.jd2.staffaccountingspringboot.converter.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToDtoConverter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeItemDTO;

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
