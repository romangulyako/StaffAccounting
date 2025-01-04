package by.itacademy.jd2.servlet.department;

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

@WebServlet(name = "departmentInfoServlet", value = "/department_info")
public class DepartmentInfoServlet extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final DepartmentDTO department = departmentService.getDepartment(ParseUtil.parseLong(
                    ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            req.setAttribute(ConstantParamAndAttribute.DEPARTMENT, department);
            req.getRequestDispatcher(ConstantJSP.DEPARTMENT_INFO_PAGE).forward(req, resp);
        } catch (NullPointerException | NumberFormatException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Ошибка в параметре");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        departmentService.closeDao();
    }
}
