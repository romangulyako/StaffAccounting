package by.itacademy.jd2.servlet.education;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.converter.EducationConverter;
import by.itacademy.jd2.dto.EducationDTO;
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

@WebServlet(name = "educationAddServlet", value = "/add_education")
public class EducationAddServlet extends HttpServlet {
    private final EducationService educationService = new EducationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long employeeId = ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID));
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, employeeId);
        req.getRequestDispatcher(ConstantJSP.ADD_EDUCATION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EducationDTO education = EducationConverter.fromHttpRequest(req);
        educationService.addEducation(education);
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID,
                education.getEmployeeId());
        req.setAttribute(ConstantParamAndAttribute.LIST_EDUCATION,
                educationService.getEducationsByEmployeeId(education.getEmployeeId()));
        req.getRequestDispatcher(ConstantJSP.EDUCATION_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        educationService.closeDao();
    }
}
