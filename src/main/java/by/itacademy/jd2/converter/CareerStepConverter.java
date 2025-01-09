package by.itacademy.jd2.converter;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.CareerStepGetDTO;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.dto.PositionHistoryDTO;
import by.itacademy.jd2.entity.CareerStepEntity;
import by.itacademy.jd2.entity.embedded.CareerStepId;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class CareerStepConverter {

    private static final String SPACE = " ";

    public static CareerStepEntity toEntity(CareerStepSaveDTO dto) {
        if (dto != null) {
            return CareerStepEntity.builder()
                    .dateOfAppointment(dto.getDateOfAppointment())
                    .order(dto.getOrder())
                    .build();
        }

        return null;
    }

    public static CareerStepGetDTO toDto(CareerStepEntity entity) {
        if (entity != null) {
            return CareerStepGetDTO.builder()
                    .id(CareerStepId.builder()
                            .employee(entity.getEmployee().getId())
                            .position(entity.getPosition().getId())
                            .dateOfAppointment(entity.getDateOfAppointment())
                            .build())
                    .positionFullName(entity.getPosition().getName() + SPACE
                            + entity.getPosition().getDepartment().getGenitiveCaseName())
                    .order(entity.getOrder())
                    .build();
        }

        return null;
    }

    public static CareerStepSaveDTO fromHttpRequest(HttpServletRequest req) {
        return CareerStepSaveDTO.builder()
                .employeeId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID)))
                .positionId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.NEW_POSITION_ID)))
                .dateOfAppointment(ParseUtil.parseDate(ServletUtil.getParam(req,
                        ConstantParamAndAttribute.NEW_DATE_OF_APPOINTMENT)))
                .order(ServletUtil.getParam(req, ConstantParamAndAttribute.ORDER))
                .build();
    }

    public static CareerStepId toCareerStepId(HttpServletRequest req) {
        return CareerStepId.builder()
                .employee(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID)))
                .position(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.POSITION_ID)))
                .dateOfAppointment(ParseUtil.parseDate(ServletUtil.getParam(req,
                        ConstantParamAndAttribute.DATE_OF_APPOINTMENT)))
                .build();
    }

    public static PositionHistoryDTO toPositionHistoryDTO(CareerStepEntity entity) {
        if (entity != null) {
            return PositionHistoryDTO.builder()
                    .dateOfAppointment(entity.getDateOfAppointment())
                    .order(entity.getOrder())
                    .employeeFullName(entity.getEmployee().getPersonData().getSurname() + SPACE
                    + entity.getEmployee().getPersonData().getName() + SPACE
                    + entity.getEmployee().getPersonData().getPatronymic())
                    .build();
        }

        return null;
    }
}
