package by.itacademy.jd2.servlet.career;

import by.itacademy.jd2.constant.ConstantAction;
import by.itacademy.jd2.constant.ConstantJSP;
import by.itacademy.jd2.constant.ConstantParamAndAttribute;
import by.itacademy.jd2.dto.CareerStepGetDTO;
import by.itacademy.jd2.dto.CareerStepSaveDTO;
import by.itacademy.jd2.dto.PositionItemDTO;
import by.itacademy.jd2.service.api.CareerService;
import by.itacademy.jd2.service.api.PositionService;
import by.itacademy.jd2.service.impl.CareerServiceImpl;
import by.itacademy.jd2.service.impl.PositionServiceImpl;
import by.itacademy.jd2.servlet.converter.HttpRequestConverter;
import by.itacademy.jd2.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "careerStepUpdateServlet", value = "/update_career_step")
public class CareerStepUpdateServlet extends HttpServlet {
    private final CareerService careerService = new CareerServiceImpl();
    private final PositionService positionService = new PositionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = ServletUtil.getParamLong(req, ConstantParamAndAttribute.CAREER_ID);
        final CareerStepGetDTO careerStep = careerService.getCareerStep(id);
        List<PositionItemDTO> positionItems = positionService.getAllPositionItems();
        req.setAttribute(ConstantParamAndAttribute.POSITION_ITEMS, positionItems);

        req.setAttribute(ConstantParamAndAttribute.CAREER_STEP, careerStep);
        req.getRequestDispatcher(ConstantJSP.UPDATE_CAREER_STEP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CareerStepSaveDTO careerStepSaveDTO =
                    HttpRequestConverter.convert(req, CareerStepSaveDTO.class);
            careerService.updateCareerStep(careerStepSaveDTO, careerStepSaveDTO.getId());
            req.getRequestDispatcher(ConstantAction.CAREER).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ConstantAction.ERROR).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
