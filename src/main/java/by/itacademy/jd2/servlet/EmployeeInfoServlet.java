package by.itacademy.jd2.servlet;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.utils.HibernateUtil;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "employeeInfoServlet", value = "/employee")
public class EmployeeInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeService employeeService = new EmployeeServiceImpl();

        try {
            final EmployeeDTO employee = employeeService.getEmployee(
                    ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE, employee);
            req.getRequestDispatcher(ConstantJSP.EMPLOYEE_PAGE).forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Передан неверный параметр");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        } catch (NullPointerException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Такого сотрудника нет!");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        HibernateUtil.close();
    }
}
