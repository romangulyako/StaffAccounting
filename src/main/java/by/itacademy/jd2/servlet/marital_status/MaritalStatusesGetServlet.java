package by.itacademy.jd2.servlet.marital_status;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.service.api.MaritalStatusService;
import by.itacademy.jd2.service.impl.MaritalStatusServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "maritalStatusesGetServlet", value = "/marital_statuses")
public class MaritalStatusesGetServlet extends HttpServlet {
    private final MaritalStatusService maritalStatusService = new MaritalStatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long employeeId = ParseUtil.parseLong(
                ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID));
        final List<MaritalStatusDTO> maritalStatuses =
                maritalStatusService.getAllMaritalStatuses(employeeId);
        req.setAttribute(ConstantParamAndAttribute.LIST_MARITAL_STATUSES, maritalStatuses);
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, employeeId);
        req.getRequestDispatcher(ConstantJSP.MARITAL_STATUSES_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        maritalStatusService.closeDao();
    }
}
