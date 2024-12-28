package by.itacademy.jd2.converter;

import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.repository.EmployeeEntity;

public class EmployeeConverter {
    public static EmployeeEntity toEntity(EmployeeDTO employeeDTO) {
        return EmployeeEntity.builder()
                .id(employeeDTO.getId())
                .personData(employeeDTO.getPersonData())
                .homeAddress(employeeDTO.getHomeAddress())
                .phone(employeeDTO.getPhone())
                .build();
    }

    public static EmployeeDTO toDTO(EmployeeEntity employeeEntity) {
        return EmployeeDTO.builder()
                .id(employeeEntity.getId())
                .personData(employeeEntity.getPersonData())
                .homeAddress(employeeEntity.getHomeAddress())
                .phone(employeeEntity.getPhone())
                .build();
    }
}
