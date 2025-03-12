package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.dto.RoleDTO;
import by.itacademy.jd2.entity.RoleEntity;

public class RoleConverter implements ToDtoConverter<RoleEntity, RoleDTO> {
    @Override
    public RoleDTO toDto(RoleEntity roleEntity) {
        if (roleEntity != null) {
            return RoleDTO.builder()
                    .id(roleEntity.getId())
                    .name(roleEntity.getAuthority())
                    .build();
        }

        return null;
    }
}
