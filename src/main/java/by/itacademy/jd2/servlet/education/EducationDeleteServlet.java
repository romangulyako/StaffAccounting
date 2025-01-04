package by.itacademy.jd2.servlet.education;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
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
import java.util.List;

@WebServlet(name = "educationDeleteServlet", value = "/delete_education")
public class EducationDeleteServlet extends HttpServlet {
    private final EducationService educationService = new EducationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        educationService.deleteEducation(ParseUtil.parseLong(
                ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
        Long employeeId = ParseUtil.parseLong(
                ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID));
        List<EducationDTO> education = educationService.getEducationsByEmployeeId(employeeId);
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, employeeId);
        req.setAttribute(ConstantParamAndAttribute.LIST_EDUCATION, education);
        req.getRequestDispatcher(ConstantJSP.EDUCATION_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        educationService.closeDao();
    }
}
