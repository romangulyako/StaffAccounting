package by.itacademy.jd2.servlet.relatives;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.RelativeService;
import by.itacademy.jd2.service.impl.RelativeServiceImpl;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "relativesGetServlet", value = "/relatives")
public class RelativesGetServlet extends HttpServlet {
    private final RelativeService relativeService = new RelativeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer pageSize = ServletUtil.getParamInt(req, ConstantParamAndAttribute.PAGE_SIZE);
            Integer pageNumber = ServletUtil.getParamInt(req, ConstantParamAndAttribute.PAGE_NUMBER);
            Long employeeId = ServletUtil.getParamLong(req, ConstantParamAndAttribute.EMPLOYEE_ID);
            PageInfo<RelativeDTO> pageInfo = relativeService.getRelativesByEmployeeIdAndPage(employeeId,
                    pageNumber, pageSize);
            req.setAttribute(ConstantParamAndAttribute.PAGE_INFO, pageInfo);
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, employeeId);
            req.getRequestDispatcher(ConstantJSP.RELATIVES_PAGE).forward(req, resp);
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
        relativeService.closeDao();
    }
}
