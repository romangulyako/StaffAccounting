package by.itacademy.jd2.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public class ServletUtil {

    /**
     * Получает из объекта HttpServletRequest параметр по его имени
     * @param req объект HttpServletRequest
     * @param nameField имя параметра, который получаем из HttpServletRequest
     * @return строковое представление параметра
     */
    public static String getParamString(HttpServletRequest req, String nameField) {
        return Optional.ofNullable(req.getParameter(nameField))
                .filter(StringUtils::isNotBlank)
                .orElse(null);
    }

    public static Long getParamLong(HttpServletRequest req, String nameField) {
        return Optional.ofNullable(replaceNull(getParamString(req, nameField)))
                .map(Long::parseLong)
                .orElse(null);
    }

    public static Integer getParamInt(HttpServletRequest req, String nameField) {
        return Optional.ofNullable(getParamString(req, nameField))
                .map(Integer::parseInt)
                .orElse(null);
    }

    public static Double getParamDouble(HttpServletRequest req, String nameField) {
        return Optional.ofNullable(getParamString(req, nameField))
                .map(Double::parseDouble)
                .orElse(null);
    }

    public static Boolean getParamBoolean(HttpServletRequest req, String nameField) {
        return Optional.ofNullable(getParamString(req, nameField))
                .map(Boolean::parseBoolean)
                .orElse(null);
    }

    public static Date getParamDate(HttpServletRequest req, String nameField) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = Optional.ofNullable(getParamString(req, nameField))
                .map(date -> LocalDate.parse(date, formatter))
                .orElse(null);
        return Optional.ofNullable(localDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    private static <T> T replaceNull(T obj) {
        if (obj != null && Objects.equals("null", obj.toString())) {
            return null;
        }
        return obj;
    }
}
