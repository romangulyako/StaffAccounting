package by.itacademy.jd2.servlet.career;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.dto.EmployeeItemDTO;
import by.itacademy.jd2.dto.PositionItemDTO;
import by.itacademy.jd2.service.api.CareerService;
import by.itacademy.jd2.service.api.EmployeeService;
import by.itacademy.jd2.service.api.PositionService;
import by.itacademy.jd2.service.impl.CareerServiceImpl;
import by.itacademy.jd2.service.impl.EmployeeServiceImpl;
import by.itacademy.jd2.service.impl.PositionServiceImpl;
import by.itacademy.jd2.servlet.converter.HttpRequestConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "appointToPositionServlet", value = "/appointment")
public class AppointmentServlet extends HttpServlet {
    private final CareerService careerService = new CareerServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final PositionService positionService = new PositionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<EmployeeItemDTO> employeeItems = employeeService.getAllEmployeeItems(false);
            List<PositionItemDTO> positionItems = positionService.getAllPositionItems();
            req.setAttribute(ConstantParamAndAttribute.EMPLOYEE_ITEMS, employeeItems);
            req.setAttribute(ConstantParamAndAttribute.POSITION_ITEMS, positionItems);
            req.getRequestDispatcher(ConstantJSP.APPOINTMENT_PAGE).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            careerService.appointEmployee(HttpRequestConverter.getConverter().convert(req, CareerStepSaveDTO.class));
            req.getRequestDispatcher(ConstantAction.CAREER).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        employeeService.closeDao();
        positionService.closeDao();
        careerService.closeDao();
    }
}
