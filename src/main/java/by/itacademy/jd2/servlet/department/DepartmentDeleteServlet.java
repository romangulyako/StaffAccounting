package by.itacademy.jd2.servlet.department;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
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

@WebServlet(name = "departmentDeleteServlet", value = "/delete_department")
public class DepartmentDeleteServlet extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            departmentService.deleteDepartment(ParseUtil.parseLong(
                    ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            req.getRequestDispatcher(ConstantAction.LIST_DEPARTMENTS).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        departmentService.closeDao();
    }
}
