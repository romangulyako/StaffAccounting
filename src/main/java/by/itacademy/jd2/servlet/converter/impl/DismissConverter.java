package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DismissDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class DismissConverter implements FromHttpRequestConverter<DismissDTO> {
    @Override
    public DismissDTO convert(HttpServletRequest req) {
        return DismissDTO.builder()
                .employeeId(ParseUtil.parseLong(ServletUtil.getParam(req,
                        ConstantParamAndAttribute.EMPLOYEE_ID)))
                .dateOfDismiss(ParseUtil.parseDate(ServletUtil.getParam(req,
                        ConstantParamAndAttribute.DATE_DISMISS)))
                .orderDismiss(ServletUtil.getParam(req,
                        ConstantParamAndAttribute.ORDER_DISMISS))
                .build();
    }
}
