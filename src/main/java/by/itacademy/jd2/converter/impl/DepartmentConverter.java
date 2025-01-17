package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.entity.PositionEntity;

public class DepartmentConverter
        implements ToDtoConverter<DepartmentEntity, DepartmentDTO>, ToEntityConverter<DepartmentEntity, DepartmentDTO> {

    @Override
    public DepartmentDTO toDto(DepartmentEntity entity) {
        if (entity != null) {
            return DepartmentDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .genitiveCaseName(entity.getGenitiveCaseName())
                    .description(entity.getDescription())
                    .actualPositionsCount((int) entity.getPositions().stream()
                            .filter(PositionEntity::getIsActual).count())
                    .reducedPositionsCount((int) entity.getPositions().stream()
                            .filter(position -> !position.getIsActual()).count())
                    .build();
        }

        return null;
    }

    @Override
    public DepartmentEntity toEntity(DepartmentDTO dto) {
        if (dto != null) {
            return DepartmentEntity.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .genitiveCaseName(dto.getGenitiveCaseName())
                    .description(dto.getDescription())
                    .build();
        }

        return null;
    }
}
