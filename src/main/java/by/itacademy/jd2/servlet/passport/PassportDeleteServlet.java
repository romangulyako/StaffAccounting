package by.itacademy.jd2.servlet.passport;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
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

@WebServlet(name = "passportDeleteServlet", value = "/delete_passport")
public class PassportDeleteServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID));
        employeeService.deletePassport(id);
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE, employeeService.getEmployee(id));
        req.getRequestDispatcher(ConstantJSP.EMPLOYEE_PAGE).forward(req, resp);
        // TODO: Переделать, чтобы адрес был правильный
    }

    @Override
    public void destroy() {
        this.employeeService.closeDao();
    }
}
