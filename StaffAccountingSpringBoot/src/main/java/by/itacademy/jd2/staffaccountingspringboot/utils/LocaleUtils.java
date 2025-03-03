package by.itacademy.jd2.staffaccountingspringboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocaleUtils {
    private static MessageSource messageSource;

    @Autowired
    public LocaleUtils(MessageSource messageSource) {
        LocaleUtils.messageSource = messageSource;
    }

    /**
     * Получает сообщение из properties файла в зависимости от выбранного языка
     * @param key имя параметра в properties файле
     * @return значение сообщения по ключу
     */
    public static String getMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
