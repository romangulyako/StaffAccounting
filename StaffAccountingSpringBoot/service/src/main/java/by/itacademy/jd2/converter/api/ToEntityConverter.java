package by.itacademy.jd2.converter.api;

public interface ToEntityConverter<ENTITY, DTO> {
    ENTITY toEntity(DTO dto);
}
