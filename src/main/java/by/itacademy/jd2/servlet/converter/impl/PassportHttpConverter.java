package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.entity.embedded.Address;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class PassportHttpConverter implements FromHttpRequestConverter<PassportDTO> {
    @Override
    public PassportDTO convert(HttpServletRequest req) {
        return PassportDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)))
                .series(ServletUtil.getParam(req, ConstantParamAndAttribute.SERIES))
                .number(ServletUtil.getParam(req, ConstantParamAndAttribute.NUMBER))
                .identificationNumber(ServletUtil.getParam(req, ConstantParamAndAttribute.IDENTIFICATION_NUMBER))
                .registrationAddress(Address.builder()
                        .city(ServletUtil.getParam(req, ConstantParamAndAttribute.REGISTRATION_CITY))
                        .street(ServletUtil.getParam(req, ConstantParamAndAttribute.REGISTRATION_STREET))
                        .house(ServletUtil.getParam(req, ConstantParamAndAttribute.REGISTRATION_HOUSE))
                        .apartment(ParseUtil.parseInt(ServletUtil.getParam(req,
                                ConstantParamAndAttribute.REGISTRATION_APARTMENT)))
                        .build())
                .publisher(ServletUtil.getParam(req, ConstantParamAndAttribute.PUBLISHER))
                .dateIssue(ParseUtil.parseDate(ServletUtil.getParam(req,
                        ConstantParamAndAttribute.DATE_ISSUE)))
                .dateEndAction(ParseUtil.parseDate(ServletUtil.getParam(req,
                        ConstantParamAndAttribute.DATE_END_ACTION)))
                .build();
    }
}
