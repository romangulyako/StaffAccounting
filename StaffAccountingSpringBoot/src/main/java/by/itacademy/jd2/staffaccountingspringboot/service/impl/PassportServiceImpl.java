package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.EmployeeEntity;
import by.itacademy.jd2.staffaccountingspringboot.entity.PassportEntity;
import by.itacademy.jd2.staffaccountingspringboot.model.PassportDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.EmployeeRepository;
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
    private final EmployeeRepository employeeRepository;

    @Override
    public void addPassport(PassportDTO passportDTO) {
        this.savePassport(passportDTO, "Added passport with id={}");
    }

    @Override
    public void updatePassport(PassportDTO passportDTO) {
        this.savePassport(passportDTO, "Updated passport with id={}");
    }

    @Override
    public void deletePassport(Long employeeId) {
        PassportEntity passportEntity = passportRepository.findByEmployeeId(employeeId).orElse(null);

        if (passportEntity != null) {
            passportRepository.delete(passportEntity);

            LOGGER.info("Deleted passport with id={}", passportEntity.getId());
        }
    }

    @Override
    public PassportDTO getPassport(Long employeeId) {
        PassportEntity passportEntity = passportRepository.findByEmployeeId(employeeId).orElse(null);
        if (passportEntity != null) {
            LOGGER.info("Found passport of employee with id={}", employeeId);
        }

        return Converter.toDto(passportEntity, PassportDTO.class);
    }

    private void savePassport(PassportDTO passport, String logMessage) {
        EmployeeEntity employeeEntity = employeeRepository.findById(passport.getEmployeeId()).orElse(null);
        PassportEntity passportEntity = Converter.toEntity(passport, PassportEntity.class);
        if (employeeEntity != null) {
            employeeEntity.setPassport(passportEntity);
            passportEntity.setEmployee(employeeEntity);
            employeeRepository.save(employeeEntity);
            LOGGER.info(logMessage, passportEntity.getId());
        }
    }
}
