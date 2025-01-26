package by.itacademy.jd2.servlet.employee;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.EmployeeFilterData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "clearFilterServlet", value = "/clear_filter")
public class ClearFilterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeFilterData filterData = new EmployeeFilterData();
        req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_FILTER_DATA, filterData);
        req.getRequestDispatcher(ConstantAction.LIST_EMPLOYEES).forward(req, resp);
    }
}
