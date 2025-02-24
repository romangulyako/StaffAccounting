package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.PassportDTO;

public interface PassportService {
    void saveOrUpdatePassport(PassportDTO passportDTO);
    void deletePassport(Long id);
    PassportDTO getPassport(Long id);
}
