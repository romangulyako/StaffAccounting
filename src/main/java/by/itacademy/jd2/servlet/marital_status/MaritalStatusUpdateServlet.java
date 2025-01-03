package by.itacademy.jd2.servlet.marital_status;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.converter.MaritalStatusConverter;
import by.itacademy.jd2.dto.MaritalStatusDTO;
import by.itacademy.jd2.service.api.MaritalStatusService;
import by.itacademy.jd2.service.impl.MaritalStatusServiceImpl;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "maritalStatusUpdateServlet", value = "/update_marital_status")
public class MaritalStatusUpdateServlet extends HttpServlet {
    private final MaritalStatusService maritalStatusService = new MaritalStatusServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final MaritalStatusDTO maritalStatus = maritalStatusService.getMaritalStatus(
                    ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
            req.setAttribute(ConstantParamAndAttribute.MARITAL_STATUS, maritalStatus);

            RequestDispatcher requestDispatcher = getServletContext()
                    .getRequestDispatcher(ConstantJSP.UPDATE_MARITAL_STATUS_PAGE);
            requestDispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Передан неверный параметр");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        } catch (NullPointerException e) {
            req.setAttribute(ConstantParamAndAttribute.ERROR, "Семейного положения с таким id нет!");
            req.getRequestDispatcher(ConstantJSP.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MaritalStatusDTO maritalStatus = MaritalStatusConverter.fromHttpRequest(req);
        maritalStatusService.updateMaritalStatus(maritalStatus);
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, maritalStatus.getEmployeeId());
        req.setAttribute(ConstantParamAndAttribute.LIST_MARITAL_STATUSES,
                maritalStatusService.getAllMaritalStatuses(maritalStatus.getEmployeeId()));
        req.getRequestDispatcher(ConstantJSP.MARITAL_STATUSES_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        maritalStatusService.closeDao();
    }
}
