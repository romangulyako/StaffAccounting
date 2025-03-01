package by.itacademy.jd2.staffaccountingspringboot.service.impl;

import by.itacademy.jd2.staffaccountingspringboot.converter.Converter;
import by.itacademy.jd2.staffaccountingspringboot.entity.PassportEntity;
import by.itacademy.jd2.staffaccountingspringboot.dto.PassportDTO;
import by.itacademy.jd2.staffaccountingspringboot.repository.PassportRepository;
import by.itacademy.jd2.staffaccountingspringboot.service.api.PassportService;
import by.itacademy.jd2.staffaccountingspringboot.utils.Constant;
import by.itacademy.jd2.staffaccountingspringboot.utils.EmployeeUtils;
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
        LOGGER.debug(Constant.ATTEMPT_TO_SAVE_PASSPORT, passportDTO.getEmployeeId());
        PassportEntity passportEntity = Converter.toEntity(passportDTO, PassportEntity.class);
        passportRepository.save(passportEntity);
        LOGGER.info(Constant.SAVE_SUCCESS_LOG, passportEntity.getId());
    }

    @Override
    public void deletePassport(Long employeeId) {
        LOGGER.debug(Constant.ATTEMPT_TO_DELETE_PASSPORT, employeeId);
        passportRepository.deleteByEmployeeId(employeeId);
        LOGGER.info(Constant.DELETE_PASSPORT_SUCCESS_LOG, employeeId);
    }

    @Transactional(readOnly = true)
    @Override
    public PassportDTO getPassport(Long employeeId) {
        LOGGER.debug(Constant.ATTEMPT_TO_FETCH_PASSPORT, employeeId);
        EmployeeUtils.findById(employeeId);
        PassportEntity passportEntity = passportRepository.findByEmployeeId(employeeId).orElse(null);

        if (passportEntity == null) {
            LOGGER.info(Constant.PASSPORT_NOT_FOUND, employeeId);
        } else {
            LOGGER.info(Constant.PASSPORT_FOUND_SUCCESS, passportEntity.getId());
        }

        return Converter.toDto(passportEntity, PassportDTO.class);
    }
}
