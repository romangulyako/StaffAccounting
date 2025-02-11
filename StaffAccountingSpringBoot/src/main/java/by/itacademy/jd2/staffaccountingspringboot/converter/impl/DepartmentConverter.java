package by.itacademy.jd2.staffaccountingspringboot.converter.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToDtoConverter;
import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToEntityConverter;
import by.itacademy.jd2.staffaccountingspringboot.entity.DepartmentEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.DepartmentDTO;
import by.itacademy.jd2.staffaccountingspringboot.entity.PositionEntity;


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
