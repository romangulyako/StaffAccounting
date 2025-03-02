package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.PositionDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.PositionHistoryDTO;
import org.springframework.data.domain.Page;

public interface PositionService {
    /**
     * Добавляет новую должность или обновляет информацию об уже существующей
     * @param positionDTO объект DTO с информацией о должности
     */
    void saveOrUpdatePosition(PositionDTO positionDTO);

    /**
     * Удаляет должность по ее id
     * @param id идентификатор должности
     */
    void deletePosition(Long id);

    /**
     * Получает должность по ее id
     * @param id идентификатор должности
     * @return объект DTO с информацией о должности
     */
    PositionDTO getPositionById(Long id);

    /**
     * Сокращает должность
     * @param id идентификатор должности
     */
    void reducePosition(Long id);

    /**
     * Возвращает должности статус действующей
     * @param id идентификатор должности
     */
    void restorePosition(Long id);

    /**
     * Получает информацию об истории должности
     * @param id идентификатор должности
     * @param page номер страницы
     * @param size размер страницы
     * @return страница со списком записей об истории должности
     */
    Page<PositionHistoryDTO> getPositionHistory(Long id, int page, int size);
}
