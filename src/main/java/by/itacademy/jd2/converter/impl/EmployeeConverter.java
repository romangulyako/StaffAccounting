package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.entity.EmployeeEntity;

import java.util.Comparator;

public class EmployeeConverter
        implements ToDtoConverter<EmployeeEntity, EmployeeDTO>, ToEntityConverter<EmployeeEntity, EmployeeDTO> {
    @Override
    public EmployeeDTO toDto(EmployeeEntity entity) {
        if (entity != null) {
            String position = entity.getCareer().stream()
                    .max(Comparator.comparing(CareerStepEntity::getDateOfAppointment))
                    .map(careerStep -> careerStep.getPosition().getName() + " "
                            + careerStep.getPosition().getDepartment().getGenitiveCaseName())
                    .orElse(null);
            return EmployeeDTO.builder()
                    .id(entity.getId())
                    .personData(entity.getPersonData())
                    .homeAddress(entity.getHomeAddress())
                    .phone(entity.getPhone())
                    .passport(new PassportConverter().toDto(entity.getPassport()))
                    .positionName(position)
                    .build();
        }

        return null;
    }

    @Override
    public EmployeeEntity toEntity(EmployeeDTO dto) {
        if (dto != null) {
            return EmployeeEntity.builder()
                    .id(dto.getId())
                    .personData(dto.getPersonData())
                    .homeAddress(dto.getHomeAddress())
                    .phone(dto.getPhone())
                    .passport(new PassportConverter().toEntity(dto.getPassport()))
                    .build();
        }

        return null;
    }
}
