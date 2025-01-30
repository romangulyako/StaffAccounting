package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class MaritalStatusHttpConverter implements FromHttpRequestConverter<MaritalStatusDTO> {
    @Override
    public MaritalStatusDTO convert(HttpServletRequest req) {
        return MaritalStatusDTO.builder()
                .id(ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID))
                .status(ServletUtil.getParamString(req, ConstantParamAndAttribute.STATUS))
                .registrationDate(ServletUtil.getParamDate(req,
                        ConstantParamAndAttribute.REGISTRATION_DATE))
                .document(ServletUtil.getParamString(req, ConstantParamAndAttribute.DOCUMENT))
                .isCurrent(ServletUtil.getParamBoolean(req, ConstantParamAndAttribute.IS_CURRENT))
                .employeeId(ServletUtil.getParamLong(req, ConstantParamAndAttribute.EMPLOYEE_ID))
                .build();
    }
}
