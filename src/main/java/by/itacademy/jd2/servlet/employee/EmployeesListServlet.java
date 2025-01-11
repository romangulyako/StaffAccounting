package by.itacademy.jd2.servlet.employee;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.service.api.EmployeeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "employeesListServlet", value = "/list_employees")
public class EmployeesListServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final List<EmployeeDTO> employees = employeeService.getAllCurrentEmployees();
            req.setAttribute(ConstantParamAndAttribute.LIST_EMPLOYEES, employees);
            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher(ConstantJSP.LIST_EMPLOYEES_PAGE);
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
    }
}
