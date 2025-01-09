package by.itacademy.jd2.servlet.career;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PositionHistoryDTO;
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
import java.util.List;

@WebServlet(name = "positionHistoryGetServlet", value = "/history")
public class PositionHistoryGetServlet extends HttpServlet {
    private final CareerService careerService = new CareerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long positionId = ParseUtil.parseLong(ServletUtil.getParam(req,
                ConstantParamAndAttribute.POSITION_ID));
        List<PositionHistoryDTO> history = careerService.getPositionHistory(positionId);
        req.setAttribute(ConstantParamAndAttribute.HISTORY, history);
        req.setAttribute(ConstantParamAndAttribute.DEPARTMENT_ID, ServletUtil.getParam(req,
                ConstantParamAndAttribute.DEPARTMENT_ID));
        req.getRequestDispatcher(ConstantJSP.POSITION_HISTORY_PAGE).forward(req, resp);
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
