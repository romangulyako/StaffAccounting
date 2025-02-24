package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.PassportEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.PassportDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.PassportRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.PassportService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PassportServiceImpl.class);
    private static final String SAVE_SUCCESS_LOG = "Passport saved successfully. ID={}";
    private static final String DELETE_SUCCESS_LOG = "Passport of employee with id={} deleted successfully";
    private static final String NOT_FOUND_LOG = "Passport of employee with id={} not found";
    private static final String NOT_FOUND_EXCEPTION = "Passport of employee not found. Employee's ID=";
    private static final String FOUND_SUCCESS_EXCEPTION = "Successfully fetched passport with id={} from database";
    private final PassportRepository passportRepository;

    @Override
    public void saveOrUpdatePassport(PassportDTO passportDTO) {
        PassportEntity passportEntity = Converter.toEntity(passportDTO, PassportEntity.class);
        passportRepository.save(passportEntity);
        LOGGER.info(SAVE_SUCCESS_LOG, passportEntity.getId());
    }

    @Override
    public void deletePassport(Long employeeId) {
        passportRepository.deleteByEmployeeId(employeeId);
        LOGGER.info(DELETE_SUCCESS_LOG, employeeId);
    }

    @Override
    public PassportDTO getPassport(Long employeeId) {
        PassportEntity passportEntity = passportRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> {
                    LOGGER.info(NOT_FOUND_LOG, employeeId);
                    return new EntityNotFoundException(NOT_FOUND_EXCEPTION + employeeId);
                });

        LOGGER.info(FOUND_SUCCESS_EXCEPTION, passportEntity.getId());
        return Converter.toDto(passportEntity, PassportDTO.class);
    }
}
