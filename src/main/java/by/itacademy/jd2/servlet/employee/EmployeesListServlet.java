package by.itacademy.jd2.servlet.employee;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
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
    private static final Integer DEFAULT_PAGE_SIZE = 2;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Boolean DEFAULT_IS_FIRED = false;
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Boolean isFiredEmployees = ParseUtil.parseBoolean(ServletUtil.getParam(req,
                    ConstantParamAndAttribute.IS_FIRED_EMPLOYEES));
            Integer pageSize = ParseUtil.parseInt(ServletUtil.getParam(req,ConstantParamAndAttribute.PAGE_SIZE));
            Integer pageNumber = ParseUtil.parseInt(ServletUtil.getParam(req,ConstantParamAndAttribute.PAGE_NUMBER));
            if (pageSize == null || pageSize < 1) {
                pageSize = DEFAULT_PAGE_SIZE;
            }
            if (pageNumber == null || pageNumber < 1) {
                pageNumber = DEFAULT_PAGE_NUMBER;
            }
            if (isFiredEmployees == null) {
                isFiredEmployees = DEFAULT_IS_FIRED;
            }
            Integer totalPages = employeeService.getTotalPages(isFiredEmployees, pageSize);

            List<EmployeeDTO> employees;
            if (isFiredEmployees) {
                employees = employeeService.getAllFiredEmployees(pageSize, pageNumber);
            } else {
                employees = employeeService.getAllCurrentEmployees(pageSize, pageNumber);
            }
            req.setAttribute(ConstantParamAndAttribute.LIST_EMPLOYEES, employees);
            req.setAttribute(ConstantParamAndAttribute.PAGE_NUMBER, pageNumber);
            req.setAttribute(ConstantParamAndAttribute.PAGE_SIZE, pageSize);
            req.setAttribute(ConstantParamAndAttribute.TOTAL_PAGES, totalPages);

            req.getRequestDispatcher(ConstantJSP.LIST_EMPLOYEES_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
    }
}
