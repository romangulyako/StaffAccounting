package by.itacademy.jd2.servlet.position;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.converter.PositionConverter;
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

@WebServlet(name = "positionUpdateServlet", value = "/update_position")
public class PositionUpdateServlet extends HttpServlet {
    private final PositionService positionService = new PositionServiceImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final PositionDTO position = positionService.getPosition(
                    ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            req.setAttribute(ConstantParamAndAttribute.POSITION, position);
            req.getRequestDispatcher(ConstantJSP.UPDATE_POSITION_PAGE).forward(req, resp);
        } catch (NumberFormatException | NullPointerException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Ошибка в параметре");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PositionDTO position = PositionConverter.fromHttpRequest(req);
        positionService.updatePosition(position);
        DepartmentDTO department = departmentService.getDepartment(position.getDepartmentId());
        req.setAttribute(ConstantParamAndAttribute.DEPARTMENT, department);
        req.setAttribute(ConstantParamAndAttribute.LIST_POSITIONS,
                positionService.getPositionsByDepartmentId(position.getDepartmentId()));
        req.getRequestDispatcher(ConstantJSP.DEPARTMENT_INFO_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        positionService.closeDao();
        departmentService.closeDao();
    }
}
