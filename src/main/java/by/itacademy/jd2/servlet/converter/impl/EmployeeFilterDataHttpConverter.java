package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeFilterData;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class EmployeeFilterDataHttpConverter implements FromHttpRequestConverter<EmployeeFilterData> {
    @Override
    public EmployeeFilterData convert(HttpServletRequest req) {
        String departmentIdParam = ServletUtil.getParam(req, ConstantParamAndAttribute.DEPARTMENT_ID);
        if ("null".equals(departmentIdParam)) {
            departmentIdParam = null;
        }
        return EmployeeFilterData.builder()
                .surname(ServletUtil.getParam(req, ConstantParamAndAttribute.SURNAME))
                .name(ServletUtil.getParam(req, ConstantParamAndAttribute.NAME))
                .patronymic(ServletUtil.getParam(req, ConstantParamAndAttribute.PATRONYMIC))
                .age(ParseUtil.parseInt(ServletUtil.getParam(req, ConstantParamAndAttribute.AGE)))
                .departmentId(ParseUtil.parseLong(departmentIdParam))
                .build();
    }
}
