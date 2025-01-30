package by.itacademy.jd2.servlet.converter.impl;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.entity.embedded.PersonData;
import by.itacademy.jd2.servlet.converter.FromHttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class RelativeHttpConverter implements FromHttpRequestConverter<RelativeDTO> {
    @Override
    public RelativeDTO convert(HttpServletRequest req) {
        return RelativeDTO.builder()
                .id(ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID))
                .personData(PersonData.builder()
                        .surname(ServletUtil.getParamString(req, ConstantParamAndAttribute.SURNAME))
                        .name(ServletUtil.getParamString(req, ConstantParamAndAttribute.NAME))
                        .patronymic(ServletUtil.getParamString(req, ConstantParamAndAttribute.PATRONYMIC))
                        .birthday(ServletUtil.getParamDate(req, ConstantParamAndAttribute.BIRTHDAY))
                        .build())
                .typeKinship(ServletUtil.getParamString(req, ConstantParamAndAttribute.TYPE_KINSHIP))
                .employeeId(ServletUtil.getParamLong(req, ConstantParamAndAttribute.EMPLOYEE_ID))
                .build();
    }
}
