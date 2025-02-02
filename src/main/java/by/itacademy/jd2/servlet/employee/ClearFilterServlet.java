package by.itacademy.jd2.servlet.employee;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeFilterData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "clearFilterServlet", value = "/clear_filter")
public class ClearFilterServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClearFilterServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            EmployeeFilterData filterData = new EmployeeFilterData();
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_FILTER_DATA, filterData);
            req.getRequestDispatcher(ConstantAction.LIST_EMPLOYEES).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }

    }
}
