package by.itacademy.jd2.servlet.passport;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.converter.PassportConverter;
import by.itacademy.jd2.dto.EmployeeDTO;
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

@WebServlet(name = "passportUpdateServlet", value = "/update_passport")
public class PassportUpdateServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final PassportDTO passport = employeeService.getPassport(
                    ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            req.setAttribute(ConstantParamAndAttribute.PASSPORT, passport);

            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher(ConstantJSP.UPDATE_PASSPORT_PAGE);
            requestDispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Передан неверный параметр");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        } catch (NullPointerException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Такого паспорта нет!");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeDTO employee = employeeService.getEmployee(
                ParseUtil.parseLong(ServletUtil.getParam(
                        req, ConstantParamAndAttribute.ID)));
        PassportDTO passport = PassportConverter.fromHttpRequest(req);
        employeeService.updatePassport(passport, employee);

        req.setAttribute(ConstantParamAndAttribute.PASSPORT, passport);
        req.setAttribute(ConstantParamAndAttribute.ID, employee.getId());
        req.getRequestDispatcher(ConstantJSP.PASSPORT_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
    }
}
