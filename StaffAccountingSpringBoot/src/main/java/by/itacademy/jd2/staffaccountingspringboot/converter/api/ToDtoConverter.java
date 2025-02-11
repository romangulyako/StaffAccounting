package by.itacademy.jd2.staffaccountingspringboot.converter.api;

public interface ToDtoConverter<ENTITY, DTO> {
    DTO toDto(ENTITY entity);
}
