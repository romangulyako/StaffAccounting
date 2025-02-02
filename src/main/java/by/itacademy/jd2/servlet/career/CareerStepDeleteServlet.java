package by.itacademy.jd2.servlet.career;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.service.api.CareerService;
import by.itacademy.jd2.service.impl.CareerServiceImpl;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "careerStepDeleteServlet", value = "/delete_career_step")
public class CareerStepDeleteServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CareerStepDeleteServlet.class);
    private final CareerService careerService = new CareerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = ServletUtil.getParamLong(req, ConstantParamAndAttribute.CAREER_ID);
            careerService.deleteCareerStep(id);
            req.getRequestDispatcher(ConstantAction.CAREER).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        careerService.closeDao();
    }
}
