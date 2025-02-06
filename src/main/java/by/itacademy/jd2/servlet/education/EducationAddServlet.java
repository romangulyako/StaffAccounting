package by.itacademy.jd2.servlet.education;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EducationDTO;
import by.itacademy.jd2.service.api.EducationService;
import by.itacademy.jd2.service.impl.EducationServiceImpl;
import by.itacademy.jd2.servlet.converter.HttpRequestConverter;
import by.itacademy.jd2.utils.LocalizationUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "educationAddServlet", value = "/add_education")
public class EducationAddServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(EducationAddServlet.class);
    private final EducationService educationService = new EducationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Locale locale = LocalizationUtil.getLocale(req);
            req.setAttribute(ConstantParamAndAttribute.LOCALE, locale);

            Long employeeId = ServletUtil.getParamLong(req, ConstantParamAndAttribute.EMPLOYEE_ID);
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, employeeId);
            req.getRequestDispatcher(ConstantJSP.ADD_EDUCATION_PAGE).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            educationService.addEducation(HttpRequestConverter.convert(req, EducationDTO.class));
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
