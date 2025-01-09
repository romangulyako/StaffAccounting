package by.itacademy.jd2.converter;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.entity.MaritalStatusEntity;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;

public class MaritalStatusConverter {
     public static MaritalStatusEntity toEntity(MaritalStatusDTO dto) {
         if (dto != null) {
             return MaritalStatusEntity.builder()
                     .id(dto.getId())
                     .status(dto.getStatus())
                     .registrationDate(dto.getRegistrationDate())
                     .document(dto.getDocument())
                     .isCurrent(dto.isCurrent())
                     .build();
         }

         return null;
     }

     public static MaritalStatusDTO toDto(MaritalStatusEntity entity) {
         if (entity != null) {
             return MaritalStatusDTO.builder()
                     .id(entity.getId())
                     .status(entity.getStatus())
                     .registrationDate(entity.getRegistrationDate())
                     .document(entity.getDocument())
                     .isCurrent(entity.isCurrent())
                     .employeeId(entity.getEmployee().getId())
                     .build();
         }

         return null;
     }

     public static MaritalStatusDTO fromHttpRequest(HttpServletRequest request) {
         return MaritalStatusDTO.builder()
                 .id(ParseUtil.parseLong(ServletUtil.getParam(request, ConstantParamAndAttribute.ID)))
                 .status(ServletUtil.getParam(request, ConstantParamAndAttribute.STATUS))
                 .registrationDate(ParseUtil.parseDate(ServletUtil.getParam(request,
                         ConstantParamAndAttribute.REGISTRATION_DATE)))
                 .document(ServletUtil.getParam(request, ConstantParamAndAttribute.DOCUMENT))
                 .isCurrent(ParseUtil.parseBoolean(ServletUtil.getParam(request, ConstantParamAndAttribute.IS_CURRENT)))
                 .employeeId(ParseUtil.parseLong(ServletUtil.getParam(request, ConstantParamAndAttribute.EMPLOYEE_ID)))
                 .build();
     }
}
