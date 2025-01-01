package by.itacademy.jd2.converter;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.repository.PassportEntity;
import by.itacademy.jd2.repository.embedded.Address;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class PassportConverter {
    public static PassportEntity toEntity(PassportDTO passportDTO) {
        if (passportDTO != null) {
            return PassportEntity.builder()
                    .id(passportDTO.getId())
                    .series(passportDTO.getSeries())
                    .number(passportDTO.getNumber())
                    .identificationNumber(passportDTO.getIdentificationNumber())
                    .registrationAddress(passportDTO.getRegistrationAddress())
                    .publisher(passportDTO.getPublisher())
                    .dateIssue(passportDTO.getDateIssue())
                    .dateEndAction(passportDTO.getDateEndAction())
                    .build();
        }

        return null;
    }

    public static PassportDTO toDTO(PassportEntity passportEntity) {
        if (passportEntity != null) {
            return PassportDTO.builder()
                    .id(passportEntity.getId())
                    .series(passportEntity.getSeries())
                    .number(passportEntity.getNumber())
                    .identificationNumber(passportEntity.getIdentificationNumber())
                    .registrationAddress(passportEntity.getRegistrationAddress())
                    .publisher(passportEntity.getPublisher())
                    .dateIssue(passportEntity.getDateIssue())
                    .dateEndAction(passportEntity.getDateEndAction())
                    .build();
        }

        return null;
    }

    public static PassportDTO fromHttpRequest(HttpServletRequest req) {
        return PassportDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)))
                .series(ServletUtil.getParam(req, ConstantParamAndAttribute.SERIES))
                .number(ServletUtil.getParam(req, ConstantParamAndAttribute.NUMBER))
                .identificationNumber(ServletUtil.getParam(req, ConstantParamAndAttribute.IDENTIFICATION_NUMBER))
                .registrationAddress(Address.builder()
                        .city(ServletUtil.getParam(req, ConstantParamAndAttribute.REGISTRATION_CITY))
                        .street(ServletUtil.getParam(req, ConstantParamAndAttribute.REGISTRATION_STREET))
                        .house(ServletUtil.getParam(req, ConstantParamAndAttribute.REGISTRATION_HOUSE))
                        .apartment(ParseUtil.parseInt(ServletUtil.getParam(req, ConstantParamAndAttribute.REGISTRATION_APARTMENT)))
                        .build())
                .publisher(ServletUtil.getParam(req, ConstantParamAndAttribute.PUBLISHER))
                .dateIssue(ParseUtil.parseDate(ServletUtil.getParam(req, ConstantParamAndAttribute.DATE_ISSUE)))
                .dateEndAction(ParseUtil.parseDate(ServletUtil.getParam(req, ConstantParamAndAttribute.DATE_END_ACTION)))
                .build();
    }
}
