package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.RelativeEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.RelativeDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.RelativeRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.RelativeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RelativeServiceImpl implements RelativeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RelativeServiceImpl.class);
    private static final String SAVE_SUCCESS_LOG = "Relative saved successfully. ID={}";
    private static final String DELETE_SUCCESS_LOG = "Relative with id={} deleted successfully";
    private static final String NOT_FOUND_LOG = "Relative with id={} not found";
    private static final String NOT_FOUND_EXCEPTION = "Relative not found. ID=";
    private static final String GET_SUCCESS_LOG = "Successfully fetched relative with id={} from database";
    private static final String NOT_FOUND_LIST_LOG =
            "No relatives found for employee ID={} for the provided parameters: page={}, size={}";
    private static final String GET_LIST_SUCCESS_LOG = "Successfully fetched {} relatives from the database";
    private final RelativeRepository relativeRepository;

    @Override
    public void saveOrUpdateRelative(RelativeDTO relativeDTO) {
        RelativeEntity relativeEntity = Converter.toEntity(relativeDTO, RelativeEntity.class);
        relativeRepository.save(relativeEntity);
        LOGGER.info(SAVE_SUCCESS_LOG, relativeEntity.getId());
    }

    @Override
    public void deleteRelative(Long id) {
        relativeRepository.deleteById(id);
        LOGGER.info(DELETE_SUCCESS_LOG, id);
    }

    @Override
    public RelativeDTO getRelative(Long id) {
        RelativeEntity relativeEntity = relativeRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.info(NOT_FOUND_LOG, id);
                    return new EntityNotFoundException(NOT_FOUND_EXCEPTION + id);
                });

        LOGGER.info(GET_SUCCESS_LOG, id);
        return Converter.toDto(relativeEntity, RelativeDTO.class);
    }

    @Override
    public Page<RelativeDTO> getRelatives(Long employeeId, int page, int size) {
        Page<RelativeEntity> entities = relativeRepository.findAllByEmployeeId(employeeId, PageRequest.of(page, size));
        if (entities.getContent().isEmpty()) {
            LOGGER.warn(NOT_FOUND_LIST_LOG, employeeId, page, size);
        } else {
            LOGGER.info(GET_LIST_SUCCESS_LOG, entities.getContent().size());
        }

        return entities.map(entity -> Converter.toDto(entity, RelativeDTO.class));
    }
}
