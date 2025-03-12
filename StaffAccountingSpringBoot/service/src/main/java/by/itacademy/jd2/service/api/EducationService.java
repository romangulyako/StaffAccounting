package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.EducationDTO;
import org.springframework.data.domain.Page;

public interface EducationService {
    /**
     * Добавляет новую запись об образовании сотрудника
     * или обновляет уже существующую
     * @param educationDTO объект с информацией об образовании
     * @return объект DTO сохраненного образования
     */
    EducationDTO saveOrUpdateEducation(EducationDTO educationDTO);

    /**
     * Удаляет запись об образовании по ее id
     * @param id идентификатор записи об образовании
     */
    void deleteEducation(Long id);

    /**
     * Получает запись об образовании по ее id
     * @param id идентификатор записи об образовании
     * @return объект DTO с информацией об образовании
     */
    EducationDTO getEducation(Long id);

    /**
     * Получает страницу со списком записей об образовании конкретного сотрудника
     * @param employeeId идентификатор сотрудника
     * @param page номер страницы
     * @param size размер страницы
     * @return страница со списком записей об образовании сотрудника
     */
    Page<EducationDTO> getEducationsByEmployeeId(Long employeeId, int page, int size);
}
