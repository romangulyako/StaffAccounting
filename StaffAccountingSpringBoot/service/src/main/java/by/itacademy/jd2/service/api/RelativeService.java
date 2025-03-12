package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.RelativeDTO;
import org.springframework.data.domain.Page;

public interface RelativeService {
    /**
     * Добавляет новую запись о родственнике сотрудника
     * или обновляет уже существующую
     * @param relativeDTO объект с информацией о родственнике сотрудника
     * @return объект DTO сохраненного родственника
     */
    RelativeDTO saveOrUpdateRelative(RelativeDTO relativeDTO);
    /**
     * Удаляет запись о родственнике сотрудника по ее id
     * @param id идентификатор записи о родственнике сотрудника
     */
    void deleteRelative(Long id);
    /**
     * Получает запись о родственнике сотрудника по ее id
     * @param id идентификатор записи о родственнике сотрудника
     * @return объект DTO с информацией о родственнике сотрудника
     */
    RelativeDTO getRelative(Long id);
    /**
     * Получает страницу со списком записей о родственниках конкретного сотрудника
     * @param employeeId идентификатор сотрудника
     * @param page номер страницы
     * @param size размер страницы
     * @return страница со списком записей о родственниках сотрудника
     */
    Page<RelativeDTO> getRelatives(Long employeeId, int page, int size);
}
