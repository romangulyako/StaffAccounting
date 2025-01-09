package by.itacademy.jd2.servlet.department;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.service.api.PositionService;
import by.itacademy.jd2.service.impl.DepartmentServiceImpl;
import by.itacademy.jd2.service.impl.PositionServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "departmentInfoServlet", value = "/department_info")
public class DepartmentInfoServlet extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private final PositionService positionService = new PositionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long departmentId = ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.DEPARTMENT_ID));
            final DepartmentDTO department = departmentService.getDepartment(departmentId);
            final List<PositionDTO> positions = positionService.getPositionsByDepartmentId(departmentId);

            req.setAttribute(ConstantParamAndAttribute.DEPARTMENT, department);
            req.setAttribute(ConstantParamAndAttribute.LIST_POSITIONS, positions);
            req.getRequestDispatcher(ConstantJSP.DEPARTMENT_INFO_PAGE).forward(req, resp);
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
        positionService.closeDao();
    }
}
