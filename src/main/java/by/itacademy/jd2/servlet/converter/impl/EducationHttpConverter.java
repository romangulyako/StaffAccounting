package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class EducationHttpConverter implements FromHttpRequestConverter<EducationDTO> {
    @Override
    public EducationDTO convert(HttpServletRequest req) {
        return EducationDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)))
                .educationLevel(ServletUtil.getParam(req, ConstantParamAndAttribute.EDUCATION_LEVEL))
                .institution(ServletUtil.getParam(req, ConstantParamAndAttribute.INSTITUTION))
                .faculty(ServletUtil.getParam(req, ConstantParamAndAttribute.FACULTY))
                .specialization(ServletUtil.getParam(req, ConstantParamAndAttribute.SPECIALIZATION))
                .dateStart(ParseUtil.parseDate(ServletUtil.getParam(req, ConstantParamAndAttribute.DATE_START)))
                .dateEnd(ParseUtil.parseDate(ServletUtil.getParam(req, ConstantParamAndAttribute.DATE_END)))
                .employeeId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID)))
                .build();
    }
}
