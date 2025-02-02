package by.itacademy.jd2.servlet.education;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.service.api.EducationService;
import by.itacademy.jd2.service.impl.EducationServiceImpl;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "educationDeleteServlet", value = "/delete_education")
public class EducationDeleteServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(EducationDeleteServlet.class);
    private final EducationService educationService = new EducationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            educationService.deleteEducation(ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID));
            req.getRequestDispatcher(ConstantAction.EDUCATION).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        educationService.closeDao();
    }
}
