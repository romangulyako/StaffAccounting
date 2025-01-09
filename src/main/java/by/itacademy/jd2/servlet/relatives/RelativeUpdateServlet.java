package by.itacademy.jd2.servlet.relatives;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.converter.RelativeConverter;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.service.api.RelativeService;
import by.itacademy.jd2.service.impl.RelativeServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
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
            req.getRequestDispatcher(ConstantJSP.UPDATE_RELATIVE_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            relativeService.updateRelative(RelativeConverter.fromHttpRequest(req));
            req.getRequestDispatcher(ConstantAction.RELATIVES).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        relativeService.closeDao();
    }
}
