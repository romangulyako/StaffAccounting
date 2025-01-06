package by.itacademy.jd2.servlet.education;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.service.api.EducationService;
import by.itacademy.jd2.service.impl.EducationServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "educationDeleteServlet", value = "/delete_education")
public class EducationDeleteServlet extends HttpServlet {
    private final EducationService educationService = new EducationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        educationService.deleteEducation(ParseUtil.parseLong(
                ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
        req.getRequestDispatcher(ConstantAction.EDUCATION).forward(req, resp);
    }

    @Override
    public void destroy() {
        educationService.closeDao();
    }
}
