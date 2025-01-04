package by.itacademy.jd2.converter;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.entity.EducationEntity;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class EducationConverter {
    public static EducationEntity toEntity(EducationDTO dto) {
        if (dto != null) {
            return EducationEntity.builder()
                    .id(dto.getId())
                    .educationLevel(dto.getEducationLevel())
                    .institution(dto.getInstitution())
                    .faculty(dto.getFaculty())
                    .specialization(dto.getSpecialization())
                    .dateStart(dto.getDateStart())
                    .dateEnd(dto.getDateEnd())
                    .build();
        }

        return null;
    }

    public static EducationDTO toDto(EducationEntity entity) {
        if (entity != null) {
            return EducationDTO.builder()
                    .id(entity.getId())
                    .educationLevel(entity.getEducationLevel())
                    .institution(entity.getInstitution())
                    .faculty(entity.getFaculty())
                    .specialization(entity.getSpecialization())
                    .dateStart(entity.getDateStart())
                    .dateEnd(entity.getDateEnd())
                    .employeeId(entity.getEmployee().getId())
                    .build();
        }

        return null;
    }

    public static EducationDTO fromHttpRequest(HttpServletRequest req) {
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
