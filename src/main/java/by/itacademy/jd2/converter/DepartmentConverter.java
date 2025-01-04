package by.itacademy.jd2.converter;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.entity.DepartmentEntity;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class DepartmentConverter {
    public static DepartmentEntity toEntity(DepartmentDTO dto) {
        if (dto != null) {
            return DepartmentEntity.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .genitiveCaseName(dto.getGenitiveCaseName())
                    .description(dto.getDescription())
                    .build();
        }

        return null;
    }

    public static DepartmentDTO toDto(DepartmentEntity entity) {
        if (entity != null) {
            return DepartmentDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .genitiveCaseName(entity.getGenitiveCaseName())
                    .description(entity.getDescription())
                    .build();
        }

        return null;
    }

    public static DepartmentDTO fromHttpRequest(HttpServletRequest req) {
        return DepartmentDTO.builder()
                .id(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)))
                .name(ServletUtil.getParam(req, ConstantParamAndAttribute.NAME))
                .genitiveCaseName(ServletUtil.getParam(req, ConstantParamAndAttribute.GENITIVE_CASE_NAME))
                .description(ServletUtil.getParam(req, ConstantParamAndAttribute.DESCRIPTION))
                .build();
    }
}
