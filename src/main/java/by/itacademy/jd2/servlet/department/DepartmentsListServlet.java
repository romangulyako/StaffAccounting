package by.itacademy.jd2.servlet.department;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.service.impl.DepartmentServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "departmentsListServlet", value = "/list_departments")
public class DepartmentsListServlet extends HttpServlet {
    private static final Integer DEFAULT_PAGE_SIZE = 2;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer pageSize = ParseUtil.parseInt(ServletUtil.getParam(req,ConstantParamAndAttribute.PAGE_SIZE));
            Integer pageNumber = ParseUtil.parseInt(ServletUtil.getParam(req,ConstantParamAndAttribute.PAGE_NUMBER));
            if (pageSize == null || pageSize < 1) {
                pageSize = DEFAULT_PAGE_SIZE;
            }
            if (pageNumber == null || pageNumber < 1) {
                pageNumber = DEFAULT_PAGE_NUMBER;
            }
            Integer totalPages = departmentService.getTotalPages(pageSize);
            List<DepartmentDTO> departments = departmentService.getDepartmentsByPage(pageNumber, pageSize);

            req.setAttribute(ConstantParamAndAttribute.LIST_DEPARTMENTS, departments);
            req.setAttribute(ConstantParamAndAttribute.PAGE_NUMBER, pageNumber);
            req.setAttribute(ConstantParamAndAttribute.PAGE_SIZE, pageSize);
            req.setAttribute(ConstantParamAndAttribute.TOTAL_PAGES, totalPages);

            req.getRequestDispatcher(ConstantJSP.LIST_DEPARTMENTS_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        departmentService.closeDao();
    }
}
