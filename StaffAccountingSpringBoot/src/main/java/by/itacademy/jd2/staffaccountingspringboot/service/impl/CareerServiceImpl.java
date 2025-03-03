package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.CareerStepEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.AppointmentInfoDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.CareerStepGetDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.CareerStepSaveDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.DismissDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EditCareerDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PositionItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.exception.AppointmentException;
import by.itacademy.jd2.staffaccountingspringboot.repository.CareerRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.EmployeeRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.PositionRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.CareerService;
import by.itacademy.jd2.staffaccountingspringboot.utils.Constant;
import by.itacademy.jd2.staffaccountingspringboot.utils.EmployeeUtils;
import by.itacademy.jd2.staffaccountingspringboot.utils.LocaleUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CareerServiceImpl.class);
    private final CareerRepository careerRepository;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    @Override
    public void appointEmployee(CareerStepSaveDTO careerStepDTO) {
        try {
            LOGGER.debug(Constant.ATTEMPT_TO_APPOINT_EMPLOYEE,
                    careerStepDTO.getEmployeeId(), careerStepDTO.getPositionId());
            CareerStepEntity careerStepEntity = Converter.toEntity(careerStepDTO, CareerStepEntity.class);
            this.updatePreviousCareerSteps(careerStepDTO.getEmployeeId(),
                    careerStepEntity.getOrderAppointment(),
                    careerStepEntity.getDateOfAppointment());

            careerRepository.save(careerStepEntity);
            LOGGER.info(Constant.APPOINT_EMPLOYEE_SUCCESS,
                    careerStepDTO.getEmployeeId(), careerStepDTO.getPositionId());
        } catch (DataIntegrityViolationException e) {
            throw new AppointmentException();
        }

    }

    @Override
    public void dismissEmployee(DismissDTO dismissDTO) {
        LOGGER.debug(Constant.ATTEMPT_TO_DISMISS_EMPLOYEE, dismissDTO.getEmployeeId());
        this.updatePreviousCareerSteps(dismissDTO.getEmployeeId(),
                dismissDTO.getOrderDismiss(),
                dismissDTO.getDateOfDismiss());
        employeeRepository.updateIsFired(dismissDTO.getEmployeeId());

        LOGGER.info(Constant.DISMISS_EMPLOYEE_SUCCESS, dismissDTO.getEmployeeId());
    }

    @Override
    public void editCareerStep(CareerStepSaveDTO careerStepDTO) {
        LOGGER.debug(Constant.ATTEMPT_TO_EDIT_CAREER_STEP, careerStepDTO.getId());
        CareerStepEntity entity = Converter.toEntity(careerStepDTO, CareerStepEntity.class);
        if (entity.getId() != null) {
            careerRepository.save(entity);
            LOGGER.info(Constant.EDIT_CAREER_STEP_SUCCESS, entity.getId());
        } else {
            LOGGER.warn(Constant.ID_IS_NULL_LOG);
        }
    }

    @Override
    public void deleteCareerStep(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_DELETE_CAREER_STEP, id);
        careerRepository.deleteById(id);
        LOGGER.info(Constant.DELETE_CAREER_STEP_SUCCESS, id);
    }

    @Transactional(readOnly = true)
    @Override
    public EditCareerDTO getInfoForEditingCareerStep(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_GET_INFO_FOR_EDITING_CAREER_STEP, id);
        CareerStepEntity careerStep = careerRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error(Constant.CAREER_STEP_NOT_FOUND, id);
                    return new EntityNotFoundException(LocaleUtils
                            .getMessage(Constant.CAREER_STEP_NOT_FOUND_EXCEPTION_MESSAGE_KEY) + id);
                });
        List<PositionItemDTO> positions = this.getActualPositionItems();

        LOGGER.info(Constant.INFO_FOR_EDITING_CAREER_STEP_FETCHED_SUCCESS, id);

        return EditCareerDTO.builder()
                .careerStep(Converter.toDto(careerStep, CareerStepGetDTO.class))
                .positions(positions)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CareerStepGetDTO> getEmployeesCareer(Long employeeId, int page, int size) {
        LOGGER.debug(Constant.ATTEMPT_TO_GET_CAREER_OF_EMPLOYEE, employeeId);
        EmployeeUtils.findById(employeeId);
        Page<CareerStepEntity> career =
                careerRepository.findAllByEmployeeIdOrderByDateOfAppointment(employeeId, PageRequest.of(page, size));
        if (career.getContent().isEmpty()) {
            LOGGER.warn(Constant.NO_CAREER_STEPS_FOUND, page, size);
        } else {
            LOGGER.info(Constant.SUCCESS_CAREER_FOUND, career.getContent().size());
        }

        return career.map(entity -> Converter.toDto(entity, CareerStepGetDTO.class));
    }

    @Transactional(readOnly = true)
    @Override
    public AppointmentInfoDTO getAppointmentInfo() {
        LOGGER.debug(Constant.ATTEMPT_TO_GET_INFO_FOR_APPOINTMENT);
        List<EmployeeItemDTO> employees = employeeRepository.findAllByIsFiredFalse().stream()
                .map(entity -> Converter.toDto(entity, EmployeeItemDTO.class))
                .toList();
        List<PositionItemDTO> positions = this.getActualPositionItems();

        LOGGER.info(Constant.EMPLOYEE_AND_POSITION_ITEMS_FOUND_LOG);

        return AppointmentInfoDTO.builder()
                .employees(employees)
                .positions(positions)
                .build();
    }

    private void updatePreviousCareerSteps(Long employeeId, String orderLiberation, Date dateLiberation) {
        careerRepository.findAllByEmployeeIdAndIsCurrentTrue(employeeId)
                .forEach(careerStep -> {
                    careerStep.setCurrent(false);
                    careerStep.setOrderLiberation(orderLiberation);
                    careerStep.setDateOfLiberationPosition(dateLiberation);
                });
    }

    private List<PositionItemDTO> getActualPositionItems() {
        return positionRepository.findAllByIsActualTrue().stream()
                .map(entity -> Converter.toDto(entity, PositionItemDTO.class))
                .toList();
    }
}
