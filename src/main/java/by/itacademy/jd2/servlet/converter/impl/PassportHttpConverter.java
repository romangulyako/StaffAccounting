package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.entity.embedded.Address;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class PassportHttpConverter implements FromHttpRequestConverter<PassportDTO> {
    @Override
    public PassportDTO convert(HttpServletRequest req) {
        return PassportDTO.builder()
                .id(ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID))
                .series(ServletUtil.getParamString(req, ConstantParamAndAttribute.SERIES))
                .number(ServletUtil.getParamString(req, ConstantParamAndAttribute.NUMBER))
                .identificationNumber(ServletUtil.getParamString(req, ConstantParamAndAttribute.IDENTIFICATION_NUMBER))
                .registrationAddress(Address.builder()
                        .city(ServletUtil.getParamString(req, ConstantParamAndAttribute.REGISTRATION_CITY))
                        .street(ServletUtil.getParamString(req, ConstantParamAndAttribute.REGISTRATION_STREET))
                        .house(ServletUtil.getParamString(req, ConstantParamAndAttribute.REGISTRATION_HOUSE))
                        .apartment(ServletUtil.getParamInt(req,
                                ConstantParamAndAttribute.REGISTRATION_APARTMENT))
                        .build())
                .publisher(ServletUtil.getParamString(req, ConstantParamAndAttribute.PUBLISHER))
                .dateIssue(ServletUtil.getParamDate(req,
                        ConstantParamAndAttribute.DATE_ISSUE))
                .dateEndAction(ServletUtil.getParamDate(req,
                        ConstantParamAndAttribute.DATE_END_ACTION))
                .build();
    }
}
