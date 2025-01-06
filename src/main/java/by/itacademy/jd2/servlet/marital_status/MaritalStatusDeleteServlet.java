package by.itacademy.jd2.servlet.marital_status;

import by.itacademy.jd2.constant.ConstantAction;
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

@WebServlet(name = "maritalStatusDeleteServlet", value = "/delete_marital_status")
public class MaritalStatusDeleteServlet extends HttpServlet {
    private final MaritalStatusService maritalStatusService = new MaritalStatusServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        maritalStatusService.deleteMaritalStatus(
                ParseUtil.parseLong(
                        ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
        req.getRequestDispatcher(ConstantAction.MARITAL_STATUSES).forward(req, resp);
    }

    @Override
    public void destroy() {
        maritalStatusService.closeDao();
    }
}
