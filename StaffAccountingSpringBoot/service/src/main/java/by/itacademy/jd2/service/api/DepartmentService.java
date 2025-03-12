package by.itacademy.jd2.service.api;

import by.itacademy.jd2.dto.DepartmentDTO;
import by.itacademy.jd2.dto.DepartmentInfoDTO;
import org.springframework.data.domain.Page;

public interface DepartmentService {
    /**
     * Добавляет новый отдел или обновляет уже существующий
     * @param departmentDTO объект с информацией об отделе
     * @return объект DTO сохраненного отдела
     */
    DepartmentDTO saveOrUpdateDepartment(DepartmentDTO departmentDTO);

    /**
     * Удаляет отдел по его id
     * @param id идентификатор отдела
     */
    void deleteDepartment(Long id);

    /**
     * Получает информацию об отделе по его id
     * @param id идентификатор отдела
     * @return объект DTO с информацией об отделе
     */
    DepartmentDTO getDepartment(Long id);

    /**
     * Получает информацию об отделе и страницу с должностями этого отдела
     * @param id идентификатор отдела
     * @param isActual флаг, определяющий, действующий отдел или сокращенный
     * @param page номер страницы
     * @param size размер страницы
     * @return информация об отделе и его должностях
     */
    DepartmentInfoDTO getDepartmentInfo(Long id, Boolean isActual, int page, int size);

    /**
     * Получает страницу со списком отделов
     * @param page номер страницы
     * @param size размер страницы
     * @param isActual флаг, определяющий, действующий отдел или сокращенный
     * @return список отделов
     */
    Page<DepartmentDTO> getDepartments(int page, int size, Boolean isActual);

    /**
     * Сокращает отдел (переводит в статус сокращенных)
     * @param id идентификатор отдела
     */
    void reduceDepartment(Long id);
    /**
     * Возвращает отделу и его должностям статус действующего
     * @param id идентификатор отдела
     */
    void restoreDepartment(Long id);
}
