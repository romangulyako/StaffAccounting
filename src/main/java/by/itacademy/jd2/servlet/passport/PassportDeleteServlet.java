package by.itacademy.jd2.servlet.passport;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "passportDeleteServlet", value = "/delete_passport")
public class PassportDeleteServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID);
            employeeService.deletePassport(id);
            req.getRequestDispatcher(ConstantAction.EMPLOYEE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        this.employeeService.closeDao();
    }
}
