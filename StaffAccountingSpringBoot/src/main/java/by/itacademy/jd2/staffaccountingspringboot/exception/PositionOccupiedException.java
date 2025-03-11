package by.itacademy.jd2.staffaccountingspringboot.exception;

import by.itacademy.jd2.staffaccountingspringboot.utils.Constant;
import by.itacademy.jd2.staffaccountingspringboot.utils.LocaleUtils;

public class PositionOccupiedException extends RuntimeException {
    public PositionOccupiedException() {
        super(LocaleUtils.getMessage(Constant.POSITION_OCCUPIED_EXCEPTION_MESSAGE_KEY));
    }
}
