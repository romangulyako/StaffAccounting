package by.itacademy.jd2.servlet.converter;

import by.itacademy.jd2.dto.*;
import by.itacademy.jd2.servlet.converter.impl.*;
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
        converters.put(EducationDTO.class, new DepartmentHttpConverter());
        converters.put(EmployeeDTO.class, new EmployeeHttpConverter());
        converters.put(MaritalStatusDTO.class, new MaritalStatusHttpConverter());
        converters.put(PassportDTO.class, new PassportHttpConverter());
        converters.put(PositionDTO.class, new PositionHttpConverter());
        converters.put(RelativeDTO.class, new RelativeHttpConverter());
        converters.put(DismissDTO.class, new DismissConverter());
    }

    public <DTO> DTO convert(HttpServletRequest req, Class<DTO> dtoClass) {
        FromHttpRequestConverter<DTO> converter = (FromHttpRequestConverter<DTO>) converters.get(dtoClass);
        return converter.convert(req);
    }
}
