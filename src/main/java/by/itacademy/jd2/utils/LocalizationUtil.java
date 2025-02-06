package by.itacademy.jd2.utils;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalizationUtil {
    private static final String BUNDLE_NAME = "localization.messages";

    public static String getMessage(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    public static Locale getLocale(String language) {
        if ("ru".equals(language)) {
            return new Locale("ru");
        } else if ("en".equals(language)) {
            return Locale.ENGLISH;
        } else {
            return Locale.getDefault();
        }
    }

    public static void saveLocale(HttpServletResponse resp, String language) {
        Cookie cookie = new Cookie(ConstantParamAndAttribute.LANGUAGE, language);
        resp.addCookie(cookie);
    }

    public static Locale getLocale(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ConstantParamAndAttribute.LANGUAGE)) {
                    return getLocale(cookie.getValue());
                }
            }
        }
        return Locale.getDefault();
    }
}
