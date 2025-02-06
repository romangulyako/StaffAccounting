package by.itacademy.jd2.servlet.career;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.CareerStepGetDTO;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.CareerService;
import by.itacademy.jd2.service.impl.CareerServiceImpl;
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

@WebServlet(name = "careerInfoServlet", value = "/career")
public class CareerInfoServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CareerInfoServlet.class);
    private final CareerService careerService = new CareerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Locale locale = LocalizationUtil.getLocale(req);
            req.setAttribute(ConstantParamAndAttribute.LOCALE, locale);

            Integer pageSize = ServletUtil.getParamInt(req, ConstantParamAndAttribute.PAGE_SIZE);
            Integer pageNumber = ServletUtil.getParamInt(req, ConstantParamAndAttribute.PAGE_NUMBER);
            Long employeeId = ServletUtil.getParamLong(req, ConstantParamAndAttribute.EMPLOYEE_ID);
            PageInfo<CareerStepGetDTO> pageInfo =
                    careerService.getCareerOfEmployeeByPage(employeeId, pageNumber, pageSize);
            req.setAttribute(ConstantParamAndAttribute.PAGE_INFO, pageInfo);
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ID, employeeId);
            req.getRequestDispatcher(ConstantJSP.CAREER_INFO_PAGE).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        careerService.closeDao();
    }
}
