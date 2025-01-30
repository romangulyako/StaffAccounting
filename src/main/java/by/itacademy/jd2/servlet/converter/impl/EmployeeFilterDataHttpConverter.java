package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeFilterData;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class EmployeeFilterDataHttpConverter implements FromHttpRequestConverter<EmployeeFilterData> {
    @Override
    public EmployeeFilterData convert(HttpServletRequest req) {
        /*String departmentIdParam = ServletUtil.getParamString(req, ConstantParamAndAttribute.DEPARTMENT_ID);
        if ("null".equals(departmentIdParam)) {
            departmentIdParam = null;
        }*/

        return EmployeeFilterData.builder()
                .surname(ServletUtil.getParamString(req, ConstantParamAndAttribute.SURNAME))
                .name(ServletUtil.getParamString(req, ConstantParamAndAttribute.NAME))
                .patronymic(ServletUtil.getParamString(req, ConstantParamAndAttribute.PATRONYMIC))
                .age(ServletUtil.getParamInt(req, ConstantParamAndAttribute.AGE))
                .departmentId(ServletUtil.getParamLong(req, ConstantParamAndAttribute.DEPARTMENT_ID))
                .build();
    }
}
