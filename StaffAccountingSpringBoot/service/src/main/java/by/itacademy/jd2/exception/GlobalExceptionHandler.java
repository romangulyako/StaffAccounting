package by.itacademy.jd2.exception;

import by.itacademy.jd2.utils.Constant;
import by.itacademy.jd2.utils.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView commonHandler(Exception e) {
        return this.baseExceptionHandler(e.getMessage(), e);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ModelAndView urlParameterExceptionHandler(MethodArgumentTypeMismatchException e) {
        return this.baseExceptionHandler(LocaleUtils
                .getMessage(Constant.URL_PARAMETER_EXCEPTION_MESSAGE_KEY) + e.getName(), e);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ModelAndView accessDeniedHandler(AuthorizationDeniedException e) {
        return this.baseExceptionHandler(LocaleUtils.getMessage(Constant.ACCESS_DENIED_EXCEPTION_MESSAGE_KEY), e);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView resourceNotFoundHandler() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404");

        return modelAndView;
    }

    private ModelAndView baseExceptionHandler(String exceptionMessage, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", exceptionMessage);
        LOGGER.error(e.getMessage(), e);

        return modelAndView;
    }
}
