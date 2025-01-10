package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class MaritalStatusHttpConverter implements FromHttpRequestConverter<MaritalStatusDTO> {
    @Override
    public MaritalStatusDTO convert(HttpServletRequest req) {
        return MaritalStatusDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)))
                .status(ServletUtil.getParam(req, ConstantParamAndAttribute.STATUS))
                .registrationDate(ParseUtil.parseDate(ServletUtil.getParam(req,
                        ConstantParamAndAttribute.REGISTRATION_DATE)))
                .document(ServletUtil.getParam(req, ConstantParamAndAttribute.DOCUMENT))
                .isCurrent(ParseUtil.parseBoolean(ServletUtil.getParam(req, ConstantParamAndAttribute.IS_CURRENT)))
                .employeeId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID)))
                .build();
    }
}
