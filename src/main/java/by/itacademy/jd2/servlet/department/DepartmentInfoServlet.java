package by.itacademy.jd2.servlet.department;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.dto.PositionDTO;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.service.api.PositionService;
import by.itacademy.jd2.service.impl.DepartmentServiceImpl;
import by.itacademy.jd2.service.impl.PositionServiceImpl;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "departmentInfoServlet", value = "/department_info")
public class DepartmentInfoServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentInfoServlet.class);
    private final DepartmentService departmentService = new DepartmentServiceImpl();
    private final PositionService positionService = new PositionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer pageSize = ServletUtil.getParamInt(req, ConstantParamAndAttribute.PAGE_SIZE);
            Integer pageNumber = ServletUtil.getParamInt(req, ConstantParamAndAttribute.PAGE_NUMBER);
            Long departmentId = ServletUtil.getParamLong(req, ConstantParamAndAttribute.DEPARTMENT_ID);
            Boolean isActual = ServletUtil.getParamBoolean(req, ConstantParamAndAttribute.IS_ACTUAL);

            final DepartmentDTO department = departmentService.getDepartment(departmentId);
            final PageInfo<PositionDTO> pageInfo =
                    positionService.getPositionsByDepartmentIdAndActualAndPage(departmentId, isActual, pageNumber,
                            pageSize);

            req.setAttribute(ConstantParamAndAttribute.DEPARTMENT, department);
            req.setAttribute(ConstantParamAndAttribute.PAGE_INFO, pageInfo);
            req.setAttribute(ConstantParamAndAttribute.IS_ACTUAL, isActual);
            req.getRequestDispatcher(ConstantJSP.DEPARTMENT_INFO_PAGE).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
