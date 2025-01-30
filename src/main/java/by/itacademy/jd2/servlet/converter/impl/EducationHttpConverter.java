package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class EducationHttpConverter implements FromHttpRequestConverter<EducationDTO> {
    @Override
    public EducationDTO convert(HttpServletRequest req) {
        return EducationDTO.builder()
                .id(ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID))
                .educationLevel(ServletUtil.getParamString(req, ConstantParamAndAttribute.EDUCATION_LEVEL))
                .institution(ServletUtil.getParamString(req, ConstantParamAndAttribute.INSTITUTION))
                .faculty(ServletUtil.getParamString(req, ConstantParamAndAttribute.FACULTY))
                .specialization(ServletUtil.getParamString(req, ConstantParamAndAttribute.SPECIALIZATION))
                .dateStart(ServletUtil.getParamDate(req, ConstantParamAndAttribute.DATE_START))
                .dateEnd(ServletUtil.getParamDate(req, ConstantParamAndAttribute.DATE_END))
                .employeeId(ServletUtil.getParamLong(req, ConstantParamAndAttribute.EMPLOYEE_ID))
                .build();
    }
}
