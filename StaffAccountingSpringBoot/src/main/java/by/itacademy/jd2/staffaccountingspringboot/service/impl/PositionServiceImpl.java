package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.CareerStepEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.PositionEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.PositionDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PositionHistoryDTO;
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
    private static final String SAVE_SUCCESS_LOG = "Position saved successfully. ID={}";
    private static final String DELETE_SUCCESS_LOG = "Position with ID={} deleted successfully";
    private static final String GET_SUCCESS_LOG = "Successfully fetched position with ID={}";
    private static final String REDUCE_SUCCESS_LOG = "Successfully reduced position with ID={}";
    private static final String RESTORE_SUCCESS_LOG = "Successfully restored position with ID={}";
    private static final String NOT_FOUND_LIST_LOG = "No rows found for the provided parameters: page={}, size={}";
    private static final String GET_LIST_SUCCESS_LOG = "Successfully fetched {} rows of history from database";
    private static final String NOT_FOUND_LOG = "Position with ID={} not found";
    private static final String NOT_FOUND_EXCEPTION = "Position not found. ID=";
    private final PositionRepository positionRepository;
    private final CareerRepository careerRepository;

    @Override
    public void saveOrUpdatePosition(PositionDTO positionDTO) {
        PositionEntity entity = Converter.toEntity(positionDTO, PositionEntity.class);
        positionRepository.save(entity);
        LOGGER.info(SAVE_SUCCESS_LOG, entity.getId());
    }

    @Override
    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
        LOGGER.info(DELETE_SUCCESS_LOG, id);
    }

    @Override
    public PositionDTO getPositionById(Long id) {
        PositionEntity entity = this.findPositionById(id);

        LOGGER.info(GET_SUCCESS_LOG, id);
        return Converter.toDto(entity, PositionDTO.class);
    }

    @Override
    public void reducePosition(Long id) {
        PositionEntity position = this.findPositionById(id);
        position.getHistory().stream()
                .filter(CareerStepEntity::isCurrent)
                .forEach(careerStep -> {
                    careerStep.setCurrent(false);
                    careerStep.setDateOfLiberationPosition(Date.valueOf(LocalDate.now()));
                });
        position.setIsActual(false);
        positionRepository.save(position);
        LOGGER.info(REDUCE_SUCCESS_LOG, id);
    }

    @Override
    public void restorePosition(Long id) {
        PositionEntity position = this.findPositionById(id);
        position.setIsActual(true);
        positionRepository.save(position);
        LOGGER.info(RESTORE_SUCCESS_LOG, id);
    }

    @Override
    public Page<PositionHistoryDTO> getPositionHistory(Long id, int page, int size) {
        Page<CareerStepEntity> history =
                careerRepository.findAllByPositionIdOrderByDateOfAppointment(id, PageRequest.of(page, size));
        if (history.getContent().isEmpty()) {
            LOGGER.warn(NOT_FOUND_LIST_LOG, page, size);
        } else {
            LOGGER.info(GET_LIST_SUCCESS_LOG, history.getContent().size());
        }

        return history.map(entity -> Converter.toDto(entity, PositionHistoryDTO.class));
    }

    private PositionEntity findPositionById(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error(NOT_FOUND_LOG, id);
                    return new EntityNotFoundException(NOT_FOUND_EXCEPTION + id);
                });
    }
}
