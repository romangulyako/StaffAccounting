package by.itacademy.jd2.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class ServletUtil {

    /**
     * Получает из объекта HttpServletRequest параметр по его имени
     * @param req объект HttpServletRequest
     * @param nameField имя параметра, который получаем из HttpServletRequest
     * @return строковое представление параметра
     */
    public static String getParam(HttpServletRequest req, String nameField) {
        return Optional.ofNullable(req.getParameter(nameField))
                .filter(StringUtils::isNotBlank)
                .orElse(null);
    }
}
