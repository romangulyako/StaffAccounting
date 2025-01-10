package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.entity.embedded.Address;
import by.itacademy.jd2.entity.embedded.PersonData;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class EmployeeHttpConverter implements FromHttpRequestConverter<EmployeeDTO> {
    @Override
    public EmployeeDTO convert(HttpServletRequest req) {
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
                        .apartment(ParseUtil.parseInt(ServletUtil.getParam(req,
                                ConstantParamAndAttribute.RESIDENCE_APARTMENT)))
                        .build())
                .phone(ServletUtil.getParam(req, ConstantParamAndAttribute.PHONE))
                .build();
    }
}
