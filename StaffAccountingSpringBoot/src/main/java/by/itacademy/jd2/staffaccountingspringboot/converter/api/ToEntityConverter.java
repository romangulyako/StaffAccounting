package by.itacademy.jd2.staffaccountingspringboot.converter.api;

public interface ToEntityConverter<ENTITY, DTO> {
    ENTITY toEntity(DTO dto);
}
