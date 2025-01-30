package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class PositionHttpConverter implements FromHttpRequestConverter<PositionDTO> {
    @Override
    public PositionDTO convert(HttpServletRequest req) {
        return PositionDTO.builder()
                .id(ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID))
                .name(ServletUtil.getParamString(req, ConstantParamAndAttribute.NAME))
                .educationLevel(ServletUtil.getParamString(req, ConstantParamAndAttribute.EDUCATION_LEVEL))
                .salary(ServletUtil.getParamDouble(req, ConstantParamAndAttribute.SALARY))
                .departmentId(ServletUtil.getParamLong(req, ConstantParamAndAttribute.DEPARTMENT_ID))
                .build();
    }
}
