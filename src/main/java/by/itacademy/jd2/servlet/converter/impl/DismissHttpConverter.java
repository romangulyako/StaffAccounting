package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DismissDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class DismissHttpConverter implements FromHttpRequestConverter<DismissDTO> {
    @Override
    public DismissDTO convert(HttpServletRequest req) {
        return DismissDTO.builder()
                .employeeId(ServletUtil.getParamLong(req, ConstantParamAndAttribute.EMPLOYEE_ID))
                .dateOfDismiss(ServletUtil.getParamDate(req, ConstantParamAndAttribute.DATE_DISMISS))
                .orderDismiss(ServletUtil.getParamString(req, ConstantParamAndAttribute.ORDER_DISMISS))
                .build();
    }
}
