package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.PassportDTO;

public interface PassportService {
    /**
     * Добавляет паспорт или обновляет уже существующий
     * @param passportDTO объект DTO с информацией о паспорте сотрудника
     * @return объект DTO сохраненного паспорта
     */
    PassportDTO saveOrUpdatePassport(PassportDTO passportDTO);

    /**
     * Удаляет паспорт по employeeId сотрудника этого паспорта
     * @param employeeId идентификатор сотрудника
     */
    void deletePassport(Long employeeId);

    /**
     * Получает паспорт по id сотрудника, который владеет этим паспортом
     * @param employeeId идентификатор сотрудника
     * @return объект DTO с информацией о паспорте
     */
    PassportDTO getPassport(Long employeeId);
}
