package by.itacademy.jd2.converter;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.EmployeeItemDTO;
import by.itacademy.jd2.entity.EmployeeEntity;
import by.itacademy.jd2.entity.embedded.Address;
import by.itacademy.jd2.entity.embedded.PersonData;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class EmployeeConverter {
    public static EmployeeEntity toEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO != null) {
            EmployeeEntity employeeEntity = EmployeeEntity.builder()
                    .id(employeeDTO.getId())
                    .personData(employeeDTO.getPersonData())
                    .homeAddress(employeeDTO.getHomeAddress())
                    .phone(employeeDTO.getPhone())
                    .passport(PassportConverter.toEntity(employeeDTO.getPassport()))
                    .build();
            if (employeeEntity.getPassport() != null) {
                employeeEntity.getPassport().setEmployee(employeeEntity);
            }

            return employeeEntity;
        }

        return null;
    }

    public static EmployeeDTO toDTO(EmployeeEntity employeeEntity) {
        if (employeeEntity != null) {
            return EmployeeDTO.builder()
                    .id(employeeEntity.getId())
                    .personData(employeeEntity.getPersonData())
                    .homeAddress(employeeEntity.getHomeAddress())
                    .phone(employeeEntity.getPhone())
                    .passport(PassportConverter.toDTO(employeeEntity.getPassport()))
                    .build();
        }

        return null;
    }

    public static EmployeeDTO fromHttpRequest(HttpServletRequest req) throws NumberFormatException {
        return EmployeeDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)))
                .personData(PersonData.builder()
                        .surname(ServletUtil.getParam(req, ConstantParamAndAttribute.SURNAME))
                        .name(ServletUtil.getParam(req, ConstantParamAndAttribute.NAME))
                        .patronymic(ServletUtil.getParam(req, ConstantParamAndAttribute.PATRONYMIC))
                        .birthday(ParseUtil.parseDate(ServletUtil.getParam(req, ConstantParamAndAttribute.BIRTHDAY)))
                        .build())
                .homeAddress(Address.builder()
                        .city(ServletUtil.getParam(req, ConstantParamAndAttribute.RESIDENCE_CITY))
                        .street(ServletUtil.getParam(req, ConstantParamAndAttribute.RESIDENCE_STREET))
                        .house(ServletUtil.getParam(req, ConstantParamAndAttribute.RESIDENCE_HOUSE))
                        .apartment(ParseUtil.parseInt(ServletUtil.getParam(req, ConstantParamAndAttribute.RESIDENCE_APARTMENT)))
                        .build())
                .phone(ServletUtil.getParam(req, ConstantParamAndAttribute.PHONE))
                .build();
    }

    public static EmployeeItemDTO toEmployeeItem(EmployeeEntity entity) {
        if (entity != null) {
            return EmployeeItemDTO.builder()
                    .id(entity.getId())
                    .fullName(entity.getPersonData().getSurname() + " "
                    + entity.getPersonData().getName() + " "
                    + entity.getPersonData().getPatronymic())
                    .build();
        }

        return null;
    }
}
