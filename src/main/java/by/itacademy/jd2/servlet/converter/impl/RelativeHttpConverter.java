package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.entity.embedded.PersonData;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class RelativeHttpConverter implements FromHttpRequestConverter<RelativeDTO> {
    @Override
    public RelativeDTO convert(HttpServletRequest req) {
        return RelativeDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)))
                .personData(PersonData.builder()
                        .surname(ServletUtil.getParam(req, ConstantParamAndAttribute.SURNAME))
                        .name(ServletUtil.getParam(req, ConstantParamAndAttribute.NAME))
                        .patronymic(ServletUtil.getParam(req, ConstantParamAndAttribute.PATRONYMIC))
                        .birthday(ParseUtil.parseDate(ServletUtil.getParam(req, ConstantParamAndAttribute.BIRTHDAY)))
                        .build())
                .typeKinship(ServletUtil.getParam(req, ConstantParamAndAttribute.TYPE_KINSHIP))
                .employeeId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID)))
                .build();
    }
}
