package by.itacademy.jd2.servlet;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.converter.EmployeeConverter;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.utils.HibernateUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "employeeAddServlet", value = "/add_employee")
public class EmployeeAddServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher(ConstantJSP.ADD_EMPLOYEE_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employeeService.addEmployee(EmployeeConverter.fromHttpRequest(req));

        resp.sendRedirect(ConstantAction.LIST_EMPLOYEES);
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
        HibernateUtil.close();
    }
}
