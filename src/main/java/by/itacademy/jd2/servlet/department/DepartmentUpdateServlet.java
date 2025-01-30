package by.itacademy.jd2.servlet.department;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.service.impl.DepartmentServiceImpl;
import by.itacademy.jd2.servlet.converter.HttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "departmentUpdateServlet", value = "/update_department")
public class DepartmentUpdateServlet extends HttpServlet {
    private static final Boolean DEFAULT_IS_ACTUAL_FOR_UPDATE = true;
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DepartmentDTO department = departmentService.getDepartment(
                    ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID));
            req.setAttribute(ConstantParamAndAttribute.DEPARTMENT, department);
            req.setAttribute(ConstantParamAndAttribute.IS_ACTUAL, DEFAULT_IS_ACTUAL_FOR_UPDATE);
            req.getRequestDispatcher(ConstantJSP.UPDATE_DEPARTMENT_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            departmentService.updateDepartment(HttpRequestConverter.convert(req, DepartmentDTO.class));
            req.getRequestDispatcher(ConstantAction.DEPARTMENT_INFO).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        departmentService.closeDao();
    }
}
