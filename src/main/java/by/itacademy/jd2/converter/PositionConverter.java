package by.itacademy.jd2.converter;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.dto.PositionItemDTO;
import by.itacademy.jd2.entity.PositionEntity;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class PositionConverter {
    public static PositionEntity toEntity(PositionDTO dto) {
        if (dto != null) {
            return PositionEntity.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .educationLevel(dto.getEducationLevel())
                    .salary(dto.getSalary())
                    .build();
        }

        return null;
    }

    public static PositionDTO toDto(PositionEntity entity) {
        if (entity != null) {
            return PositionDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .educationLevel(entity.getEducationLevel())
                    .salary(entity.getSalary())
                    .departmentId(entity.getDepartment().getId())
                    .build();
        }

        return null;
    }

    public static PositionDTO fromHttpRequest(HttpServletRequest req) {
        return PositionDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)))
                .name(ServletUtil.getParam(req, ConstantParamAndAttribute.NAME))
                .educationLevel(ServletUtil.getParam(req, ConstantParamAndAttribute.EDUCATION_LEVEL))
                .salary(ParseUtil.parseDouble(ServletUtil.getParam(req, ConstantParamAndAttribute.SALARY)))
                .departmentId(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.DEPARTMENT_ID)))
                .build();
    }

    public static PositionItemDTO toPositionItem(PositionEntity entity) {
        if (entity != null) {
            return PositionItemDTO.builder()
                    .id(entity.getId())
                    .fullName(entity.getName() + " " + entity.getDepartment().getGenitiveCaseName())
                    .build();
        }

        return null;
    }
}
