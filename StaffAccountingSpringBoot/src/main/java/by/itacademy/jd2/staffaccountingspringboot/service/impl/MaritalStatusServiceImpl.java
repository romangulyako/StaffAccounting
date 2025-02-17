package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.MaritalStatusEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.MaritalStatusDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.MaritalStatusRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.MaritalStatusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MaritalStatusServiceImpl implements MaritalStatusService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MaritalStatusServiceImpl.class);
    private final MaritalStatusRepository maritalStatusRepository;

    @Override
    public void saveOrUpdateMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        MaritalStatusEntity maritalStatusEntity = Converter.toEntity(maritalStatusDTO, MaritalStatusEntity.class);
        maritalStatusRepository.save(maritalStatusEntity);
        LOGGER.info("Marital status saved successfully. ID: {}", maritalStatusEntity.getId());
    }

    @Override
    public void deleteMaritalStatus(Long id) {
        maritalStatusRepository.deleteById(id);
        LOGGER.info("Marital status with id={} deleted successfully", id);
    }

    @Override
    public MaritalStatusDTO getMaritalStatus(Long id) {
        MaritalStatusEntity maritalStatus = maritalStatusRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.info("Marital status with id={} not found", id);
                    return new EntityNotFoundException("Marital status with id " + id + " not found");
                });

        LOGGER.info("Successfully fetched marital status with id={} from database", id);
        return Converter.toDto(maritalStatus, MaritalStatusDTO.class);
    }

    @Override
    public Page<MaritalStatusDTO> getAllMaritalStatuses(Long employeeId, Pageable pageable) {
        Page<MaritalStatusEntity> maritalStatuses = maritalStatusRepository.findAllByEmployeeIdOrderByRegistrationDate(employeeId, pageable);
        if (maritalStatuses.isEmpty()) {
            LOGGER.warn("No marital statuses found for employee ID={} for the provided parameters: page={}, size={}",
                    employeeId, pageable.getPageNumber(), pageable.getPageSize());
        } else {
            LOGGER.info("Successfully fetched {} marital statuses from the database", maritalStatuses.getContent().size());
        }

        return maritalStatuses.map(entity -> Converter.toDto(entity, MaritalStatusDTO.class));
    }
}
