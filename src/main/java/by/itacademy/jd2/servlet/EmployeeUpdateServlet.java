package by.itacademy.jd2.servlet;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.converter.EmployeeConverter;
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

@WebServlet(name = "employeeUpdateServlet", value = "/update_employee")
public class EmployeeUpdateServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final EmployeeDTO employee = employeeService.getEmployee(
                    ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE, employee);

            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher(ConstantJSP.UPDATE_EMPLOYEE_PAGE);
            requestDispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Передан неверный параметр");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        } catch (NullPointerException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Такого сотрудника нет!");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employeeService.updateEmployee(EmployeeConverter.fromHttpRequest(req));

        resp.sendRedirect(ConstantAction.LIST_EMPLOYEES);
    }

    @Override
    public void destroy() {
        this.employeeService.closeDao();
        HibernateUtil.close();
    }
}
