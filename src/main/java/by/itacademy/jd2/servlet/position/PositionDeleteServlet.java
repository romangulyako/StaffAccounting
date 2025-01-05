package by.itacademy.jd2.servlet.position;

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

@WebServlet(name = "positionDeleteServlet", value = "/delete_position")
public class PositionDeleteServlet extends HttpServlet {
    private final PositionService positionService = new PositionServiceImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        positionService.deletePosition(ParseUtil.parseLong(
                ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
        Long departmentId = ParseUtil.parseLong(
                ServletUtil.getParam(req, ConstantParamAndAttribute.DEPARTMENT_ID));
        List<PositionDTO> positions = positionService.getPositionsByDepartmentId(departmentId);
        DepartmentDTO department = departmentService.getDepartment(departmentId);
        req.setAttribute(ConstantParamAndAttribute.DEPARTMENT, department);
        req.setAttribute(ConstantParamAndAttribute.LIST_POSITIONS, positions);
        req.getRequestDispatcher(ConstantJSP.DEPARTMENT_INFO_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        positionService.closeDao();
        departmentService.closeDao();
    }
}
