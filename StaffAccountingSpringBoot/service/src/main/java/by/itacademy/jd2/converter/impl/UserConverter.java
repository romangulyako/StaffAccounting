package by.itacademy.jd2.converter.impl;

import by.itacademy.jd2.converter.api.ToDtoConverter;
import by.itacademy.jd2.converter.api.ToEntityConverter;
import by.itacademy.jd2.dto.UserDTO;
import by.itacademy.jd2.entity.RoleEntity;
import by.itacademy.jd2.entity.UserEntity;

import java.util.stream.Collectors;

public class UserConverter implements
        ToDtoConverter<UserEntity, UserDTO>, ToEntityConverter<UserEntity, UserDTO> {
    @Override
    public UserDTO toDto(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .authorities(userEntity.getAuthorities().stream()
                        .map(RoleEntity::getAuthority)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public UserEntity toEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .build();
    }
}
