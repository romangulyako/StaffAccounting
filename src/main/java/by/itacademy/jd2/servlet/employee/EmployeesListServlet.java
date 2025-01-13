package by.itacademy.jd2.servlet.employee;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "employeesListServlet", value = "/list_employees")
public class EmployeesListServlet extends HttpServlet {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Boolean isFired = ParseUtil.parseBoolean(ServletUtil.getParam(req,
                    ConstantParamAndAttribute.IS_FIRED_EMPLOYEES));
            Integer pageSize = ParseUtil.parseInt(ServletUtil.getParam(req, ConstantParamAndAttribute.PAGE_SIZE));
            Integer pageNumber = ParseUtil.parseInt(ServletUtil.getParam(req, ConstantParamAndAttribute.PAGE_NUMBER));
            PageInfo<EmployeeDTO> pageItems =
                    employeeService.getEmployeesByFiredAndPage(isFired, pageSize, pageNumber);
            req.setAttribute(ConstantParamAndAttribute.PAGE, pageItems);

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
