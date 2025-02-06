package by.itacademy.jd2.servlet.department;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.service.PageInfo;
import by.itacademy.jd2.service.api.DepartmentService;
import by.itacademy.jd2.service.impl.DepartmentServiceImpl;
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

@WebServlet(name = "departmentsListServlet", value = "/list_departments")
public class DepartmentsListServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentsListServlet.class);
    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Locale locale = LocalizationUtil.getLocale(req);
            req.setAttribute(ConstantParamAndAttribute.LOCALE, locale);

            Integer pageSize = ServletUtil.getParamInt(req, ConstantParamAndAttribute.PAGE_SIZE);
            Integer pageNumber = ServletUtil.getParamInt(req, ConstantParamAndAttribute.PAGE_NUMBER);
            Boolean isActual = ServletUtil.getParamBoolean(req, ConstantParamAndAttribute.IS_ACTUAL);

            PageInfo<DepartmentDTO> pageItems = departmentService.getDepartmentsByActualAndPage(isActual,
                    pageNumber, pageSize);
            req.setAttribute(ConstantParamAndAttribute.PAGE_INFO, pageItems);
            req.setAttribute(ConstantParamAndAttribute.IS_ACTUAL, isActual);

            req.getRequestDispatcher(ConstantJSP.LIST_DEPARTMENTS_PAGE).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        departmentService.closeDao();
    }
}
