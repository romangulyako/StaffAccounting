package by.itacademy.jd2.servlet.marital_status;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.service.api.MaritalStatusService;
import by.itacademy.jd2.service.impl.MaritalStatusServiceImpl;
import by.itacademy.jd2.servlet.converter.HttpRequestConverter;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "maritalStatusAddServlet", value = "/add_marital_status")
public class MaritalStatusAddServlet extends HttpServlet {
    private final MaritalStatusService maritalStatusService = new MaritalStatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long employeeId = ParseUtil.parseLong(ServletUtil
                    .getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID));
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, employeeId);
            req.getRequestDispatcher(ConstantJSP.ADD_MARITAL_STATUS_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            maritalStatusService.addMaritalStatus(HttpRequestConverter.getConverter().convert(req,
                    MaritalStatusDTO.class));
            req.getRequestDispatcher(ConstantAction.MARITAL_STATUSES).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        maritalStatusService.closeDao();
    }
}
