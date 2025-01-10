package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class DepartmentHttpConverter implements FromHttpRequestConverter<DepartmentDTO> {
    @Override
    public DepartmentDTO convert(HttpServletRequest req) {
        return DepartmentDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)))
                .name(ServletUtil.getParam(req, ConstantParamAndAttribute.NAME))
                .genitiveCaseName(ServletUtil.getParam(req, ConstantParamAndAttribute.GENITIVE_CASE_NAME))
                .description(ServletUtil.getParam(req, ConstantParamAndAttribute.DESCRIPTION))
                .build();
    }
}
