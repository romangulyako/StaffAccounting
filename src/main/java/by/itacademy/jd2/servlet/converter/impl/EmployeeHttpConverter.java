package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.entity.embedded.Address;
import by.itacademy.jd2.entity.embedded.PersonData;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class EmployeeHttpConverter implements FromHttpRequestConverter<EmployeeDTO> {
    @Override
    public EmployeeDTO convert(HttpServletRequest req) {
        return EmployeeDTO.builder()
                .id(ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID))
                .personData(PersonData.builder()
                        .surname(ServletUtil.getParamString(req, ConstantParamAndAttribute.SURNAME))
                        .name(ServletUtil.getParamString(req, ConstantParamAndAttribute.NAME))
                        .patronymic(ServletUtil.getParamString(req, ConstantParamAndAttribute.PATRONYMIC))
                        .birthday(ServletUtil.getParamDate(req, ConstantParamAndAttribute.BIRTHDAY))
                        .build())
                .homeAddress(Address.builder()
                        .city(ServletUtil.getParamString(req, ConstantParamAndAttribute.RESIDENCE_CITY))
                        .street(ServletUtil.getParamString(req, ConstantParamAndAttribute.RESIDENCE_STREET))
                        .house(ServletUtil.getParamString(req, ConstantParamAndAttribute.RESIDENCE_HOUSE))
                        .apartment(ServletUtil.getParamInt(req,
                                ConstantParamAndAttribute.RESIDENCE_APARTMENT))
                        .build())
                .phone(ServletUtil.getParamString(req, ConstantParamAndAttribute.PHONE))
                .build();
    }
}
