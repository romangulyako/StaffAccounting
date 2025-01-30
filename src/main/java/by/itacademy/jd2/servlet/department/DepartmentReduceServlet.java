package by.itacademy.jd2.servlet.department;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.service.impl.DepartmentServiceImpl;
import by.itacademy.jd2.utils.HibernateUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "departmentReduceServlet", value = "/reduce_department")
public class DepartmentReduceServlet extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long departmentId =
                    ServletUtil.getParamLong(req, ConstantParamAndAttribute.DEPARTMENT_ID);
            departmentService.reduceDepartment(departmentId);
            req.getRequestDispatcher(ConstantAction.LIST_DEPARTMENTS).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        departmentService.closeDao();
        HibernateUtil.close();
    }
}
