package by.itacademy.jd2.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ParseUtil {
    public static Long parseLong(String input) throws NumberFormatException {
        return Optional.ofNullable(input)
                .map(Long::parseLong)
                .orElse(null);
    }

    public static Integer parseInt(String input) throws NumberFormatException {
        return Optional.ofNullable(input)
                .map(Integer::parseInt)
                .orElse(null);
    }

    public static Date parseDate(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(input, formatter);
        return Date.valueOf(localDate);
    }

    public static Boolean parseBoolean(String input) {
        return Optional.ofNullable(input)
                .map(Boolean::parseBoolean)
                .orElse(null);
    }
}
