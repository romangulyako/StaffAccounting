package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.MaritalStatusEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.MaritalStatusDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.MaritalStatusRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.MaritalStatusService;
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
public class MaritalStatusServiceImpl implements MaritalStatusService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MaritalStatusServiceImpl.class);
    private static final String SAVE_SUCCESS_LOG = "Marital status saved successfully. ID: {}";
    private static final String DELETE_SUCCESS_LOG = "Marital status with ID={} deleted successfully";
    private static final String NOT_FOUND_LOG = "Marital status with ID={} not found";
    private static final String NOT_FOUND_EXCEPTION = "Marital status not found. ID=";
    private static final String FOUND_SUCCESS_LOG = "Successfully fetched marital status with ID={} from database";
    private static final String NOT_FOUND_LIST_LOG =
            "No marital statuses found for employee ID={} for the provided parameters: page={}, size={}";
    private static final String FOUND_LIST_SUCCESS_LOG = "Successfully fetched {} marital statuses from the database";
    private final MaritalStatusRepository maritalStatusRepository;

    @Override
    public void saveOrUpdateMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        MaritalStatusEntity maritalStatusEntity = Converter.toEntity(maritalStatusDTO, MaritalStatusEntity.class);
        maritalStatusRepository.save(maritalStatusEntity);
        LOGGER.info(SAVE_SUCCESS_LOG, maritalStatusEntity.getId());
    }

    @Override
    public void deleteMaritalStatus(Long id) {
        maritalStatusRepository.deleteById(id);
        LOGGER.info(DELETE_SUCCESS_LOG, id);
    }

    @Override
    public MaritalStatusDTO getMaritalStatus(Long id) {
        MaritalStatusEntity maritalStatus = maritalStatusRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.info(NOT_FOUND_LOG, id);
                    return new EntityNotFoundException(NOT_FOUND_EXCEPTION + id);
                });

        LOGGER.info(FOUND_SUCCESS_LOG, id);
        return Converter.toDto(maritalStatus, MaritalStatusDTO.class);
    }

    @Override
    public Page<MaritalStatusDTO> getAllMaritalStatuses(Long employeeId, int page, int size) {
        Page<MaritalStatusEntity> maritalStatuses =
                maritalStatusRepository.findAllByEmployeeIdOrderByRegistrationDate(employeeId,
                        PageRequest.of(page, size));
        if (maritalStatuses.isEmpty()) {
            LOGGER.warn(NOT_FOUND_LIST_LOG, employeeId, page, size);
        } else {
            LOGGER.info(FOUND_LIST_SUCCESS_LOG, maritalStatuses.getContent().size());
        }

        return maritalStatuses.map(entity -> Converter.toDto(entity, MaritalStatusDTO.class));
    }
}
