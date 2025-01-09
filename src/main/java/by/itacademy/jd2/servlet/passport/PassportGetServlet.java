package by.itacademy.jd2.servlet.passport;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "passportGetServlet", value = "/passport")
public class PassportGetServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PassportDTO passport = employeeService.getPassport(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            req.setAttribute(ConstantParamAndAttribute.PASSPORT, passport);
            req.setAttribute(ConstantParamAndAttribute.ID, ServletUtil.getParam(req, ConstantParamAndAttribute.ID));

            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher(ConstantJSP.PASSPORT_PAGE);
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
    }
}
