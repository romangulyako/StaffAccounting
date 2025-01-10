package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.entity.EmployeeEntity;

import javax.persistence.Converter;

public class EmployeeConverter
        implements ToDtoConverter<EmployeeEntity, EmployeeDTO>, ToEntityConverter<EmployeeEntity, EmployeeDTO> {
    @Override
    public EmployeeDTO toDto(EmployeeEntity entity) {
        if (entity != null) {
            return EmployeeDTO.builder()
                    .id(entity.getId())
                    .personData(entity.getPersonData())
                    .homeAddress(entity.getHomeAddress())
                    .phone(entity.getPhone())
                    .passport(new PassportConverter().toDto(entity.getPassport()))
                    .build();
        }

        return null;
    }

    @Override
    public EmployeeEntity toEntity(EmployeeDTO dto) {
        if (dto != null) {
            EmployeeEntity employeeEntity = EmployeeEntity.builder()
                    .id(dto.getId())
                    .personData(dto.getPersonData())
                    .homeAddress(dto.getHomeAddress())
                    .phone(dto.getPhone())
                    .passport(new PassportConverter().toEntity(dto.getPassport()))
                    .build();
            if (employeeEntity.getPassport() != null) {
                employeeEntity.getPassport().setEmployee(employeeEntity);
            }

            return employeeEntity;
        }

        return null;
    }
}
