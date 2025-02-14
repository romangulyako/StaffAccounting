package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.MaritalStatusEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.MaritalStatusDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.EmployeeRepository;
import by.itacademy.jd2.staffaccountingspringboot.repository.MaritalStatusRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.MaritalStatusService;
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
    private final EmployeeRepository employeeRepository;

    @Override
    public void addMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        if (maritalStatusDTO != null) {
            MaritalStatusEntity maritalStatusEntity = Converter.toEntity(maritalStatusDTO, MaritalStatusEntity.class);
            EmployeeEntity employeeEntity = employeeRepository.findById(maritalStatusDTO.getEmployeeId()).orElse(null);
            if (employeeEntity != null) {
                employeeEntity.getMaritalStatuses().stream()
                        .filter(MaritalStatusEntity::getIsCurrent)
                        .forEach(status -> status.setIsCurrent(false));
                maritalStatusEntity.setIsCurrent(true);
                maritalStatusEntity.setEmployee(employeeEntity);
                employeeEntity.getMaritalStatuses().add(maritalStatusEntity);
                maritalStatusRepository.save(maritalStatusEntity);

                LOGGER.info("Added marital status for employee with id={}", maritalStatusDTO.getEmployeeId());
            }
        }
    }

    @Override
    public void editMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        if (maritalStatusDTO != null) {
            MaritalStatusEntity maritalStatus = Converter.toEntity(maritalStatusDTO, MaritalStatusEntity.class);
            EmployeeEntity employeeEntity = employeeRepository.findById(maritalStatusDTO.getEmployeeId()).orElse(null);
            if (employeeEntity != null) {
                maritalStatus.setEmployee(employeeEntity);
                maritalStatusRepository.save(maritalStatus);

                LOGGER.info("Updated marital status with id={}", maritalStatusDTO.getId());
            }
        }
    }

    @Override
    public void deleteMaritalStatus(Long id) {
        MaritalStatusEntity maritalStatus = maritalStatusRepository.findById(id).orElse(null);
        if (maritalStatus != null) {
            maritalStatusRepository.delete(maritalStatus);

            LOGGER.info("Deleted marital status with id={}", id);
        }

    }

    @Override
    public MaritalStatusDTO getMaritalStatus(Long id) {
        MaritalStatusEntity maritalStatus = maritalStatusRepository.findById(id).orElse(null);
        if (maritalStatus != null) {
            LOGGER.info("Found marital status with id={}", id);
        }

        return Converter.toDto(maritalStatus, MaritalStatusDTO.class);
    }

    @Override
    public Page<MaritalStatusDTO> getAllMaritalStatuses(Long employeeId, Pageable pageable) {
        Page<MaritalStatusEntity> maritalStatuses = maritalStatusRepository.findAllByEmployeeId(employeeId, pageable);
        LOGGER.info("Found {} from {} marital statuses", maritalStatuses.stream().count(), maritalStatuses.getTotalElements());
        return maritalStatuses.map(entity -> Converter.toDto(entity, MaritalStatusDTO.class));
    }
}
