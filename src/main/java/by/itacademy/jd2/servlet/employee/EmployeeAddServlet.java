package by.itacademy.jd2.servlet.employee;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.converter.EmployeeConverter;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.service.api.EmployeeService;
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
        try {
            req.getRequestDispatcher(ConstantJSP.ADD_EMPLOYEE_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
           employeeService.addEmployee(EmployeeConverter.fromHttpRequest(req));
           resp.sendRedirect(ConstantAction.LIST_EMPLOYEES);
       } catch (Exception e) {
           req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
       }
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
    }
}
