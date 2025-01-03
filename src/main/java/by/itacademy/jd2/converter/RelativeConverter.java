package by.itacademy.jd2.converter;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.entity.RelativeEntity;
import by.itacademy.jd2.entity.embedded.PersonData;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class RelativeConverter {
    public static RelativeEntity toEntity(RelativeDTO dto) {
        if (dto != null) {
            return RelativeEntity.builder()
                    .id(dto.getId())
                    .personData(dto.getPersonData())
                    .typeKinship(dto.getTypeKinship())
                    .build();
        }

        return null;
    }

    public static RelativeDTO toDTO(RelativeEntity entity) {
        if (entity != null) {
            return RelativeDTO.builder()
                    .id(entity.getId())
                    .personData(entity.getPersonData())
                    .typeKinship(entity.getTypeKinship())
                    .employeeId(entity.getEmployee().getId())
                    .build();
        }

        return null;
    }

    public static RelativeDTO fromHttpRequest(HttpServletRequest req) {
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
