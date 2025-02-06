package by.itacademy.jd2.servlet;

import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.utils.LocalizationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "languageServlet", value = "/set_language")
public class LanguageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter(ConstantParamAndAttribute.LANGUAGE);

        if(language != null) {
            LocalizationUtil.saveLocale(resp, language);
        }

        resp.sendRedirect(req.getHeader("Referer"));
    }
}
