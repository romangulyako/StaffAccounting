package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.MaritalStatusDTO;
import org.springframework.data.domain.Page;

public interface MaritalStatusService {
        /**
         * Добавляет новую запись о семейном положении сотрудника
         * или обновляет уже существующую
         * @param maritalStatusDTO объект с информацией о семейном положении
         * @return объект DTO сохраненного семейного положения
         */
        MaritalStatusDTO saveOrUpdateMaritalStatus(MaritalStatusDTO maritalStatusDTO);
        /**
         * Удаляет запись о семейном положении по ее id
         * @param id идентификатор записи о семейном положении
         */
        void deleteMaritalStatus(Long id);
        /**
         * Получает запись о семейном положении по ее d
         * @param id идентификатор записи о семейном положении
         * @return объект DTO с информацией о семейном положении
         */
        MaritalStatusDTO getMaritalStatus(Long id);
        /**
         * Получает страницу со списком записей о семейном положении конкретного сотрудника
         * @param employeeId идентификатор сотрудника
         * @param page номер страницы
         * @param size размер страницы
         * @return страница со списком записей о семейном положении сотрудника
         */
        Page<MaritalStatusDTO> getMaritalStatusesByEmployee(Long employeeId, int page, int size);
}
