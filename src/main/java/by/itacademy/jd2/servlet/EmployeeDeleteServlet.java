package by.itacademy.jd2.servlet;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.utils.HibernateUtil;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "employeeDeleteServlet", value = "/delete_employee")
public class EmployeeDeleteServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employeeService.deleteEmployee(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
        resp.sendRedirect(ConstantAction.LIST_EMPLOYEES);
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
        HibernateUtil.close();
    }
}
