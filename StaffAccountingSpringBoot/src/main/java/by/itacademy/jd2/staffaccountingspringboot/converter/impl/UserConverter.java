package by.itacademy.jd2.staffaccountingspringboot.converter.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToDtoConverter;
import by.itacademy.jd2.staffaccountingspringboot.converter.api.ToEntityConverter;
import by.itacademy.jd2.staffaccountingspringboot.dto.UserDTO;
import by.itacademy.jd2.staffaccountingspringboot.entity.RoleEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.UserEntity;

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
