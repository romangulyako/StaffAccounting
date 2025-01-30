package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class CareerStepHttpConverter implements FromHttpRequestConverter<CareerStepSaveDTO> {
    @Override
    public CareerStepSaveDTO convert(HttpServletRequest req) {
        return CareerStepSaveDTO.builder()
                .id(ServletUtil.getParamLong(req, ConstantParamAndAttribute.CAREER_ID))
                .employeeId(ServletUtil.getParamLong(req, ConstantParamAndAttribute.EMPLOYEE_ID))
                .positionId(ServletUtil.getParamLong(req, ConstantParamAndAttribute.NEW_POSITION_ID))
                .dateOfAppointment(ServletUtil.getParamDate(req, ConstantParamAndAttribute.NEW_DATE_OF_APPOINTMENT))
                .orderAppointment(ServletUtil.getParamString(req, ConstantParamAndAttribute.ORDER_APPOINTMENT))
                .dateOfLiberation(ServletUtil.getParamDate(req, ConstantParamAndAttribute.DATE_OF_LIBERATION))
                .orderLiberation(ServletUtil.getParamString(req, ConstantParamAndAttribute.ORDER_LIBERATION))
                .build();
    }
}
