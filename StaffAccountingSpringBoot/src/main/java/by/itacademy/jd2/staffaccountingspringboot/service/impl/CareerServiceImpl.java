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
        CareerStepEntity careerStepEntity = Converter.toEntity(careerStepDTO, CareerStepEntity.class);
        careerRepository.findAllByEmployeeIdAndIsCurrentTrue(careerStepDTO.getEmployeeId())
                .forEach(careerStep -> {
                    careerStep.setCurrent(false);
                    careerStep.setOrderLiberation(careerStepEntity.getOrderAppointment());
                    careerStep.setDateOfLiberationPosition(careerStepEntity.getDateOfAppointment());
                });

        careerRepository.save(careerStepEntity);

        LOGGER.info("Employee with id={} appointed to position with id={} successfully", careerStepDTO.getEmployeeId(), careerStepDTO.getPositionId());
    }

    @Override
    public void dismissEmployee(DismissDTO dismissDTO) {
        careerRepository.findAllByEmployeeIdAndIsCurrentTrue(dismissDTO.getEmployeeId())
                .forEach(careerStep -> {
                    careerStep.setCurrent(false);
                    careerStep.setOrderLiberation(dismissDTO.getOrderDismiss());
                    careerStep.setDateOfLiberationPosition(dismissDTO.getDateOfDismiss());
                });

        employeeRepository.updateIsFired(dismissDTO.getEmployeeId());
        LOGGER.info("Employee with id={} dismissed successfully", dismissDTO.getEmployeeId());
    }

    @Override
    public void editCareerStep(CareerStepSaveDTO careerStepDTO) {
        CareerStepEntity entity = Converter.toEntity(careerStepDTO, CareerStepEntity.class);
        if (entity.getId() != null) {
            careerRepository.save(entity);
            LOGGER.info("Career step with id={} edited successfully", entity.getId());
        }
    }

    @Override
    public void deleteCareerStep(Long id) {
        careerRepository.deleteById(id);
        LOGGER.info("Career Step with id={} deleted successfully", id);
    }

    @Override
    public EditCareerDTO getInfoForEditingCareerStep(Long id) {
        CareerStepEntity careerStep = careerRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Career Step with id={} not found", id);
                    return new EntityNotFoundException("Career Step with id=" + id + "not found");
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
            LOGGER.warn("No career steps found for the provided parameters: page={}, size={}", page, size);
        } else {
            LOGGER.info("Successfully fetched {} career steps from database", career.getContent().size());
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

        LOGGER.info("All current employees and all actual positions fetched successfully");

        return AppointmentInfoDTO.builder()
                .employees(employees)
                .positions(positions)
                .build();
    }
}
