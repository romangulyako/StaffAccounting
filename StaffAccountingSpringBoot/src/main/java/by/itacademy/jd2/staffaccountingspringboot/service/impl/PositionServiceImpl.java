package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.CareerStepEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.PositionEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.PositionDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PositionHistoryDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.CareerRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.PositionRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.PositionService;
import by.itacademy.jd2.staffaccountingspringboot.utils.Constant;
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
    private static final String NOT_FOUND_EXCEPTION = "Position not found. ID=";

    private final PositionRepository positionRepository;
    private final CareerRepository careerRepository;

    @Override
    public void saveOrUpdatePosition(PositionDTO positionDTO) {
        LOGGER.debug(Constant.ATTEMPT_TO_SAVE_POSITION, positionDTO.getDepartmentId());
        PositionEntity entity = Converter.toEntity(positionDTO, PositionEntity.class);
        positionRepository.save(entity);
        LOGGER.info(Constant.SAVE_POSITION_SUCCESS, entity.getId());
    }

    @Override
    public void deletePosition(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_DELETE_POSITION, id);
        positionRepository.deleteById(id);
        LOGGER.info(Constant.DELETE_POSITION_SUCCESS, id);
    }

    @Transactional(readOnly = true)
    @Override
    public PositionDTO getPositionById(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_FETCH_POSITION, id);
        PositionEntity entity = this.findPositionById(id);
        LOGGER.info(Constant.GET_POSITION_SUCCESS, id);
        return Converter.toDto(entity, PositionDTO.class);
    }

    @Override
    public void reducePosition(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_REDUCE_POSITION, id);
        PositionEntity position = this.findPositionById(id);
        position.getHistory().stream()
                .filter(CareerStepEntity::isCurrent)
                .forEach(careerStep -> {
                    careerStep.setCurrent(false);
                    careerStep.setDateOfLiberationPosition(Date.valueOf(LocalDate.now()));
                });
        position.setIsActual(false);
        positionRepository.save(position);
        LOGGER.info(Constant.REDUCE_POSITION_SUCCESS, id);
    }

    @Override
    public void restorePosition(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_RESTORE_POSITION, id);
        PositionEntity position = this.findPositionById(id);
        position.setIsActual(true);
        positionRepository.save(position);
        LOGGER.info(Constant.RESTORE_POSITION_SUCCESS, id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PositionHistoryDTO> getPositionHistory(Long id, int page, int size) {
        LOGGER.debug(Constant.ATTEMPT_TO_GET_HISTORY_OF_POSITION, id);
        this.findPositionById(id);
        Page<CareerStepEntity> history =
                careerRepository.findAllByPositionIdOrderByDateOfAppointment(id, PageRequest.of(page, size));
        if (history.getContent().isEmpty()) {
            LOGGER.warn(Constant.NOT_FOUND_POSITION_HISTORY, page, size);
        } else {
            LOGGER.info(Constant.GET_POSITION_HISTORY_SUCCESS, history.getContent().size());
        }

        return history.map(entity -> Converter.toDto(entity, PositionHistoryDTO.class));
    }

    private PositionEntity findPositionById(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error(Constant.POSITION_NOT_FOUND, id);
                    return new EntityNotFoundException(NOT_FOUND_EXCEPTION + id);
                });
    }
}
