package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.entity.EducationEntity;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.dto.EducationDTO;

public class EducationConverter
        implements ToDtoConverter<EducationEntity, EducationDTO>, ToEntityConverter<EducationEntity, EducationDTO> {
    @Override
    public EducationDTO toDto(EducationEntity entity) {
        if (entity != null) {
            return EducationDTO.builder()
                    .id(entity.getId())
                    .educationLevel(entity.getEducationLevel())
                    .institution(entity.getInstitution())
                    .faculty(entity.getFaculty())
                    .specialization(entity.getSpecialization())
                    .dateStart(entity.getDateStart())
                    .dateEnd(entity.getDateEnd())
                    .employeeId(entity.getEmployee().getId())
                    .build();
        }

        return null;
    }

    @Override
    public EducationEntity toEntity(EducationDTO dto) {
        if (dto != null) {
            return EducationEntity.builder()
                    .id(dto.getId())
                    .educationLevel(dto.getEducationLevel())
                    .institution(dto.getInstitution())
                    .faculty(dto.getFaculty())
                    .specialization(dto.getSpecialization())
                    .dateStart(dto.getDateStart())
                    .dateEnd(dto.getDateEnd())
                    .employee(EmployeeEntity.builder()
                            .id(dto.getEmployeeId())
                            .build())
                    .build();
        }

        return null;
    }
}
