package by.itacademy.jd2.converter;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.converter.api.ToEntityConverter;

import by.itacademy.jd2.converter.impl.CareerStepGetConverter;
import by.itacademy.jd2.converter.impl.CareerStepSaveConverter;
import by.itacademy.jd2.converter.impl.DepartmentConverter;
import by.itacademy.jd2.converter.impl.DepartmentItemConverter;
import by.itacademy.jd2.converter.impl.EducationConverter;
import by.itacademy.jd2.converter.impl.EmployeeConverter;
import by.itacademy.jd2.converter.impl.EmployeeItemConverter;
import by.itacademy.jd2.converter.impl.MaritalStatusConverter;
import by.itacademy.jd2.converter.impl.PassportConverter;
import by.itacademy.jd2.converter.impl.PositionConverter;
import by.itacademy.jd2.converter.impl.PositionItemConverter;
import by.itacademy.jd2.converter.impl.RelativeConverter;
import by.itacademy.jd2.converter.impl.PositionHistoryConverter;

import by.itacademy.jd2.dto.CareerStepGetDTO;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.dto.DepartmentItemDTO;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.EmployeeItemDTO;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.dto.PositionHistoryDTO;
import by.itacademy.jd2.dto.PositionItemDTO;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.EducationEntity;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.entity.PassportEntity;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.entity.RelativeEntity;
import by.itacademy.jd2.entity.MaritalStatusEntity;
import by.itacademy.jd2.entity.CareerStepEntity;

import java.util.HashMap;
import java.util.Map;

public class Converter {
    private static Converter instance;
    private static final Map<Class<?>, ToDtoConverter<?, ?>> toDtoConverters = new HashMap<>();
    private static final Map<Class<?>, ToEntityConverter<?, ?>> toEntityConverters = new HashMap<>();

    static  {
        toDtoConverters.put(CareerStepGetDTO.class, new CareerStepGetConverter());
        toDtoConverters.put(DepartmentDTO.class, new DepartmentConverter());
        toDtoConverters.put(EducationDTO.class, new EducationConverter());
        toDtoConverters.put(EmployeeDTO.class, new EmployeeConverter());
        toDtoConverters.put(EmployeeItemDTO.class, new EmployeeItemConverter());
        toDtoConverters.put(MaritalStatusDTO.class, new MaritalStatusConverter());
        toDtoConverters.put(PassportDTO.class, new PassportConverter());
        toDtoConverters.put(PositionDTO.class, new PositionConverter());
        toDtoConverters.put(PositionHistoryDTO.class, new PositionHistoryConverter());
        toDtoConverters.put(PositionItemDTO.class, new PositionItemConverter());
        toDtoConverters.put(RelativeDTO.class, new RelativeConverter());
        toDtoConverters.put(DepartmentItemDTO.class, new DepartmentItemConverter());

        toEntityConverters.put(CareerStepEntity.class, new CareerStepSaveConverter());
        toEntityConverters.put(DepartmentEntity.class, new DepartmentConverter());
        toEntityConverters.put(EducationEntity.class, new EducationConverter());
        toEntityConverters.put(EmployeeEntity.class, new EmployeeConverter());
        toEntityConverters.put(MaritalStatusEntity.class, new MaritalStatusConverter());
        toEntityConverters.put(PassportEntity.class, new PassportConverter());
        toEntityConverters.put(PositionEntity.class, new PositionConverter());
        toEntityConverters.put(RelativeEntity.class, new RelativeConverter());
    }

    public static  <DTO, ENTITY> DTO toDto(ENTITY entity, Class<DTO> dtoClass) {
        ToDtoConverter<ENTITY, DTO> converter = (ToDtoConverter<ENTITY, DTO>) toDtoConverters.get(dtoClass);
        return converter.toDto(entity);
    }

    public static  <DTO, ENTITY> ENTITY toEntity(DTO dto, Class<ENTITY> entityClass) {
        ToEntityConverter<ENTITY, DTO> converter = (ToEntityConverter<ENTITY, DTO>) toEntityConverters.get(entityClass);
        return converter.toEntity(dto);
    }
}
