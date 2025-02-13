package by.itacademy.jd2.staffaccountingspringboot.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
public class LanguageController {

    private final LocaleResolver localeResolver;

    @Autowired
    public LanguageController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @PostMapping("/changeLanguage")
    public String changeLanguage(@RequestParam("language") String language,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        Locale locale = new Locale(language);
        localeResolver.setLocale(request, response, locale);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
