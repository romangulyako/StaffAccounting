package by.itacademy.jd2.servlet.department;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.service.impl.DepartmentServiceImpl;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "departmentsListServlet", value = "/list_departments")
public class DepartmentsListServlet extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer pageSize = ServletUtil.getParamInt(req, ConstantParamAndAttribute.PAGE_SIZE);
            Integer pageNumber = ServletUtil.getParamInt(req, ConstantParamAndAttribute.PAGE_NUMBER);
            Boolean isActual = ServletUtil.getParamBoolean(req, ConstantParamAndAttribute.IS_ACTUAL);

            PageInfo<DepartmentDTO> pageItems = departmentService.getDepartmentsByActualAndPage(isActual,
                    pageNumber, pageSize);
            req.setAttribute(ConstantParamAndAttribute.PAGE_INFO, pageItems);
            req.setAttribute(ConstantParamAndAttribute.IS_ACTUAL, isActual);

            req.getRequestDispatcher(ConstantJSP.LIST_DEPARTMENTS_PAGE).forward(req, resp);
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
        departmentService.closeDao();
    }
}
