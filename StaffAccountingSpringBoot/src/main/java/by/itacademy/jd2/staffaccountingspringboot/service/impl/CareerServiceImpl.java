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
import by.itacademy.jd2.staffaccountingspringboot.repository.CareerRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.EmployeeRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.PositionRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.CareerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final String APPOINT_SUCCESS_LOG =
            "Employee with ID={} appointed to position with ID={} successfully";
    private static final String DISMISS_SUCCESS_LOG = "Employee with ID={} dismissed successfully";
    private static final String EDIT_SUCCESS_LOG = "Career step with ID={} edited successfully";
    private static final String ID_IS_NULL_LOG = "Career can't be to edit, ID is null";
    private static final String DELETE_SUCCESS_LOG = "Career step with ID={} deleted successfully";
    private static final String CAREER_STEP_NOT_FOUND_LOG = "Career step with ID={} not found";
    private static final String CAREER_STEP_NOT_FOUND_EXCEPTION = "Career step not found. ID=";
    private static final String NO_CAREER_STEPS_FOUND_LOG =
            "No career steps found for the provided parameters: page={}, size={}";
    private static final String SUCCESS_CAREER_FOUND_LOG = "Successfully fetched {} career steps from database";
    private static final String EMPLOYEE_AND_POSITION_ITEMS_FOUND_LOG =
            "All current employees and all actual positions fetched successfully";
    private final CareerRepository careerRepository;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    @Override
    public void appointEmployee(CareerStepSaveDTO careerStepDTO) {
        CareerStepEntity careerStepEntity = Converter.toEntity(careerStepDTO, CareerStepEntity.class);
        fillingCareerFieldsInOldRows(careerStepDTO.getEmployeeId(),
                careerStepEntity.getOrderAppointment(),
                careerStepEntity.getDateOfAppointment());

        careerRepository.save(careerStepEntity);

        LOGGER.info(APPOINT_SUCCESS_LOG, careerStepDTO.getEmployeeId(), careerStepDTO.getPositionId());
    }

    @Override
    public void dismissEmployee(DismissDTO dismissDTO) {
        this.fillingCareerFieldsInOldRows(dismissDTO.getEmployeeId(),
                dismissDTO.getOrderDismiss(),
                dismissDTO.getDateOfDismiss());
        employeeRepository.updateIsFired(dismissDTO.getEmployeeId());

        LOGGER.info(DISMISS_SUCCESS_LOG, dismissDTO.getEmployeeId());
    }

    @Override
    public void editCareerStep(CareerStepSaveDTO careerStepDTO) {
        CareerStepEntity entity = Converter.toEntity(careerStepDTO, CareerStepEntity.class);
        if (entity.getId() != null) {
            careerRepository.save(entity);
            LOGGER.info(EDIT_SUCCESS_LOG, entity.getId());
        } else {
            LOGGER.warn(ID_IS_NULL_LOG);
        }
    }

    @Override
    public void deleteCareerStep(Long id) {
        careerRepository.deleteById(id);
        LOGGER.info(DELETE_SUCCESS_LOG, id);
    }

    @Override
    public EditCareerDTO getInfoForEditingCareerStep(Long id) {
        CareerStepEntity careerStep = careerRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error(CAREER_STEP_NOT_FOUND_LOG, id);
                    return new EntityNotFoundException(CAREER_STEP_NOT_FOUND_EXCEPTION + id);
                });



        return EditCareerDTO.builder()
                .careerStep(Converter.toDto(careerStep, CareerStepGetDTO.class))
                .positions(positionRepository.findAllByIsActualTrue().stream()
                        .map(entity -> Converter.toDto(entity, PositionItemDTO.class))
                        .toList())
                .build();
    }

    @Override
    public Page<CareerStepGetDTO> getEmployeesCareer(Long employeeId, int page, int size) {
        Page<CareerStepEntity> career =
                careerRepository.findAllByEmployeeIdOrderByDateOfAppointment(employeeId, PageRequest.of(page, size));
        if (career.getContent().isEmpty()) {
            LOGGER.warn(NO_CAREER_STEPS_FOUND_LOG, page, size);
        } else {
            LOGGER.info(SUCCESS_CAREER_FOUND_LOG, career.getContent().size());
        }

        return career.map(entity -> Converter.toDto(entity, CareerStepGetDTO.class));
    }

    @Override
    public AppointmentInfoDTO getAppointmentInfo() {
        List<EmployeeItemDTO> employees = employeeRepository.findAllByIsFiredFalse().stream()
                .map(entity -> Converter.toDto(entity, EmployeeItemDTO.class))
                .toList();
        List<PositionItemDTO> positions = positionRepository.findAllByIsActualTrue().stream()
                .map(entity -> Converter.toDto(entity, PositionItemDTO.class))
                .toList();

        LOGGER.info(EMPLOYEE_AND_POSITION_ITEMS_FOUND_LOG);

        return AppointmentInfoDTO.builder()
                .employees(employees)
                .positions(positions)
                .build();
    }

    private void fillingCareerFieldsInOldRows(Long employeeId, String orderLiberation, Date dateLiberation) {
        careerRepository.findAllByEmployeeIdAndIsCurrentTrue(employeeId)
                .forEach(careerStep -> {
                    careerStep.setCurrent(false);
                    careerStep.setOrderLiberation(orderLiberation);
                    careerStep.setDateOfLiberationPosition(dateLiberation);
                });
    }
}
