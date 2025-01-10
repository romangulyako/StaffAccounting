package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class PositionHttpConverter implements FromHttpRequestConverter<PositionDTO> {
    @Override
    public PositionDTO convert(HttpServletRequest req) {
        return PositionDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)))
                .name(ServletUtil.getParam(req, ConstantParamAndAttribute.NAME))
                .educationLevel(ServletUtil.getParam(req, ConstantParamAndAttribute.EDUCATION_LEVEL))
                .salary(ParseUtil.parseDouble(ServletUtil.getParam(req, ConstantParamAndAttribute.SALARY)))
                .departmentId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.DEPARTMENT_ID)))
                .build();
    }
}
