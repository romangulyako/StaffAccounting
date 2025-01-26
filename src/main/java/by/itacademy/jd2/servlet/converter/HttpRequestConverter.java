package by.itacademy.jd2.servlet.converter;

import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.dto.DismissDTO;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.EmployeeFilterData;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.servlet.converter.impl.CareerStepHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.DepartmentHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.DismissHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.EducationHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.EmployeeFilterDataHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.EmployeeHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.MaritalStatusHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.PassportHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.PositionHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.RelativeHttpConverter;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestConverter {
    private static final Map<Class<?>, FromHttpRequestConverter<?>> CONVERTERS = new HashMap<>();

    static {
        CONVERTERS.put(CareerStepSaveDTO.class, new CareerStepHttpConverter());
        CONVERTERS.put(DepartmentDTO.class, new DepartmentHttpConverter());
        CONVERTERS.put(EducationDTO.class, new EducationHttpConverter());
        CONVERTERS.put(EmployeeDTO.class, new EmployeeHttpConverter());
        CONVERTERS.put(MaritalStatusDTO.class, new MaritalStatusHttpConverter());
        CONVERTERS.put(PassportDTO.class, new PassportHttpConverter());
        CONVERTERS.put(PositionDTO.class, new PositionHttpConverter());
        CONVERTERS.put(RelativeDTO.class, new RelativeHttpConverter());
        CONVERTERS.put(DismissDTO.class, new DismissHttpConverter());
        CONVERTERS.put(EmployeeFilterData.class, new EmployeeFilterDataHttpConverter());
    }

    public static <DTO> DTO convert(HttpServletRequest req, Class<DTO> dtoClass) {
        FromHttpRequestConverter<DTO> converter = (FromHttpRequestConverter<DTO>) CONVERTERS.get(dtoClass);
        return converter.convert(req);
    }
}
