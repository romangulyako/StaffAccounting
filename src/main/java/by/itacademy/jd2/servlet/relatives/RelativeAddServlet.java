package by.itacademy.jd2.servlet.relatives;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.converter.RelativeConverter;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.service.api.RelativeService;
import by.itacademy.jd2.service.impl.RelativeServiceImpl;
import by.itacademy.jd2.utils.HibernateUtil;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "relativeAddServlet", value = "/add_relative")
public class RelativeAddServlet extends HttpServlet {
    private final RelativeService relativeService = new RelativeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long employee_id = ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID));
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, employee_id);
        req.getRequestDispatcher(ConstantJSP.ADD_RELATIVE_PAGE).forward(req, resp);
        // TODO: Переделать, чтобы адрес был правильный
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RelativeDTO relative = RelativeConverter.fromHttpRequest(req);
        relativeService.addRelative(relative);
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, relative.getEmployeeId());
        req.setAttribute(ConstantParamAndAttribute.LIST_RELATIVES,
                relativeService.getRelatives(relative.getEmployeeId()));
        req.getRequestDispatcher(ConstantJSP.RELATIVES_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        this.relativeService.closeDao();
        HibernateUtil.close();
    }
}
