package by.itacademy.jd2.servlet.relatives;

import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.RelativeDTO;
import by.itacademy.jd2.service.api.RelativeService;
import by.itacademy.jd2.service.impl.RelativeServiceImpl;
import by.itacademy.jd2.utils.HibernateUtil;
import by.itacademy.jd2.utils.ParseUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "deleteRelativeServlet", value = "/delete_relative")
public class DeleteRelativeServlet extends HttpServlet {
    private final RelativeService relativeService = new RelativeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        relativeService.deleteRelative(ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.ID)));
        Long employeeId = ParseUtil.parseLong(ServletUtil.getParam(req, ConstantParamAndAttribute.EMPLOYEE_ID));
        List<RelativeDTO> relatives = relativeService.getRelatives(employeeId);
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, employeeId);
        req.setAttribute(ConstantParamAndAttribute.LIST_RELATIVES, relatives);
        req.getRequestDispatcher(ConstantJSP.RELATIVES_PAGE).forward(req, resp);
    }

    @Override
    public void destroy() {
        this.relativeService.closeDao();
        HibernateUtil.close();
    }
}
