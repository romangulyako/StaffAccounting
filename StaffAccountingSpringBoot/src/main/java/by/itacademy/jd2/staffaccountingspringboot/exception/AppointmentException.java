package by.itacademy.jd2.staffaccountingspringboot.exception;

import by.itacademy.jd2.staffaccountingspringboot.utils.Constant;
import by.itacademy.jd2.staffaccountingspringboot.utils.LocaleUtils;
import org.springframework.dao.DataIntegrityViolationException;

public class AppointmentException extends DataIntegrityViolationException {
    public AppointmentException() {
        super(LocaleUtils.getMessage(Constant.APPOINTED_EXCEPTION_UNIQUE_MESSAGE_KEY));
    }
}
