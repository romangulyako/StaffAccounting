package by.itacademy.jd2.converter;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.CareerStepDTO;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class CareerStepConverter {
    public static CareerStepEntity toEntity(CareerStepDTO dto) {
        if (dto != null) {
            return CareerStepEntity.builder()
                    .dateOfAppointment(dto.getDateOfAppointment())
                    .order(dto.getOrder())
                    .build();
        }

        return null;
    }

    public static CareerStepDTO toDto(CareerStepEntity entity) {
        if (entity != null) {
            return CareerStepDTO.builder()
                    .employeeId(entity.getEmployee().getId())
                    .positionId(entity.getPosition().getId())
                    .dateOfAppointment(entity.getDateOfAppointment())
                    .order(entity.getOrder())
                    .build();
        }

        return null;
    }

    public static CareerStepDTO fromHttpRequest(HttpServletRequest req) {
        return CareerStepDTO.builder()
                .employeeId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID)))
                .positionId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.POSITION_ID)))
                .dateOfAppointment(ParseUtil.parseDate(ServletUtil.getParam(req, ConstantParamAndAttribute.DATE_OF_APPOINTMENT)))
                .order(ServletUtil.getParam(req, ConstantParamAndAttribute.ORDER))
                .build();
    }
}
