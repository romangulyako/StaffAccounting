package by.itacademy.jd2.servlet.position;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.service.api.PositionService;
import by.itacademy.jd2.service.impl.DepartmentServiceImpl;
import by.itacademy.jd2.service.impl.PositionServiceImpl;
import by.itacademy.jd2.servlet.converter.HttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "positionAddServlet", value = "/add_position")
public class PositionAddServlet extends HttpServlet {
    private final PositionService positionService = new PositionServiceImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long departmentId = ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.DEPARTMENT_ID));
            req.setAttribute(ConstantParamAndAttribute.DEPARTMENT_ID, departmentId);
            req.getRequestDispatcher(ConstantJSP.ADD_POSITION_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            positionService.addPosition(HttpRequestConverter.getConverter().convert(req, PositionDTO.class));
            req.getRequestDispatcher(ConstantAction.DEPARTMENT_INFO).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        positionService.closeDao();
        departmentService.closeDao();
    }
}
