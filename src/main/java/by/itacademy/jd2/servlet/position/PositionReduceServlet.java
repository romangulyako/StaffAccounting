package by.itacademy.jd2.servlet.position;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.service.api.PositionService;
import by.itacademy.jd2.service.impl.PositionServiceImpl;
import by.itacademy.jd2.utils.HibernateUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "positionReduceServlet", value = "/reduce_position")
public class PositionReduceServlet extends HttpServlet {
    private final PositionService positionService = new PositionServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long positionId = ServletUtil.getParamLong(req, ConstantParamAndAttribute.POSITION_ID);
            positionService.reducePosition(positionId);
            req.getRequestDispatcher(ConstantAction.DEPARTMENT_INFO).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        positionService.closeDao();
        HibernateUtil.close();
    }
}
