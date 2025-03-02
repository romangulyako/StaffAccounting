package by.itacademy.jd2.staffaccountingspringboot.service.api;

import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeFilterData;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeeItemDTO;
import by.itacademy.jd2.staffaccountingspringboot.dto.EmployeesPageDTO;

import java.util.List;

public interface EmployeeService {
    /**
     * Добавляет нового сотрудника или обновляет информацию об уже существующем
     * @param employeeDTO объект DTO с информацией о сотруднике
     */
    void saveOrUpdateEmployee(EmployeeDTO employeeDTO);
    /**
     * Удаляет сотрудника по его id
     * @param id идентификатор сотрудника
     */
    void deleteEmployee(Long id);

    /**
     * Получает сотрудника по его id
     * @param id идентификатор сотрудника
     * @return объект DTO с информацией о сотруднике
     */
    EmployeeDTO getEmployee(Long id);

    /**
     * Получает страницу со списком сотрудников
     * @param filterData данные для фильтрации
     * @param isFired флаг, указывающий, действующие сотрудники или уволенные
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с сотрудниками
     */
    EmployeesPageDTO getEmployeesPage(EmployeeFilterData filterData,
                                      Boolean isFired,
                                      int page,
                                      int size);

    /**
     * Возвращает сотрудника из уволенных в действующие
     * @param id идентификатор сотрудника
     */
    void returnToCurrent(Long id);

    /**
     * Получает список всех действующих сотрудников
     * @return список объектов DTO с действующими сотрудниками, содержащий только id и ФИО
     */
    List<EmployeeItemDTO> getCurrentEmployeeItems();
}
