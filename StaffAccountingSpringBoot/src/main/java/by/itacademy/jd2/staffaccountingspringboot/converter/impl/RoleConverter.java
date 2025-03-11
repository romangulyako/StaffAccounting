package by.itacademy.jd2.staffaccountingspringboot.converter.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToDtoConverter;
import by.itacademy.jd2.staffaccountingspringboot.dto.RoleDTO;
import by.itacademy.jd2.staffaccountingspringboot.entity.RoleEntity;

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
