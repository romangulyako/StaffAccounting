package by.itacademy.jd2.converter.api;

public interface ToDtoConverter<ENTITY, DTO> {
    DTO toDto(ENTITY entity);
}
