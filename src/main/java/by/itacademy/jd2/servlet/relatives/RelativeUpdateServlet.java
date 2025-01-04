package by.itacademy.jd2.servlet.relatives;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.converter.RelativeConverter;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.service.api.RelativeService;
import by.itacademy.jd2.service.impl.RelativeServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "relativeUpdateServlet", value = "/update_relative")
public class RelativeUpdateServlet extends HttpServlet {
    private final RelativeService relativeService = new RelativeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final RelativeDTO relative = relativeService.getRelative(
                    ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            req.setAttribute(ConstantParamAndAttribute.RELATIVE, relative);

            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher(ConstantJSP.UPDATE_RELATIVE_PAGE);
            requestDispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Передан неверный параметр");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        } catch (NullPointerException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Такого родственника нет!");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RelativeDTO relative = RelativeConverter.fromHttpRequest(req);
        relativeService.updateRelative(relative);
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, relative.getEmployeeId());
        req.setAttribute(ConstantParamAndAttribute.LIST_RELATIVES,
                relativeService.getRelatives(relative.getEmployeeId()));
        req.getRequestDispatcher(ConstantJSP.RELATIVES_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        relativeService.closeDao();
    }
}
