package by.itacademy.jd2.servlet.education;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.converter.EducationConverter;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.service.api.EducationService;
import by.itacademy.jd2.service.impl.EducationServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "educationUpdateServlet", value = "/update_education")
public class EducationUpdateServlet extends HttpServlet {
    private final EducationService educationService = new EducationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final EducationDTO education = educationService.getEducation(
                    ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            req.setAttribute(ConstantParamAndAttribute.EDUCATION, education);

            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher(ConstantJSP.UPDATE_EDUCATION_PAGE);
            requestDispatcher.forward(req, resp);
        } catch (NumberFormatException | NullPointerException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Ошибка в параметре");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        educationService.updateEducation(EducationConverter.fromHttpRequest(req));
        req.getRequestDispatcher(ConstantAction.EDUCATION).forward(req, resp);
    }

    @Override
    public void destroy() {
        educationService.closeDao();
    }
}
