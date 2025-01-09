package by.itacademy.jd2.servlet.career;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.CareerStepGetDTO;
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

@WebServlet(name = "careerInfoServlet", value = "/career")
public class CareerInfoServlet extends HttpServlet {
    private final CareerService careerService = new CareerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long employeeId = ParseUtil.parseLong(ServletUtil.getParam(req,
                    ConstantParamAndAttribute.EMPLOYEE_ID));
            List<CareerStepGetDTO> career = careerService.getCareerOfEmployee(employeeId);
            req.setAttribute(ConstantParamAndAttribute.CAREER, career);
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, employeeId);
            req.getRequestDispatcher(ConstantJSP.CAREER_INFO_PAGE).forward(req, resp);
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
