package by.itacademy.jd2.servlet.department;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.converter.DepartmentConverter;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.service.impl.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "departmentAddServlet", value = "/add_department")
public class DepartmentAddServlet extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ConstantJSP.ADD_DEPARTMENT_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        departmentService.addDepartment(DepartmentConverter.fromHttpRequest(req));
        resp.sendRedirect(ConstantAction.LIST_DEPARTMENTS);
    }

    @Override
    public void destroy() {
        departmentService.closeDao();
    }
}
