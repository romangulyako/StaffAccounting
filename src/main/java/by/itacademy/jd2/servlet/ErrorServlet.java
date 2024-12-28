package by.itacademy.jd2.servlet;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.utils.HibernateUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "errorServlet", value = "/error")
public class ErrorServlet extends HttpServlet {
    private static final String DEFAULT_MESSAGE = "Неизвестная ошибка! Что-то пошло не так!";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = ServletUtil.getParam(req, ConstantParamAndAttribute.ERROR);
        if (message == null) {
            message = DEFAULT_MESSAGE;
        }
        req.setAttribute(ConstantParamAndAttribute.ERROR, message);

        req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        HibernateUtil.close();
    }
}
