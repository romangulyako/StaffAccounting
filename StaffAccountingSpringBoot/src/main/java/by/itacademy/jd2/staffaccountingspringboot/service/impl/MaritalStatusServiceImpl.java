package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.MaritalStatusEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.MaritalStatusDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.MaritalStatusRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.MaritalStatusService;
import by.itacademy.jd2.staffaccountingspringboot.utils.Constant;
import by.itacademy.jd2.staffaccountingspringboot.utils.EmployeeUtils;
import by.itacademy.jd2.staffaccountingspringboot.utils.LocaleUtils;
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
    private final MaritalStatusRepository maritalStatusRepository;

    @Override
    public void saveOrUpdateMaritalStatus(MaritalStatusDTO maritalStatusDTO) {
        LOGGER.debug(Constant.ATTEMPT_TO_SAVE_MARITAL_STATUS, maritalStatusDTO.getEmployeeId());
        MaritalStatusEntity maritalStatusEntity = Converter.toEntity(maritalStatusDTO, MaritalStatusEntity.class);
        maritalStatusRepository.save(maritalStatusEntity);
        LOGGER.info(Constant.SAVE_MARITAL_STATUS_SUCCESS, maritalStatusEntity.getId());
    }

    @Override
    public void deleteMaritalStatus(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_DELETE_MARITAL_STATUS, id);
        maritalStatusRepository.deleteById(id);
        LOGGER.info(Constant.DELETE_MARITAL_STATUS_SUCCESS, id);
    }

    @Transactional(readOnly = true)
    @Override
    public MaritalStatusDTO getMaritalStatus(Long id) {
        LOGGER.debug(Constant.ATTEMPT_TO_GET_MARITAL_STATUS, id);
        MaritalStatusEntity maritalStatus = maritalStatusRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.info(Constant.NOT_FOUND_MARITAL_STATUS, id);
                    return new EntityNotFoundException(LocaleUtils
                            .getMessage(Constant.MARITAL_NOT_FOUND_EXCEPTION_MESSAGE_KEY)+ id);
                });

        LOGGER.info(Constant.FOUND_MARITAL_STATUS_SUCCESS, id);
        return Converter.toDto(maritalStatus, MaritalStatusDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MaritalStatusDTO> getMaritalStatusesByEmployee(Long employeeId, int page, int size) {
        LOGGER.debug(Constant.ATTEMPT_TO_GET_MARITAL_STATUSES_BY_EMPLOYEE, employeeId);
        EmployeeUtils.findById(employeeId);
        Page<MaritalStatusEntity> maritalStatuses =
                maritalStatusRepository.findAllByEmployeeIdOrderByRegistrationDate(employeeId,
                        PageRequest.of(page, size));
        if (maritalStatuses.isEmpty()) {
            LOGGER.warn(Constant.NOT_FOUND_MARITAL_STATUS_LIST, employeeId, page, size);
        } else {
            LOGGER.info(Constant.FOUND_MARITAL_STATUS_LIST_SUCCESS, maritalStatuses.getContent().size());
        }

        return maritalStatuses.map(entity -> Converter.toDto(entity, MaritalStatusDTO.class));
    }
}
