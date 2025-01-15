package by.itacademy.jd2.servlet.converter;

import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.dto.DismissDTO;
import by.itacademy.jd2.servlet.converter.impl.CareerStepHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.DepartmentHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.EmployeeHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.EducationHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.PassportHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.MaritalStatusHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.RelativeHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.PositionHttpConverter;
import by.itacademy.jd2.servlet.converter.impl.DismissHttpConverter;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestConverter {
    private static HttpRequestConverter instance;
    private final Map<Class<?>, FromHttpRequestConverter<?>> converters = new HashMap<>();

    private HttpRequestConverter() {
        fillConverters();
    }

    public static HttpRequestConverter getConverter() {
        if (instance == null) {
            instance = new HttpRequestConverter();
        }

        return instance;
    }

    private void fillConverters() {
        converters.put(CareerStepSaveDTO.class, new CareerStepHttpConverter());
        converters.put(DepartmentDTO.class, new DepartmentHttpConverter());
        converters.put(EducationDTO.class, new EducationHttpConverter());
        converters.put(EmployeeDTO.class, new EmployeeHttpConverter());
        converters.put(MaritalStatusDTO.class, new MaritalStatusHttpConverter());
        converters.put(PassportDTO.class, new PassportHttpConverter());
        converters.put(PositionDTO.class, new PositionHttpConverter());
        converters.put(RelativeDTO.class, new RelativeHttpConverter());
        converters.put(DismissDTO.class, new DismissHttpConverter());
    }

    public <DTO> DTO convert(HttpServletRequest req, Class<DTO> dtoClass) {
        FromHttpRequestConverter<DTO> converter = (FromHttpRequestConverter<DTO>) converters.get(dtoClass);
        return converter.convert(req);
    }
}
