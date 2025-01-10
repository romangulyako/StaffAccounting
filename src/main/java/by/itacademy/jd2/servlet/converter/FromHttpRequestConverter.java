package by.itacademy.jd2.servlet.converter;

import jakarta.servlet.http.HttpServletRequest;

public interface FromHttpRequestConverter<DTO> {
    DTO convert(HttpServletRequest req);
}
