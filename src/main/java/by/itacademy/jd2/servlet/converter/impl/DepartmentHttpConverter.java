package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class DepartmentHttpConverter implements FromHttpRequestConverter<DepartmentDTO> {
    @Override
    public DepartmentDTO convert(HttpServletRequest req) {
        return DepartmentDTO.builder()
                .id(ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID))
                .name(ServletUtil.getParamString(req, ConstantParamAndAttribute.NAME))
                .genitiveCaseName(ServletUtil.getParamString(req, ConstantParamAndAttribute.GENITIVE_CASE_NAME))
                .description(ServletUtil.getParamString(req, ConstantParamAndAttribute.DESCRIPTION))
                .build();
    }
}
