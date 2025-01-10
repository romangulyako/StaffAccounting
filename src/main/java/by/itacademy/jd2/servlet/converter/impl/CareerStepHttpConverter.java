package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class CareerStepHttpConverter implements FromHttpRequestConverter<CareerStepSaveDTO> {
    @Override
    public CareerStepSaveDTO convert(HttpServletRequest req) {
        return CareerStepSaveDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.CAREER_ID)))
                .employeeId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID)))
                .positionId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.NEW_POSITION_ID)))
                .dateOfAppointment(ParseUtil.parseDate(ServletUtil.getParam(req,
                        ConstantParamAndAttribute.NEW_DATE_OF_APPOINTMENT)))
                .orderAppointment(ServletUtil.getParam(req, ConstantParamAndAttribute.ORDER_APPOINTMENT))
                .dateOfLiberation(ParseUtil.parseDate(ServletUtil.getParam(req,
                        ConstantParamAndAttribute.DATE_OF_LIBERATION)))
                .orderLiberation(ServletUtil.getParam(req, ConstantParamAndAttribute.ORDER_LIBERATION))
                .build();
    }
}
