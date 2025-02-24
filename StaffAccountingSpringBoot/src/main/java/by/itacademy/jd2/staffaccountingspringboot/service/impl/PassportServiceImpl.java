package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.PassportEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.PassportDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.PassportRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.PassportService;
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
    private final PassportRepository passportRepository;

    @Override
    public void saveOrUpdatePassport(PassportDTO passportDTO) {
        PassportEntity passportEntity = Converter.toEntity(passportDTO, PassportEntity.class);
        passportRepository.save(passportEntity);
        LOGGER.info("Passport saved successfully. ID={}", passportEntity.getId());
    }

    @Override
    public void deletePassport(Long employeeId) {
        passportRepository.deleteByEmployeeId(employeeId);
        LOGGER.info("Passport of employee with id={} deleted successfully", employeeId);
    }

    @Override
    public PassportDTO getPassport(Long employeeId) {
        PassportEntity passportEntity = passportRepository.findByEmployeeId(employeeId)
                .orElse(null);
        if (passportEntity != null) {
            LOGGER.info("Successfully fetched passport with id={} from database", passportEntity.getId());
        } else {
            LOGGER.info("Passport of employee with id={} not found", employeeId);
        }

        return Converter.toDto(passportEntity, PassportDTO.class);
    }
}
