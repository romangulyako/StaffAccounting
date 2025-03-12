package by.itacademy.jd2.exception;

import by.itacademy.jd2.utils.Constant;
import by.itacademy.jd2.utils.LocaleUtils;
import org.springframework.dao.DataIntegrityViolationException;

public class AppointmentException extends DataIntegrityViolationException {
    public AppointmentException() {
        super(LocaleUtils.getMessage(Constant.APPOINTED_EXCEPTION_UNIQUE_MESSAGE_KEY));
    }
}
