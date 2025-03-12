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
import by.itacademy.jd2.converter.impl.PositionHistoryConverter;
import by.itacademy.jd2.converter.impl.PositionItemConverter;
import by.itacademy.jd2.converter.impl.RelativeConverter;
import by.itacademy.jd2.converter.impl.RoleConverter;
import by.itacademy.jd2.converter.impl.UserConverter;
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
import by.itacademy.jd2.dto.RoleDTO;
import by.itacademy.jd2.dto.UserDTO;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.entity.EducationEntity;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.MaritalStatusEntity;
import by.itacademy.jd2.entity.PassportEntity;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.entity.RelativeEntity;
import by.itacademy.jd2.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

public class Converter {
    private static final Map<Class<?>, ToDtoConverter<?, ?>> TO_DTO_CONVERTERS = new HashMap<>();
    private static final Map<Class<?>, ToEntityConverter<?, ?>> TO_ENTITY_CONVERTERS = new HashMap<>();

    static {
        TO_DTO_CONVERTERS.put(CareerStepGetDTO.class, new CareerStepGetConverter());
        TO_DTO_CONVERTERS.put(DepartmentDTO.class, new DepartmentConverter());
        TO_DTO_CONVERTERS.put(EducationDTO.class, new EducationConverter());
        TO_DTO_CONVERTERS.put(EmployeeDTO.class, new EmployeeConverter());
        TO_DTO_CONVERTERS.put(EmployeeItemDTO.class, new EmployeeItemConverter());
        TO_DTO_CONVERTERS.put(MaritalStatusDTO.class, new MaritalStatusConverter());
        TO_DTO_CONVERTERS.put(PassportDTO.class, new PassportConverter());
        TO_DTO_CONVERTERS.put(PositionDTO.class, new PositionConverter());
        TO_DTO_CONVERTERS.put(PositionHistoryDTO.class, new PositionHistoryConverter());
        TO_DTO_CONVERTERS.put(PositionItemDTO.class, new PositionItemConverter());
        TO_DTO_CONVERTERS.put(RelativeDTO.class, new RelativeConverter());
        TO_DTO_CONVERTERS.put(DepartmentItemDTO.class, new DepartmentItemConverter());
        TO_DTO_CONVERTERS.put(UserDTO.class, new UserConverter());
        TO_DTO_CONVERTERS.put(RoleDTO.class, new RoleConverter());

        TO_ENTITY_CONVERTERS.put(CareerStepEntity.class, new CareerStepSaveConverter());
        TO_ENTITY_CONVERTERS.put(DepartmentEntity.class, new DepartmentConverter());
        TO_ENTITY_CONVERTERS.put(EducationEntity.class, new EducationConverter());
        TO_ENTITY_CONVERTERS.put(EmployeeEntity.class, new EmployeeConverter());
        TO_ENTITY_CONVERTERS.put(MaritalStatusEntity.class, new MaritalStatusConverter());
        TO_ENTITY_CONVERTERS.put(PassportEntity.class, new PassportConverter());
        TO_ENTITY_CONVERTERS.put(PositionEntity.class, new PositionConverter());
        TO_ENTITY_CONVERTERS.put(RelativeEntity.class, new RelativeConverter());
        TO_ENTITY_CONVERTERS.put(UserEntity.class, new UserConverter());
    }

    public static <DTO, ENTITY> DTO toDto(ENTITY entity, Class<DTO> dtoClass) {
        ToDtoConverter<ENTITY, DTO> converter = (ToDtoConverter<ENTITY, DTO>) TO_DTO_CONVERTERS.get(dtoClass);
        return converter.toDto(entity);
    }

    public static <DTO, ENTITY> ENTITY toEntity(DTO dto, Class<ENTITY> entityClass) {
        ToEntityConverter<ENTITY, DTO> converter =
                (ToEntityConverter<ENTITY, DTO>) TO_ENTITY_CONVERTERS.get(entityClass);
        return converter.toEntity(dto);
    }
}
