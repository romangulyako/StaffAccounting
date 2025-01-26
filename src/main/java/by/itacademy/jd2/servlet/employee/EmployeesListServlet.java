package by.itacademy.jd2.servlet.employee;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DepartmentItemDTO;
import by.itacademy.jd2.dto.EmployeeDTO;
import by.itacademy.jd2.dto.EmployeeFilterData;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.service.impl.DepartmentServiceImpl;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.servlet.converter.HttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
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
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer pageSize = ParseUtil.parseInt(ServletUtil.getParam(req, ConstantParamAndAttribute.PAGE_SIZE));
            Integer pageNumber = ParseUtil.parseInt(ServletUtil.getParam(req, ConstantParamAndAttribute.PAGE_NUMBER));
            Boolean isFired = ParseUtil.parseBoolean(ServletUtil.getParam(req, ConstantParamAndAttribute.IS_FIRED));
            EmployeeFilterData filterData = HttpRequestConverter.convert(req, EmployeeFilterData.class);
            List<DepartmentItemDTO> departments = departmentService.getDepartmentItems();
            PageInfo<EmployeeDTO> pageInfo =
                    employeeService.getEmployeesByFiredAndPage(filterData, isFired, pageSize, pageNumber);

            req.setAttribute(ConstantParamAndAttribute.PAGE_INFO, pageInfo);
            req.setAttribute(ConstantParamAndAttribute.IS_FIRED, isFired);
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_FILTER_DATA, filterData);
            req.setAttribute(ConstantParamAndAttribute.DEPARTMENT_ITEMS, departments);

            req.getRequestDispatcher(ConstantJSP.LIST_EMPLOYEES_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
    }
}
