package by.itacademy.jd2.servlet.career;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DismissDTO;
import by.itacademy.jd2.dto.EmployeeItemDTO;
import by.itacademy.jd2.service.api.CareerService;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.service.impl.CareerServiceImpl;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.servlet.converter.HttpRequestConverter;
import by.itacademy.jd2.utils.LocalizationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "dismissServlet", value = "/dismiss")
public class DismissServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DismissServlet.class);
    private final CareerService careerService = new CareerServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Locale locale = LocalizationUtil.getLocale(req);
            req.setAttribute(ConstantParamAndAttribute.LOCALE, locale);

            List<EmployeeItemDTO> employeeItems = employeeService.getAllEmployeeItems(true);
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ITEMS, employeeItems);
            req.getRequestDispatcher(ConstantJSP.DISMISS_PAGE).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            careerService.dismissEmployee(HttpRequestConverter.convert(req, DismissDTO.class));
            resp.sendRedirect(ConstantAction.LIST_EMPLOYEES);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        careerService.closeDao();
    }
}
