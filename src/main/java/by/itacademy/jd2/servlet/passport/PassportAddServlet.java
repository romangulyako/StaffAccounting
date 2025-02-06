package by.itacademy.jd2.servlet.passport;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.PassportDTO;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.servlet.converter.HttpRequestConverter;
import by.itacademy.jd2.utils.LocalizationUtil;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "passportAddServlet", value = "/add_passport")
public class PassportAddServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(PassportAddServlet.class);
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Locale locale = LocalizationUtil.getLocale(req);
            req.setAttribute(ConstantParamAndAttribute.LOCALE, locale);

            Long employee_id = ServletUtil.getParamLong(req, ConstantParamAndAttribute.ID);
            req.setAttribute(ConstantParamAndAttribute.ID, employee_id);
            req.getRequestDispatcher(ConstantJSP.ADD_PASSPORT_PAGE).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PassportDTO passport = HttpRequestConverter.convert(req, PassportDTO.class);
            employeeService.addPassport(passport);
            req.getRequestDispatcher(ConstantAction.PASSPORT).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
    }
}
