package by.itacademy.jd2.staffaccountingspringboot.converter.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToDtoConverter;
import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToEntityConverter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.CareerStepEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.EmployeeDTO;

import java.util.Comparator;

public class EmployeeConverter
        implements ToDtoConverter<EmployeeEntity, EmployeeDTO>, ToEntityConverter<EmployeeEntity, EmployeeDTO> {
    @Override
    public EmployeeDTO toDto(EmployeeEntity entity) {
        if (entity != null) {
            String position = entity.getCareer().stream()
                    .filter(CareerStepEntity::isCurrent)
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
