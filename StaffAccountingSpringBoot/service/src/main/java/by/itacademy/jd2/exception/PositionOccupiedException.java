package by.itacademy.jd2.exception;

import by.itacademy.jd2.utils.Constant;
import by.itacademy.jd2.utils.LocaleUtils;

public class PositionOccupiedException extends RuntimeException {
    public PositionOccupiedException() {
        super(LocaleUtils.getMessage(Constant.POSITION_OCCUPIED_EXCEPTION_MESSAGE_KEY));
    }
}
