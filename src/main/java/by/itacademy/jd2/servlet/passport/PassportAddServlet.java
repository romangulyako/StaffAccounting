package by.itacademy.jd2.servlet.passport;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.converter.PassportConverter;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "passportAddServlet", value = "/add_passport")
public class PassportAddServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long employee_id = ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID));
            req.setAttribute(ConstantParamAndAttribute.ID, employee_id);
            req.getRequestDispatcher(ConstantJSP.ADD_PASSPORT_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            EmployeeDTO employee = employeeService.getEmployee(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            PassportDTO passport = PassportConverter.fromHttpRequest(req);
            employeeService.addPassport(passport, employee);
            req.getRequestDispatcher(ConstantAction.PASSPORT).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
    }
}
