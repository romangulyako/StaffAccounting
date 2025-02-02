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
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "positionAddServlet", value = "/add_position")
public class PositionAddServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(PositionAddServlet.class);
    private static final Boolean DEFAULT_IS_ACTUAL_FOR_ADD = true;
    private final PositionService positionService = new PositionServiceImpl();
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long departmentId = ServletUtil.getParamLong(req, ConstantParamAndAttribute.DEPARTMENT_ID);
            req.setAttribute(ConstantParamAndAttribute.DEPARTMENT_ID, departmentId);
            req.setAttribute(ConstantParamAndAttribute.IS_ACTUAL, DEFAULT_IS_ACTUAL_FOR_ADD);
            req.getRequestDispatcher(ConstantJSP.ADD_POSITION_PAGE).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            positionService.addPosition(HttpRequestConverter.convert(req, PositionDTO.class));
            req.getRequestDispatcher(ConstantAction.DEPARTMENT_INFO).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        positionService.closeDao();
        departmentService.closeDao();
    }
}
