package by.itacademy.jd2.servlet.career;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.converter.CareerStepConverter;
import by.itacademy.jd2.service.api.CareerService;
import by.itacademy.jd2.service.impl.CareerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "careerStepDeleteServlet", value = "/delete_career_step")
public class CareerStepDeleteServlet extends HttpServlet {
    private final CareerService careerService = new CareerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            careerService.deleteCareerStep(CareerStepConverter.toCareerStepId(req));
            req.getRequestDispatcher(ConstantAction.CAREER).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        careerService.closeDao();
    }
}
