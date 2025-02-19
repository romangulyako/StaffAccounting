package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.CareerStepEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.PositionEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.PositionDTO;
import by.itacademy.jd2.staffaccountingspringboot.model.PositionHistoryDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.CareerRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.PositionRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.PositionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);
    private final PositionRepository positionRepository;
    private final CareerRepository careerRepository;

    @Override
    public void saveOrUpdatePosition(PositionDTO positionDTO) {
        PositionEntity entity = Converter.toEntity(positionDTO, PositionEntity.class);
        positionRepository.save(entity);
        LOGGER.info("Position saved successfully. ID={}", entity.getId());
    }

    @Override
    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
        LOGGER.info("Position with ID={} deleted successfully", id);
    }

    @Override
    public PositionDTO getPositionById(Long id) {
        PositionEntity entity = this.findPositionById(id);

        LOGGER.info("Successfully fetched position with ID={}", id);
        return Converter.toDto(entity, PositionDTO.class);
    }

    @Override
    public Page<PositionDTO> getPositionsByDepartmentAndActual(Long departmentId,
                                                               Boolean isActual,
                                                               int page,
                                                               int size) {
        Page<PositionEntity> pagePositions =
                positionRepository.findAllByDepartmentIdAndIsActual(departmentId, isActual, PageRequest.of(page, size));

        if (pagePositions.getContent().isEmpty()) {
            LOGGER.warn("No positions found for the provided parameters: page={}, size={}", page, size);
        } else {
            LOGGER.info("Successfully fetched {} positions from database", pagePositions.getContent().size());
        }

        return pagePositions.map(entity -> Converter.toDto(entity, PositionDTO.class));
    }

    @Override
    public void reducePosition(Long id) {
        PositionEntity position = findPositionById(id);
        position.getHistory().stream()
                .filter(CareerStepEntity::isCurrent)
                .forEach(careerStep -> {
                    careerStep.setCurrent(false);
                    careerStep.setDateOfLiberationPosition(Date.valueOf(LocalDate.now()));
                });
        position.setIsActual(false);
        positionRepository.save(position);
        LOGGER.info("Successfully reduced position with ID={}", id);
    }

    @Override
    public void restorePosition(Long id) {
        PositionEntity position = findPositionById(id);
        position.setIsActual(true);
        positionRepository.save(position);
        LOGGER.info("Successfully restored position with ID={}", id);
    }

    private PositionEntity findPositionById(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Position with ID={} not found", id);
                    return new EntityNotFoundException("Position with ID=" + id + " not found");
                });
    }

    @Override
    public Page<PositionHistoryDTO> getPositionHistory(Long id, int page, int size) {
        Page<CareerStepEntity> history =
                careerRepository.findAllByPositionIdOrderByDateOfAppointment(id, PageRequest.of(page, size));
        if (history.getContent().isEmpty()) {
            LOGGER.warn("No rows found for the provided parameters: page={}, size={}", page, size);
        } else {
            LOGGER.info("Successfully fetched {} rows of history from database", history.getContent().size());
        }

        return history.map(entity -> Converter.toDto(entity, PositionHistoryDTO.class));
    }
}
