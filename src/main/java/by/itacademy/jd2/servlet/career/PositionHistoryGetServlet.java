package by.itacademy.jd2.servlet.career;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PositionHistoryDTO;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.CareerService;
import by.itacademy.jd2.service.impl.CareerServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "positionHistoryGetServlet", value = "/history")
public class PositionHistoryGetServlet extends HttpServlet {
    private final CareerService careerService = new CareerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer pageSize = ParseUtil.parseInt(ServletUtil.getParam(req, ConstantParamAndAttribute.PAGE_SIZE));
            Integer pageNumber = ParseUtil.parseInt(ServletUtil.getParam(req, ConstantParamAndAttribute.PAGE_NUMBER));
            Long positionId = ParseUtil.parseLong(ServletUtil.getParam(req,
                    ConstantParamAndAttribute.POSITION_ID));
            Boolean isActual = ParseUtil.parseBoolean(ServletUtil.getParam(req, ConstantParamAndAttribute.IS_ACTUAL));
            PageInfo<PositionHistoryDTO> pageInfo =
                    careerService.getPositionHistoryByPage(positionId, pageNumber, pageSize);

            req.setAttribute(ConstantParamAndAttribute.IS_ACTUAL, isActual);
            req.setAttribute(ConstantParamAndAttribute.PAGE_INFO, pageInfo);
            req.setAttribute(ConstantParamAndAttribute.DEPARTMENT_ID, ServletUtil.getParam(req,
                    ConstantParamAndAttribute.DEPARTMENT_ID));
            req.setAttribute(ConstantParamAndAttribute.POSITION_ID, ServletUtil.getParam(req,
                    ConstantParamAndAttribute.POSITION_ID));
            req.getRequestDispatcher(ConstantJSP.POSITION_HISTORY_PAGE).forward(req, resp);
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
        careerService.closeDao();
    }
}
